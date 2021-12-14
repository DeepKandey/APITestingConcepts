package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

public class ParseJsonArrayResponseToList {

  @Test
  public void parseJsonArrayToList() {
    List<Map<String, Object>> responseBody;

    responseBody =
        RestAssured.given()
            .baseUri("https://restful-booker.herokuapp.com/")
            .basePath("booking")
            .when()
            .get()
            .then()
            .extract()
            .body()
            // Extract response as List<Map<String, Object>> since the response in a list of Map
            // format
            .as(new TypeRef<>() {
            });

    System.out.println("Total bookings : " + responseBody.size());
    System.out.println("All booking ids are : ");

    for (Map<String, Object> booking : responseBody) {
      System.out.println(booking.get("bookingid"));
    }
  }
}
