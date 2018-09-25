package com.qa.restAPI;

import java.util.HashMap;
import java.util.Map.Entry;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.gson.Gson;
import com.qa.parameters.UpdateUser;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredPut_UpdateUser {

	@Test
	public void updateUserTest() {
		RestAssured.baseURI = "https://reqres.in/api/users/2";
		RequestSpecification restRequest = RestAssured.given();

		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("content-type", "application/json");

		for (Entry<String, String> entry : headerMap.entrySet()) {
			restRequest.header(entry.getKey(), entry.getValue());
		}

		UpdateUser updateUserRequest = new UpdateUser("Deepak", "Automation");

		Gson gson = new Gson();
		String jsonStringRequest = gson.toJson(updateUserRequest);
		System.out.println("Json String Request Payload-->" + jsonStringRequest);

		restRequest.body(jsonStringRequest);

		Response restResponse = restRequest.request(Method.PUT);

		int StatusCode = restResponse.getStatusCode();
		System.out.println("Status Code in Response-->" + StatusCode);

		String responseBody = restResponse.getBody().asString();
		System.out.println("JSON String Response Body-->" + responseBody);

		// Unmarshelling
		UpdateUser updateUserResponse = gson.fromJson(responseBody, UpdateUser.class);
		
		// Validating Name and updatedAt fields
		Assert.assertEquals(updateUserResponse.getName(), updateUserRequest.getName());
		Assert.assertNotNull(updateUserResponse.getUpdatedAt()); //UpdatedAt should not be null
	}
}
