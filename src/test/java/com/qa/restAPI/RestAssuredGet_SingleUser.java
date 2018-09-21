package com.qa.restAPI;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredGet_SingleUser {

	@Test
	public void getSingleUserDetails() {

		// 1.Setting base URL
		RestAssured.baseURI = "https://reqres.in/api";
		// 2.Creating Rest client
		RequestSpecification restClient = RestAssured.given();
		// 3.Executing Get request
		Response restResponse = restClient.request(Method.GET, "/2");

		// 4. Adding header to restClient
		Header h1 = new Header("content-type", "application/json");
		List<Header> headerList = new ArrayList<Header>();
		headerList.add(h1);

		Headers headers = new Headers(headerList);

		restClient.headers(headers);

		String restResponseAsString = restResponse.getBody().asString();
		System.out.println("Rest Response in Body-->" + restResponseAsString);
	}
}
