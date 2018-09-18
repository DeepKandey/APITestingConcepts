package com.qa.restAPI;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredGet_ListUsers {

	@Test
	public void getlistOfUsersDetails() throws ParseException {
		RestAssured.baseURI = "https://reqres.in/api/users";
		RequestSpecification restClient = RestAssured.given();
		Response restResponse = restClient.request(Method.GET);

		// For status Line
		String statusLine = restResponse.getStatusLine();
		System.out.println("Status Line-->" + statusLine);

		// For Status Code
		int statusCode = restResponse.getStatusCode();
		System.out.println("Status Code-->" + statusCode);

		// For Headers
		Headers allHeaders = restResponse.getHeaders();
		// System.out.println("Headers-->" + allHeaders);

		for (Header header : allHeaders) {
			System.out.println("Key:" + header.getName() + " Value: " + header.getValue());
		}

		// For Response Body
		String responseBody = restResponse.body().asString();
		System.out.println("Response Body from REST Assured API-->" + responseBody);

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonPathEvaluator = restResponse.jsonPath();

		// Printing per page count using in built function of Rest Assured
		int per_Page_Count = jsonPathEvaluator.get("per_page");
		System.out.println("Per Page Count from REST Assured API-->" + per_Page_Count);

		// With JSON Simple API
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(responseBody);

		// Printing per page count using JSON Simple API
		Long per_Page_JSONSimple = (Long) jsonObject.get("per_page");
		System.out.println("Per Page count from JSON Simple API-->" + per_Page_JSONSimple);

		// Printing JSON Array using JSON Simple API
		JSONArray dataArray = (JSONArray) jsonObject.get("data");
		Iterator dataIterator = dataArray.iterator();

		while (dataIterator.hasNext()) {
			Iterator keyValueSet = ((Map) dataIterator.next()).entrySet().iterator();
			while (keyValueSet.hasNext()) {
				Map.Entry keyValuePair = (Entry) keyValueSet.next();
				System.out.println("Key: " + keyValuePair.getKey() + ", Value:" + keyValuePair.getValue());
			}
		}
	}
}
