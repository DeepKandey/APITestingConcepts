package com.qa.restAssured;

import com.google.gson.Gson;
import com.qa.constants.CommonAPIConstants;
import com.qa.pojo.UpdateUserDetails;
import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static com.qa.util.LoggerUtil.log;

public class PUT_UpdateUser {

  @Test
  public void updateUserTest() {

    // Headers details
    Header h1 = new Header("content-type", "application/json");
    List<Header> headerList = new ArrayList<>();
    headerList.add(h1);

    // Serialize POJO into JSON String | marshalling
    UpdateUserDetails updateUserRequest = new UpdateUserDetails("Deepak", "Automation");
    Gson gson = new Gson();
    String jsonStringRequestBody = gson.toJson(updateUserRequest);
    log("Json String Request Payload--> " + jsonStringRequestBody);

    // Rest Response
    Response restResponse =
        RestCommonMethods.putAPIRequest(
            CommonAPIConstants.REQRES_ENDPOINT_URL,
            CommonAPIConstants.SINGLE_USERS_URL,
            headerList,
            jsonStringRequestBody);

    // Validate Status Code
    log("Status Code in Response--> " + restResponse.getStatusCode());

    String jsonStringresponseBody = restResponse.getBody().asString();
    log("JSON String Response Body--> " + jsonStringresponseBody);

    // Deserialization from JsonString to POJO | unmarshalling
    UpdateUserDetails updateUserResponse =
        gson.fromJson(jsonStringresponseBody, UpdateUserDetails.class);

    // Validate Name and updatedAt fields
    Assert.assertEquals(updateUserResponse.getName(), updateUserRequest.getName());
    Assert.assertNotNull(updateUserResponse.getUpdatedAt()); // UpdatedAt should not be null
  }
}
