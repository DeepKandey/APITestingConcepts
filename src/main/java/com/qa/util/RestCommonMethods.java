package com.qa.util;

import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestCommonMethods {

	private RestCommonMethods() {

	}

	public static Response getAPIRequest(String endpointURI, String getURL, List<Header> headersList) {
		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		Headers headers = new Headers(headersList);
		restClient.headers(headers);
		return restClient.request(Method.GET, getURL);
	}

	public static Response postAPIRequest(String endpointURI, String getURL, List<Header> headersList,
			String jsonRequestBody) {
		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		restClient.headers("content-type", "application/json");
		Headers headers = new Headers(headersList);
		restClient.headers(headers);
		restClient.body(jsonRequestBody);
		return restClient.request(Method.POST, getURL);
	}

	public static Response putAPIRequest(String endpointURI, String getURL, List<Header> headersList,
			String jsonRequestBody) {
		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		restClient.headers("content-type", "application/json");
		Headers headers = new Headers(headersList);
		restClient.headers(headers);
		restClient.body(jsonRequestBody);
		return restClient.request(Method.PUT, getURL);
	}

	public static Response deleteAPIRequest(String endpointURI, String deleteURL) {
		RestAssured.baseURI = endpointURI;
		RequestSpecification restClient = RestAssured.given();
		return restClient.request(Method.DELETE, deleteURL);
	}

} // End of class RestCommonMethods