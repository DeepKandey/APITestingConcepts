package com.qa.restAssured;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.Test;

public class ShareDataUsingITestContext {

  @Test()
  public void createBooking(ITestContext context) {
    // ObjectMapper object
    ObjectMapper objMapper = new ObjectMapper();

    // booking Details
    ObjectNode bookingDetails = objMapper.createObjectNode();
    bookingDetails.put("firstname", "Jim");
    bookingDetails.put("lastname", "Brown");
    bookingDetails.put("totalprice", 111);
    bookingDetails.put("depositpaid", true);

    // booking date details
    ObjectNode bookingDatesDetails = objMapper.createObjectNode();
    bookingDatesDetails.put("checkin", "2021-07-01");
    bookingDatesDetails.put("checkout", "2021-08-01");

    bookingDetails.set("bookingdates", bookingDatesDetails);
    bookingDetails.put("additionalneeds", "Breakfast");

    int bookingId =
        RestAssured.given()
            .log()
            .all()
            .baseUri("https://restful-booker.herokuapp.com/")
            .basePath("booking")
            .contentType(ContentType.JSON)
            .body(bookingDetails)
            .when()
            .post()
            .getBody()
            .jsonPath()
            .get("bookingid");
    //    // storing data in a context to use for other tests
    context.setAttribute("bookingId", bookingId);
  }

  @Test(
      priority = 1,
      dependsOnMethods = {"createBooking"})
  public void updateBooking(ITestContext context) {
    // ObjectMapper object
    ObjectMapper objMapper = new ObjectMapper();

    // booking Details
    ObjectNode bookingDetails = objMapper.createObjectNode();
    bookingDetails.put("firstname", "Jim");
    bookingDetails.put("lastname", "Brown");
    bookingDetails.put("totalprice", 111);
    bookingDetails.put("depositpaid", true);

    // booking date details
    ObjectNode bookingDatesDetails = objMapper.createObjectNode();
    bookingDatesDetails.put("checkin", "2021-07-01");
    bookingDatesDetails.put("checkout", "2021-09-01");

    bookingDetails.set("bookingdates", bookingDatesDetails);
    bookingDetails.put("additionalneeds", "Breakfast");

    int bookingId = (int) context.getAttribute("bookingId");

    RestAssured.given()
        .log()
        .all()
        .baseUri("https://restful-booker.herokuapp.com/")
        .basePath("booking/" + bookingId)
        .header("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM=")
        .contentType(ContentType.JSON)
        .body(bookingDetails)
        .when()
        .put()
        .then()
        .log()
        .all();
  }
}
