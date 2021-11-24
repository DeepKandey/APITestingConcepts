package com.qa.restAssured;

import com.qa.constants.CommonAPIConstants;
import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import static com.qa.util.LoggerUtil.log;

public class GET_ListUsers {

  @Test
  public void getListOfUsersDetails() throws ParseException {

    Response restResponse =
        RestCommonMethods.getAPIRequest(
            CommonAPIConstants.REQRES_ENDPOINT_URL,
            CommonAPIConstants.USERS_LIST_URL,
            Collections.emptyList());

    // Status Line
    log("Status Line--> " + restResponse.getStatusLine());
    // Status Code
    log("Status Code--> " + restResponse.getStatusCode());
    // Headers
    Headers allHeaders = restResponse.getHeaders();
    /* log("Headers-->" + allHeaders); */
    log("Headers as below-->");
    for (Header header : allHeaders) {
      log(header.getName() + " = " + header.getValue());
    }

    // Response Body
    log("Response body in json-->");
    restResponse.body().prettyPrint();

    // First get the JsonPath object instance from the Response interface
    JsonPath jsonResponseBody = restResponse.body().jsonPath();
    // Print per page count using in built function of Rest Assured
    int per_Page_Count = jsonResponseBody.get("per_page");
    log("Per page count using REST Assured--> " + per_Page_Count);

    // With JSON Simple API
    JSONParser parser = new JSONParser();
    JSONObject jsonObject = (JSONObject) parser.parse(restResponse.body().asString());

    // Printing per page count using JSON Simple API
    Long per_Page_JSONSimple = (Long) jsonObject.get("per_page");
    log("Per page count using JSON Simple--> " + per_Page_JSONSimple);

    // Print JSON Array using JSON Simple
    JSONArray usersDataArray = (JSONArray) jsonObject.get("data");

    Iterator<Object> usersDataArrayIterator = usersDataArray.iterator();
    while (usersDataArrayIterator.hasNext()) {
      for (Object o : ((Map<String,String>) usersDataArrayIterator.next()).entrySet()) {
        Map.Entry singleRecordEntrySet = (Map.Entry) o;
        System.out.print(
                singleRecordEntrySet.getKey() + " = " + singleRecordEntrySet.getValue() + ", ");
      }
    }
  } // end of method getListOfUsersDetails
} // End of class RestAssuredGet_ListUsers
