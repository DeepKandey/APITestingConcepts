package com.qa.util;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestCommonMethods {

	public static Response deleteAPIRequest(String endpointURI, String URL) {

		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		Response restResponse = restClient.request(Method.DELETE, "/api/users/2");
		return restResponse;
	}

	public static Response getAPIRequest(String endpointURI, String URL) {

		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		restClient.headers("content-type", "application/json");
		Response restResponse = restClient.request(Method.GET, URL);
		return restResponse;
	}
}