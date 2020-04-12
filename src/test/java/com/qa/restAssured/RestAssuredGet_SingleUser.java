package com.qa.restAssured;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.qa.constants.CommonAPIConstants;
import com.qa.util.RestCommonMethods;

import io.restassured.http.Header;
import io.restassured.response.Response;

public class RestAssuredGet_SingleUser {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void getSingleUserDetails() throws ParseException {

		// Headers details
		Header h1 = new Header("content-type", "application/json");
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(h1);

		Response restResponse = RestCommonMethods.getAPIRequest(CommonAPIConstants.REQRES_ENDPOINT_URI,
				CommonAPIConstants.SINGLE_USERS_URL, headerList);

		// 1. Response Body as String
		System.out.println("Response Body in json:-");
		restResponse.body().prettyPrint();

		// 6. Parsing data in Response Body
		JSONParser parser = new JSONParser();
		JSONObject jsonStringResponse = (JSONObject) parser.parse(restResponse.getBody().asString());

		// 7. Iterating through the user Details
		HashMap<String, String> singleUserData = (HashMap<String, String>) jsonStringResponse.get("data");
		for (Entry userDetailsEntry : singleUserData.entrySet()) {
			System.out.println(userDetailsEntry.getKey() + " = " + userDetailsEntry.getValue());
		}
	} // end of method getSingleUSerDetails
} // end of class RestAssured_SingleUser