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

	/**
	 * 
		 * {@summary method to execute get API request}
		 * @param endpointURI, serviceURL, headersList
		 * @return Response
		 * @author deepak rai
	 */
	public static Response getAPIRequest(String endpointURL, String serviceURL, List<Header> headersList) {
		
		RestAssured.baseURI = endpointURL;
		RequestSpecification restClient = RestAssured.given();
		Headers headers = new Headers(headersList);
		restClient.headers(headers);
		return restClient.request(Method.GET, serviceURL);
		
	}

	/**
	 * 
		 * {@summary method to execute post API request}
		 * @param endpointURI, serviceURL, headersList
		 * @return Response
		 * @author deepak rai
	 */
	public static Response postAPIRequest(String endpointURL, String serviceURL, List<Header> headersList,
			String jsonRequestBody) {
		
		RestAssured.baseURI = endpointURL;
		RequestSpecification restClient = RestAssured.given();
		restClient.headers("content-type", "application/json");
		Headers headers = new Headers(headersList);
		restClient.headers(headers);
		restClient.body(jsonRequestBody);
		return restClient.request(Method.POST, serviceURL);
		
	}

	/**
	 * 
		 * {@summary method to execute put API request}
		 * @param endpointURI, serviceURL, headersList
		 * @return Response
		 * @author deepak rai
	 */
	public static Response putAPIRequest(String endpointURL, String serviceURL, List<Header> headersList,
			String jsonRequestBody) {
		
		RestAssured.baseURI = endpointURL;
		RequestSpecification restClient = RestAssured.given();
		restClient.headers("content-type", "application/json");
		Headers headers = new Headers(headersList);
		restClient.headers(headers);
		restClient.body(jsonRequestBody);
		return restClient.request(Method.PUT, serviceURL);
		
	}

	/**
	 * 
		 * {@summary method to execute delete API request}
		 * @param endpointURI, serviceURL
		 * @return Response
		 * @author deepak rai
	 */
	public static Response deleteAPIRequest(String endpointURL, String serviceURL) {
		
		RestAssured.baseURI = endpointURL;
		RequestSpecification restClient = RestAssured.given();
		return restClient.request(Method.DELETE, serviceURL);
		
	}

} // End of class RestCommonMethods