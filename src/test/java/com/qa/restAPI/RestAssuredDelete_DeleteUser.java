package com.qa.restAPI;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredDelete_DeleteUser {

	@Test
	public void deleteUserTest() {
		RestAssured.baseURI = "https://reqres.in";
		RequestSpecification restClient = RestAssured.given();

		Response restResponse = restClient.request(Method.DELETE, "/api/users/2");

		int statusCode = restResponse.getStatusCode();
		Assert.assertEquals(statusCode, 204);

		String responseBody = restResponse.getBody().asString();
		if (statusCode == 204) {
			Assert.assertTrue(responseBody.isEmpty()); // Validating if the response Body is empty
		}

		Headers headers = restResponse.headers();
		System.out.println("Headers in Response Body-->");
		for (Header header : headers) {
			System.out.println(header.getName() + " = " + header.getValue());
		}
	}
}
