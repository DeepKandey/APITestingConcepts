/** @author Deepak Rai */
package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.testng.annotations.Test;

public class CreateJSONArrayUsingList {
  /**
   * {@summary Create JSON array request body using List}
   *
   * @param
   * @return
   * @author deepak rai
   */
  @Test
  public void createJSONArrayPayloadUsingListTest() {

    // First booking
    Map<String, Object> bookingOne = new HashMap<>();
    bookingOne.put("firstname", "Jim");
    bookingOne.put("lastname", "Brown");
    bookingOne.put("totalprice", 111);
    bookingOne.put("depositpaid", true);

    Map<String, String> bookingDatesMapOne = new HashMap<>();
    bookingDatesMapOne.put("checkin", "2021-07-01");
    bookingDatesMapOne.put("checkout", "2021-08-01");

    bookingOne.put("bokingdates", bookingDatesMapOne);
    bookingOne.put("additionalneeds", "Breakfast");

    // Second booking
    Map<String, Object> bookingTwo = new HashMap<>();
    bookingTwo.put("firstname", "Jim");
    bookingTwo.put("lastname", "Brown");
    bookingTwo.put("totalprice", 111);
    bookingTwo.put("depositpaid", true);

    Map<String, String> bookingDatesMapTwo = new HashMap<>();
    bookingDatesMapTwo.put("checkin", "2021-07-01");
    bookingDatesMapTwo.put("checkout", "2021-08-01");

    bookingTwo.put("bokingdates", bookingDatesMapTwo);
    bookingTwo.put("additionalneeds", "Breakfast");

    // Creating JSON array to add both JSON objects
    List<Map<String, Object>> jsonArrayPayLoad = new ArrayList<>();
    jsonArrayPayLoad.add(bookingOne);
    jsonArrayPayLoad.add(bookingTwo);

    // Given
    RestAssured.given()
        .baseUri("https://restful-booker.herokuapp.com/booking")
        .contentType(ContentType.JSON)
        .body(jsonArrayPayLoad)
        .log()
        .all()
        // When
        .when()
        .post()
        // Then
        .then()
        // Asserting status code as 500 as it does not accept json array payload
        .assertThat()
        .statusCode(500)
        .log()
        .all();
  }
}
