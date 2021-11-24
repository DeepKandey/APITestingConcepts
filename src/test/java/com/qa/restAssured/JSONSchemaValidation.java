/* */
package com.qa.restAssured;

import com.qa.constants.CommonAPIConstants;
import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/** @author deepak */
public class JSONSchemaValidation {

  @Test
  public void validateJSONSchemaForSingleUser() {

    // Headers details
    Header h1 = new Header("Authorization", "Bearer NFsy75Qo3gD8owfvnhan1fR5njSbItF8I6vu");
    List<Header> headerList = new ArrayList<>();
    headerList.add(h1);

    Response response =
        RestCommonMethods.getAPIRequest(
            CommonAPIConstants.GOREST_ENDPOINT_URL,
            CommonAPIConstants.SINGLE_USER_URL_GOREST,
            headerList);

    // Validate response schema
    ValidatableResponse validatableResponse = response.then();
    validatableResponse
        .assertThat()
        .body(matchesJsonSchemaInClasspath("SingleUserJSONSchemaOnGorest.json"));
  }
}
