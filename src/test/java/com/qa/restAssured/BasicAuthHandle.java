/**
 * @author Deepak Rai
 *
 */
package com.qa.restAssured;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BasicAuthHandle {

	/**
	 * 
	 * {@summary method to handle basic authentication using rest assured}
	 * 
	 * @param
	 * @return
	 * @author deepak
	 */
	@Test
	public void basicAuthRestAssuredHandle() {

		// Set the base URI
		RestAssured.baseURI = "http://the-internet.herokuapp.com";
		// Get the request specification
		RequestSpecification request = RestAssured.given().log().all();
		// Set the basic authentication - username and password
		request.auth().basic("admin", "admin");
		// Get the response
		Response response = request.get("/basic_auth");

		// Print the response body
		response.getBody().prettyPrint();
	}
}