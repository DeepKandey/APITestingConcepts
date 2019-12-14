package com.qa.restAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredGet_SingleUser {

	@Test
	public void getSingleUserDetails() throws ParseException {

		// 1.Setting base URL
		RestAssured.baseURI = "https://reqres.in/api";
		// 2.Creating Rest client
		RequestSpecification restClient = RestAssured.given();
		// 3.Executing Get request
		Response restResponse = restClient.request(Method.GET, "/users/2");

		// 4. Adding header to restClient
		Header h1 = new Header("content-type", "application/json");
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(h1);
		Headers headers = new Headers(headerList);
		restClient.headers(headers);

		// 5. Response Body as String
		String restResponseAsString = restResponse.getBody().asString();
		System.out.println("Rest Response in Body-->" + restResponseAsString);

		// 6. Parsing data in Response Body
		JSONParser parser = new JSONParser();
		JSONObject jsonStringResponse = (JSONObject) parser.parse(restResponseAsString);

		// 7. Iterating through the user Details
		HashMap<String, String> singleUserData = (HashMap<String, String>) jsonStringResponse.get("data");
		for (Entry userDetailsEntry : singleUserData.entrySet()) {
			System.out.println("Key: " + userDetailsEntry.getKey() + " ,Value: " + userDetailsEntry.getValue());
		}
	}
}