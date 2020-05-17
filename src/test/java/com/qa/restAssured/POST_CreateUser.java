package com.qa.restAssured;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.constants.CommonAPIConstants;
import com.qa.pojo.CreateUserDetails;
import com.qa.util.RestCommonMethods;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class POST_CreateUser {

	@Test
	public void createUser() throws IOException {

		// Declare Variables
		String userName = "Deepak";
		String job = "Cleaning";

		// Serialize POJO into JSON String | marshalling
		CreateUserDetails userRequest = new CreateUserDetails(userName, job);
		
		ObjectMapper mapper = new ObjectMapper();
		String jsonStringRequestBody = mapper.writeValueAsString(userRequest);
		System.out.println("JSON string Request Payload-->" + jsonStringRequestBody);

		// Headers details
		Header h1 = new Header("content-type", "application/json");
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(h1);

		// Rest Response
		Response restResponse = RestCommonMethods.postAPIRequest(CommonAPIConstants.REQRES_ENDPOINT_URI,
				CommonAPIConstants.USERS_LIST_URL, headerList, jsonStringRequestBody);

		// Status Code in response
		System.out.println("Status code--> " + restResponse.getStatusCode());

		// Response Body
		String jsonStringResponseBody = restResponse.getBody().asString();
		System.out.println("JSON String Response Payload--> " + jsonStringResponseBody);

		// Deserialization from JsonString to POJO | unmarshalling
		CreateUserDetails userResponse = mapper.readValue(jsonStringResponseBody, CreateUserDetails.class);
		System.out.println("User id--> " + userResponse.getId());

		// Comparing Name passed in Request and Name received in Response
		Assert.assertEquals(userRequest.getName(), userResponse.getName(), "Name does not match");
	}
} // end of class RestAssuredPost_CreateUser