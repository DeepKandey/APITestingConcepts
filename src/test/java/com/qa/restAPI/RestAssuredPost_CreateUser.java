package com.qa.restAPI;

import java.io.IOException;

import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.parameters.CreateUser;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class RestAssuredPost_CreateUser {

	@Test
	public void createUser() throws IOException {

		// 1. Base URI
		RestAssured.baseURI = "https://reqres.in";
		// 2. CretingRest Client
		RequestSpecification restClient = RestAssured.given();
		// 3. Creating object of User Class
		CreateUser userRequest = new CreateUser("Deepak", "Cleaning");
		// 4. Object Mapper to serialize Java Objects into JSON String
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(userRequest);
		// 5. Passing JSON Payload in restClient
		restClient.body(jsonString);

		// 6. Rest Response
		Response restResponse = restClient.request(Method.POST, "/api/users");
		// 7. Status Code in response
		System.out.println(restResponse.getStatusCode());
		// 8. Getting jsonStringResponse
		String jsonStringResponse = restResponse.body().asString();
		System.out.println(jsonStringResponse);

		// 9. Deserialization fron JsonString to POJO
		CreateUser userResponse = mapper.readValue(jsonStringResponse, CreateUser.class);
		System.out.println("User id-->" + userResponse.getId());

		Assert.assertEquals(userRequest.getName(), userResponse.getName(), "Name does not match");
	}
}
