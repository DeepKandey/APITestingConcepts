/**
 * @author Deepak Rai
 */
package com.qa.restAssured;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateNestedJSONObjectUsingMap {

	/**
	 * 
	 * {@summary create json object using Map and pass it as payload}
	 * 
	 * @param
	 * @return
	 * @author deepak rai
	 */
	@Test
	public void createNestedJSONObjectUsingMapTest() {

		Map<String, Object> jsonBodyUsingMap = new HashMap<>();
		jsonBodyUsingMap.put("firstname", "Jim");
		jsonBodyUsingMap.put("lastname", "Brown");
		jsonBodyUsingMap.put("totalprice", 111);
		jsonBodyUsingMap.put("depositpaid", true);

		Map<String, String> bookingDatesMap = new HashMap<>();
		bookingDatesMap.put("checkin", "2021-07-01");
		bookingDatesMap.put("checkout", "2021-08-01");

		jsonBodyUsingMap.put("bokingdates", bookingDatesMap);
		jsonBodyUsingMap.put("additionalneeds", "Breakfast");

		// Given
		RestAssured.given().baseUri("https://restful-booker.herokuapp.com/booking").contentType(ContentType.JSON)
				.body(jsonBodyUsingMap).log().all()
				// When
				.when().post()
				// Then
				.then().assertThat().statusCode(200).log().all();
	}
}
