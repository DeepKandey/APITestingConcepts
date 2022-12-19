package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static com.qa.util.LoggerUtil.log;

public class RequestSpecBuilderExample {

  /**
   * {summary Create RequestSpecification using RequestSpecificBuilder. Get and Validate Response
   * time}
   *
   * @author deepak rai
   */
  @Test
  public static void createRequestUsingRequestSpecBuilder() {

    // Create an object of RequestSpecBuilder
    RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
    // setting Base URI
    reqSpecBuilder.setBaseUri("https://restful-booker.herokuapp.com");
    // setting Base Path
    reqSpecBuilder.setBasePath("/booking");
    // Getting RequestSpecification reference using build() method
    RequestSpecification reqSpec = reqSpecBuilder.build();

    // Usage in different styles
    // We can directly call http verbs on RequestSpecification
    //		Response response1 = reqSpec.get();
    //		log(response1.asString());

    // We can also pass RequestSpecification reference variable in overloaded
    // given() method
    Response response2 = RestAssured.given(reqSpec).get();
    log(response2.asString());

    // We can also pass RequestSpecification reference variable in overloaded
    // given() method
    Response response3 = RestAssured.given().spec(reqSpec).get();
    log(response3.asString());

    // By default, response time is given in milliseconds
    long responseTime1 = response2.getTime();
    log("Response time in ms using getTime(): " + responseTime1);

    // By default, response time is given in milliseconds
    long responseTimeInSeconds1 = response2.getTimeIn(TimeUnit.SECONDS);
    log("Response time in ms using getTimeIn(): " + responseTimeInSeconds1);

    // Similar methods
    long responseTime2 = response2.time();
    log("Response time in ms using time(): " + responseTime2);

    long responseTimeInSeconds2 = response2.timeIn(TimeUnit.SECONDS);
    log("Response time in seconds using timeIn(): " + responseTimeInSeconds2);

    // Interface ValidatableResponseOptions :-

    // Get ValidatableResponse type
    ValidatableResponse valRes = response2.then();

    // Asserting response time is less than 2000 milliseconds. L just represent
    // long. It is in milliseconds
    valRes.time(Matchers.lessThan(4000L));

    // Asserting response time is greater than 2000 milliseconds.
    valRes.time(Matchers.greaterThan(2000L));

    // Asserting response time in between some values
    valRes.time(
        Matchers.both(Matchers.greaterThanOrEqualTo(1000L)).and(Matchers.lessThanOrEqualTo(4000L)));

    // Assert response time in different time units
    valRes.time(Matchers.lessThan(4L), TimeUnit.SECONDS);
  }
}
