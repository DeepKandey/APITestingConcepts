package com.qa.restAssured;

import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import com.qa.constants.CommonConstants;
import com.qa.util.RestCommonMethods;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class RestAssuredGet_ListUsers {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void getlistOfUsersDetails() throws ParseException {

		Response restResponse = RestCommonMethods.getAPIRequest(CommonConstants.REQRES_ENDPOINT_URI,
				CommonConstants.USERS_LIST);

		// Status Line
		System.out.println("Status Line--> " + restResponse.getStatusLine());
		// Status Code
		System.out.println("Status Code--> " + restResponse.getStatusCode());
		// Headers
		Headers allHeaders = restResponse.getHeaders();
		/* System.out.println("Headers-->" + allHeaders); */
		for (Header header : allHeaders) {
			System.out.println("Key:" + header.getName() + " Value: " + header.getValue());
		}
		// Response Body
		restResponse.body().prettyPrint();

		// First get the JsonPath object instance from the Response interface
		JsonPath jsonResponseBody = restResponse.body().jsonPath();
		// Print per page count using in built function of Rest Assured
		int per_Page_Count = jsonResponseBody.get("per_page");
		System.out.println("Per Page Count using REST Assured--> " + per_Page_Count);

		// With JSON Simple API
		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(restResponse.body().asString());
		// Printing per page count using JSON Simple API
		Long per_Page_JSONSimple = (Long) jsonObject.get("per_page");
		System.out.println("Per Page count using JSON Simple--> " + per_Page_JSONSimple);

		// Print JSON Array using JSON Simple
		JSONArray dataArray = (JSONArray) jsonObject.get("data");

		Iterator<Object> dataAsIterator = dataArray.iterator();
		while (dataAsIterator.hasNext()) {

			Iterator dataValuesAsEntrySet = ((Map) dataAsIterator.next()).entrySet().iterator();

			while (dataValuesAsEntrySet.hasNext()) {
				Map.Entry recordAsEntrySet = (Map.Entry) dataValuesAsEntrySet.next();
				System.out.print(recordAsEntrySet.getKey() + " = " + recordAsEntrySet.getValue() + ", ");
			}
			System.out.println();
		}
	}
}