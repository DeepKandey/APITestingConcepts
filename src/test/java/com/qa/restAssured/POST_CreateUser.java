package com.qa.restAssured;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.constants.CommonAPIConstants;
import com.qa.pojo.CreateUserDetails;
import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.qa.util.LoggerUtil.log;

public class POST_CreateUser {

  @Test
  public void createUser() throws IOException {

    // Declare Variables
    String userName = "Deepak";
    String job = "Cleaning";

    // Create POJO object
    CreateUserDetails userRequest = new CreateUserDetails(userName, job);

    // Serialize POJO into JSON String | marshalling
    ObjectMapper mapper = new ObjectMapper();
    String jsonStringRequestBody = mapper.writeValueAsString(userRequest);
    log("JSON string Request Payload-->" + jsonStringRequestBody);

    // Headers details
    Header h1 = new Header("content-type", "application/json");
    List<Header> headerList = new ArrayList<Header>();
    headerList.add(h1);

    // Rest Response
    Response restResponse =
        RestCommonMethods.postAPIRequest(
            CommonAPIConstants.REQRES_ENDPOINT_URL,
            CommonAPIConstants.USERS_LIST_URL,
            headerList,
            jsonStringRequestBody);

    // Status Code in response
    log("Status code--> " + restResponse.getStatusCode());

    // Response Body
    String jsonStringResponseBody = restResponse.getBody().asString();
    log("JSON String Response Payload--> " + jsonStringResponseBody);

    // Deserialization from JsonString to POJO | unmarshalling
    CreateUserDetails userResponse =
        mapper.readValue(jsonStringResponseBody, CreateUserDetails.class);
    log("User id--> " + userResponse.getId());

    // Comparing Name passed in Request and Name received in Response
    Assert.assertEquals(userRequest.getName(), userResponse.getName(), "Name does not match");
  }
} // end of class RestAssuredPost_CreateUser
