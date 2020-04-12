package com.qa.util;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestCommonMethods {

	private RestCommonMethods() {

	}

	public static Response deleteAPIRequest(String endpointURI, String deleteURL) {

		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		return restClient.request(Method.DELETE, deleteURL);
	}

	public static Response getAPIRequest(String endpointURI, String getURL) {

		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		restClient.headers("content-type", "application/json");
		return restClient.request(Method.GET, getURL);
	}
} // End of class RestCommonMethods