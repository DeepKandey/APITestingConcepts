/* @author Deepak Rai */
package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class BasicAuthHandle {

  /**
   * {summary method to handle basic authentication using rest assured}
   *
   * @author deepak
   */
  @Test
  public void basicAuthRestAssuredHandle() {
    RequestSpecification requestSpecification =
        RestAssured.given()
            .baseUri("http://the-internet.herokuapp.com")
            .auth()
            .basic("admin", "admin")
            .log()
            .all();

    Response response = requestSpecification.get("/basic_auth");
    assertThat(response.statusCode(), equalTo(200));
    response.getBody().prettyPrint();
  }
}
