package com.qa.restAssured;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

public class UseJsonObjectCreatedUsingJacksonAPIAsPayload {
  @Test
  private void UseJsonObjectCreatedUsingJacksonAPIAsPayloadTest() {

    // ObjectMapper object
    ObjectMapper objMapper = new ObjectMapper();

    // booking Details
    ObjectNode bookingDetails = objMapper.createObjectNode();
    bookingDetails.put("firstname", "Jim");
    bookingDetails.put("lastname", "Brown");
    bookingDetails.put("totalPrice", 111);
    bookingDetails.put("depositPaid", true);

    // booking date details
    ObjectNode bookingDatesDetails = objMapper.createObjectNode();
    bookingDatesDetails.put("checkIn", "2021-07-01");
    bookingDatesDetails.put("checkout", "2021-08-01");

    bookingDetails.set("bookingDates", bookingDatesDetails);
    bookingDetails.put("additionalNeeds", "Breakfast");

    // Given
    RestAssured.given()
        .baseUri("https://restful-booker.herokuapp.com/booking")
        .contentType(ContentType.JSON)
        // pass JSON pay load directly
        .body(bookingDetails)
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
