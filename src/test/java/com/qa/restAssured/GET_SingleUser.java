package com.qa.restAssured;

import com.qa.constants.CommonAPIConstants;
import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import static com.qa.util.LoggerUtil.log;

public class GET_SingleUser {

  @Test
  public void getSingleUserDetails() throws ParseException {

    // Headers details
    Header h1 = new Header("content-type", "application/json");
    List<Header> headerList = new ArrayList<Header>();
    headerList.add(h1);

    Response restResponse =
        RestCommonMethods.getAPIRequest(
            CommonAPIConstants.REQRES_ENDPOINT_URL,
            CommonAPIConstants.SINGLE_USERS_URL,
            headerList);

    // 1. Response Body as String
    log("Response Body in json:-");
    restResponse.body().prettyPrint();

    // 2. Parsing data in Response Body
    JSONParser parser = new JSONParser();
    JSONObject jsonStringResponse = (JSONObject) parser.parse(restResponse.getBody().asString());

    // 3. Iterating through the user Details
    HashMap<String, String> singleUserData =
        (HashMap<String, String>) jsonStringResponse.get("data");
    for (Entry userDetailsEntry : singleUserData.entrySet()) {
      log(userDetailsEntry.getKey() + " = " + userDetailsEntry.getValue());
    }
  } // end of method getSingleUSerDetails
} // end of class RestAssured_SingleUser
