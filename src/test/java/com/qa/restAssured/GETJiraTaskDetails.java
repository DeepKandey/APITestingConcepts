package com.qa.restAssured;

import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.poi.ss.usermodel.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GETJiraTaskDetails {
  // Headers
  private static final Header contentType = new Header("content-type", "application/json");
  private static final Header authorization =
      new Header("Authorization", System.getenv("Authentication_key"));

  // Sheet Path
  private static final String path =
      System.getProperty("user.dir") + "/src/main/java/com/qa/testData/TestSheet.xlsx";

  /**
   * This method is to get board ids for OND project
   *
   * @return ArrayList<Long> arrayList of boardIds
   * @throws ParseException ParseException
   * @throws IOException IOException
   * @author deepak rai
   */
  public static ArrayList<Long> getBoardDetails() throws ParseException, IOException {

    ArrayList<Long> boardsIds = new ArrayList<>();

    // Excel related fields
    FileInputStream fis = new FileInputStream(path);
    Workbook workBook = WorkbookFactory.create(fis);
    fis.close();

    // Header List
    List<Header> headerList = new ArrayList<>();
    headerList.add(contentType);
    headerList.add(authorization);

    // API Call
    Response response =
        RestCommonMethods.getAPIRequest(
            "https://ftdr.atlassian.net", "/rest/agile/1.0/board", headerList);

    // Parsing JSON body
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(response.body().asString());
    JSONArray boardList = (JSONArray) jsonObject.get("values");

    int sheetIndex = 0; // counter to store sheet index

    // loop over all boards and get the required board ids
    for (Object o : boardList) {
      JSONObject singleBoardDetails = (JSONObject) o;

      if (singleBoardDetails.get("name").equals("OND-Backend")
          || singleBoardDetails.get("name").equals("OND-Frontend")) {
        String boardName = (String) singleBoardDetails.get("name");
        if (!workBook.getSheetName(sheetIndex).equals(boardName)) {
          workBook.setSheetName(sheetIndex, boardName); // set the sheet name as per the board name
        }
        sheetIndex++;
        long boardId = (long) singleBoardDetails.get("id");
        boardsIds.add(boardId);
      }
    }
    FileOutputStream fileOut = new FileOutputStream(path);
    workBook.write(fileOut);
    fileOut.close();
    return boardsIds;
  }

  /**
   * This method is to get sprint ids for board ids returned by getBoardDetails() method
   *
   * @return ArrayList<Long> arrayList of sprintIds
   * @throws ParseException ParseException
   * @throws IOException IOException
   * @author deepak rai
   */
  public static ArrayList<Long> getSprintDetails() throws ParseException, IOException {

    ArrayList<Long> sprintIds = new ArrayList<>();

    // Header List
    List<Header> headerList = new ArrayList<>();
    headerList.add(contentType);
    headerList.add(authorization);

    // Get board ids
    ArrayList<Long> boardIds = getBoardDetails();

    // Iterate over the board id and get respective sprint ids
    for (Long boardId : boardIds) {
      // API call
      Response response =
          RestCommonMethods.getAPIRequest(
              "https://ftdr.atlassian.net",
              "/rest/agile/1.0/board/" + boardId + "/sprint",
              headerList);

      // Parsing JSON body
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(response.body().asString());
      JSONArray sprintList = (JSONArray) jsonObject.get("values");

      // Fetch active sprint ids
      for (Object o : sprintList) {
        JSONObject singleSprintDetails = (JSONObject) o;
        if (singleSprintDetails.get("state").equals("active")
            && (singleSprintDetails.get("name").equals("ONDBackend 2021 PI 2 Sprint 2")
                || singleSprintDetails.get("name").equals("ONDFrontend 2021 PI 2 Sprint 2"))) {
          // String sprintName = (String) singleSprintDetails.get("name");
          long sprintId = (long) singleSprintDetails.get("id");
          sprintIds.add(sprintId);
        }
      }
    }
    return sprintIds;
  }

  /**
   * This method is used to write jira issues details in the sheet
   *
   * @throws IOException IOException
   * @throws ParseException ParseException
   * @author deepak rai
   */
  @Test
  public void getIssueDetails() throws IOException, ParseException {

    // Fetch board and sprint ids
    ArrayList<Long> boardIds = getBoardDetails();
    ArrayList<Long> sprintIds = getSprintDetails();

    // Excel related fields
    FileInputStream fis = new FileInputStream(path);
    Workbook workBook = WorkbookFactory.create(fis);
    fis.close();

    Row row;
    Cell cell;

    // Header List
    List<Header> headerList = new ArrayList<>();
    headerList.add(contentType);
    headerList.add(authorization);

    // loop over board id and respective sprint id to get issue details
    for (int k = 0, l = 0; k < boardIds.size() && l < sprintIds.size(); k++, l++) {
      // API call
      Response response =
          RestCommonMethods.getAPIRequest(
              "https://ftdr.atlassian.net",
              "/rest/agile/1.0/board/" + boardIds.get(k) + "/sprint/" + sprintIds.get(l) + "/issue",
              headerList);

      Sheet sheet = workBook.getSheetAt(k);

      // Parsing JSON body
      JSONParser parser = new JSONParser();
      JSONObject jsonObject = (JSONObject) parser.parse(response.body().asString());
      JSONArray issueList = (JSONArray) jsonObject.get("issues");

      // loop over all the issues in the given sprint id and get the details
      for (int i = 0; i < issueList.size(); i++) {
        JSONObject singleIssueDetails = (JSONObject) issueList.get(i);

        // create row in the sheet for each jira ticket
        row = sheet.createRow(i + 1);

        int j = 0;
        // jira ticked id
        String issueKey = (String) singleIssueDetails.get("key");
        cell = row.createCell(j);
        cell.setCellValue(issueKey);

        // fields detail of the jira ticket
        JSONObject fields = (JSONObject) singleIssueDetails.get("fields");

        // Summary
        String summary = (String) fields.get("summary");
        cell = row.createCell(++j);
        cell.setCellValue(summary);

        // Type
        JSONObject issueType = (JSONObject) fields.get("issuetype");
        String issueTypeName = (String) issueType.get("name");
        cell = row.createCell(++j);
        cell.setCellValue(issueTypeName);

        // Current Status
        JSONObject status = (JSONObject) fields.get("status");
        String statusName = (String) status.get("name");
        cell = row.createCell(++j);
        cell.setCellValue(statusName);

        // label
        JSONArray labelsArray = (JSONArray) fields.get("labels");
        String label = null;
        for (Object o1 : labelsArray) {
          if (o1.toString().contains("FocusGroup")) label = o1.toString();
        }
        cell = row.createCell(++j);
        cell.setCellValue(label);

        // Write jira ticket details to the sheet
        FileOutputStream fileOut = new FileOutputStream(path);
        workBook.write(fileOut);
        fileOut.close();
      }
    }
  }
}
