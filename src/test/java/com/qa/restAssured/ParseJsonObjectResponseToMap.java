package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Map;

public class ParseJsonObjectResponseToMap {

  @Test
  public void parseResponseToMap() {
    Map<String, Object> responseBody;

    String jsonString =
            """
                    {
                        "firstname" : "Jim",
                        "lastname" : "Brown",
                        "totalprice" : 111,
                        "depositpaid" : true,
                        "bookingdates" : {
                            "checkin" : "2018-01-01",
                            "checkout" : "2019-01-01"
                        },
                        "additionalneeds" : "Breakfast"
                    }""";

    responseBody =
        RestAssured.given()
            .baseUri("https://restful-booker.herokuapp.com/")
            .basePath("booking")
            .contentType(ContentType.JSON)
            .body(jsonString)
            .when()
            .post()
            .then().log().all()
            .extract()
            .body()
            // Extract response as Map<String,Object>
            .as(new TypeRef<>() {
            });

    // To print booking id
    System.out.println("Booking id is : "+ responseBody.get("bookingid"));

    Map<String, Object> bookingDetails = (Map<String, Object>) responseBody.get("booking");
    System.out.println("First name is : "+ bookingDetails.get("firstname"));
  }
}
