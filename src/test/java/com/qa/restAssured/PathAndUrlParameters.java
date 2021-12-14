package com.qa.restAssured;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class PathAndUrlParameters {

  // Using Path parameter
  @Test
  public void pathVariable1() {
    RestAssured.given()
        .log()
        .all()
        .baseUri("https://restful-booker.herokuapp.com/")
        .basePath("{resourcePath}")
        .pathParam("resourcePath", "booking")
        .when()
        .get()
        .then()
        .log()
        .all();
  }

  // complete parameterized URL in HTTP method
  @Test
  public void pathVariable2() {
    RestAssured.given()
        .log()
        .all()
        .pathParam("resourcePath", "booking")
        .when()
        .get("https://restful-booker.herokuapp.com/{resourcePath}")
        .then()
        .log()
        .all();
  }

  // Using inline path Parameter index based
  @Test
  public void pathVariable3() {
    RestAssured.given()
        .log()
        .all()
        .when()
        .get("https://restful-booker.herokuapp.com/{resourcePath}", "booking")
        .then()
        .log()
        .all();
  }

  @Test
  public void pathVariable4() {
    RestAssured.given()
        .log()
        .all()
        .when()
        .get("https://restful-booker.herokuapp.com/{resourcePath}/{bookingId}", "booking", "10")
        .then()
        .log()
        .all();
  }

  @Test
  public void pathVariable5() {
    RestAssured.given()
        .log()
        .all()
        .baseUri("https://restful-booker.herokuapp.com/{resourcePath}")
        .pathParam("resourcePath", "booking")
        .when()
        .get()
        .then()
        .log()
        .all();
  }
}
