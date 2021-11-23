/* @author Deepak Rai */
package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateJSONArrayUsingList {
  /**
   * {@summary Create JSON array request body using List}
   *
   * @author deepak rai
   */
  @Test
  public void createJSONArrayPayloadUsingListTest() {

    // First booking
    Map<String, Object> bookingOne = new HashMap<>();
    bookingOne.put("firstname", "Jim");
    bookingOne.put("lastname", "Brown");
    bookingOne.put("totalPrice", 111);
    bookingOne.put("depositPaid", true);

    Map<String, String> bookingDatesMapOne = new HashMap<>();
    bookingDatesMapOne.put("checkIn", "2021-07-01");
    bookingDatesMapOne.put("checkOut", "2021-08-01");

    bookingOne.put("bookingDates", bookingDatesMapOne);
    bookingOne.put("additionalNeeds", "Breakfast");

    // Second booking
    Map<String, Object> bookingTwo = new HashMap<>();
    bookingTwo.put("firstname", "Jim");
    bookingTwo.put("lastname", "Brown");
    bookingTwo.put("totalPrice", 111);
    bookingTwo.put("depositPaid", true);

    Map<String, String> bookingDatesMapTwo = new HashMap<>();
    bookingDatesMapTwo.put("checkIn", "2021-07-01");
    bookingDatesMapTwo.put("checkOut", "2021-08-01");

    bookingTwo.put("bookingDates", bookingDatesMapTwo);
    bookingTwo.put("additionalNeeds", "Breakfast");

    // Creating JSON array to add both JSON objects
    List<Map<String, Object>> jsonArrayPayLoad = new ArrayList<>();
    jsonArrayPayLoad.add(bookingOne);
    jsonArrayPayLoad.add(bookingTwo);

    RestAssured.given()
        .baseUri("https://restful-booker.herokuapp.com/booking")
        .contentType(ContentType.JSON)
        .body(jsonArrayPayLoad)
        .log()
        .all()
        .when()
        .post()
        .then()
        // Asserting status code as 500 as it does not accept json array payload
        .assertThat()
        .statusCode(500)
        .log()
        .all();
  }
}
