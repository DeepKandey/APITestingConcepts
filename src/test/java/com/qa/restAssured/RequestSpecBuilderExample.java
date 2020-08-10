/**
 * @author Deepak Rai
 */
package com.qa.restAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestSpecBuilderExample {

	/**
	 * {@summary Create RequestSpecification using RequestSpecificBuilder}
	 * 
	 * @param
	 * @return
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
//		System.out.println(response1.asString());

		// We can also pass RequestSpecification reference variable in overloaded
		// given() method
		Response response2 = RestAssured.given(reqSpec).get();
		System.out.println(response2.asString());

		// We can also pass RequestSpecification reference variable in overloaded
		// given() method
		Response response3 = RestAssured.given().spec(reqSpec).get();
		System.out.println(response3.asString());
	}
}
