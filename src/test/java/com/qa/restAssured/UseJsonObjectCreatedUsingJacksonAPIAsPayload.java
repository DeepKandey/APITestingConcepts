/**
 * @author Deepak Rai
 */
package com.qa.restAssured;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class UseJsonObjectCreatedUsingJacksonAPIAsPayload {
	@Test
	private void UseJsonObjectCreatedUsingJacksonAPIAsPayloadtest() throws JsonProcessingException {

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

		// Given
		RestAssured.given().baseUri("https://restful-booker.herokuapp.com/booking").contentType(ContentType.JSON)
				// pass JSON pay load directly
				.body(bookingDetails).log().all()
				// When
				.when().post()
				// Then
				.then().assertThat().statusCode(200).log().all();
	}

}
