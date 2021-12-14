package com.qa.restAssured;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class AssertFullResponseBody {

  @Test
  public void assertResponseBody() {
    RestAssured.given()
        .log()
        .all()
        .baseUri("https://gorest.co.in/public-api/")
        .basePath("users/{id}")
        .pathParam("id", "3188")
        .when()
        .get()
        .then()
        .log()
        .all()
        .assertThat()
        // Pass full expected response body with hamcrest matchers
        .body(
            Matchers.equalTo(
                "{\"code\":200,\"meta\":null,\"data\":{\"id\":3188,\"name\":\"lop\",\"email\":\"lop@gmail.com\",\"gender\":\"male\",\"status\":\"active\"}}"));
  }
}
