/* @author Deepak Rai */
package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class CreateNestedJSONObjectUsingMap {

  /**
   * {@summary create json object using Map and pass it as payload}
   *
   * @author deepak rai
   */
  @Test
  public void createNestedJSONObjectUsingMapTest() {

    Map<String, Object> jsonBodyUsingMap = new HashMap<>();
    jsonBodyUsingMap.put("firstname", "Jim");
    jsonBodyUsingMap.put("lastname", "Brown");
    jsonBodyUsingMap.put("totalPrice", 111);
    jsonBodyUsingMap.put("depositPaid", true);

    Map<String, String> bookingDatesMap = new HashMap<>();
    bookingDatesMap.put("checkIn", "2021-07-01");
    bookingDatesMap.put("checkout", "2021-08-01");

    jsonBodyUsingMap.put("bookingDates", bookingDatesMap);
    jsonBodyUsingMap.put("additionalNeeds", "Breakfast");

    // Given
    RestAssured.given()
        .baseUri("https://restful-booker.herokuapp.com/booking")
        .contentType(ContentType.JSON)
        .body(jsonBodyUsingMap)
        .log()
        .all()
        // When
        .when()
        .post()
        // Then
        .then()
        .assertThat()
        .statusCode(200)
        .log()
        .all();
  }
}
