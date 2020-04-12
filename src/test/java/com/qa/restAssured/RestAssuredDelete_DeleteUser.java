package com.qa.restAssured;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.constants.CommonAPIConstants;
import com.qa.util.RestCommonMethods;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class RestAssuredDelete_DeleteUser {

	@Test
	public void deleteUserTest() {
		Response restResponse = RestCommonMethods.deleteAPIRequest(CommonAPIConstants.REQRES_ENDPOINT_URI,
				CommonAPIConstants.SINGLE_USERS_URL);

		// Validate Status code
		int statusCode = restResponse.getStatusCode();
		Assert.assertEquals(statusCode, 204);

		// Validate response Body
		String responseBody = restResponse.getBody().asString();
		if (statusCode == 204) {
			Assert.assertTrue(responseBody.isEmpty()); // Validating if the response Body is empty
		}

		// Validate Headers
		Headers headers = restResponse.headers();
		System.out.println("Headers in Response Body-->");
		for (Header header : headers) {
			System.out.println(header.getName() + " = " + header.getValue());
		}
	} // end of method deleteUserTest
} // end of class RestAssuredDelete_DeleteUser