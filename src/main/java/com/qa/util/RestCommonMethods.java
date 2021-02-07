package com.qa.util;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;

public class RestCommonMethods {

  private RestCommonMethods() {}

  /**
   * {summary method to execute get API request}
   *
   * @param baseURI,serviceURL,headersList Endpoint and Service URLs and headers
   * @return Response
   * @author deepak rai
   */
  public static Response getAPIRequest(
      String baseURI, String serviceURL, List<Header> headersList) {

    RestAssured.baseURI = baseURI;
    RequestSpecification restClient = RestAssured.given();
    Headers headers = new Headers(headersList);
    restClient.headers(headers);
    return restClient.request(Method.GET, serviceURL);
  }

  /**
   * {summary method to execute get API request}
   *
   * @param baseURI,serviceURL,headersList,username,password Endpoint,Service URLs and
   *     headers
   * @return Response
   * @author deepak rai
   */
  public static Response getAPIRequestWithBasicAuthentication(
      String baseURI,
      String serviceURL,
      List<Header> headersList,
      String username,
      String password) {

    RestAssured.baseURI = baseURI;
    RequestSpecification restClient = RestAssured.given();
    restClient.auth().basic(username, password);
    Headers headers = new Headers(headersList);
    restClient.headers(headers);
    return restClient.request(Method.GET, serviceURL);
  }

  /**
   * {summary method to execute post API request}
   *
   * @param baseURI,serviceURL,headersList Endpoint and Service URLs,headers and payload
   * @return Response
   * @author deepak rai
   */
  public static Response postAPIRequest(
      String baseURI, String serviceURL, List<Header> headersList, String jsonRequestBody) {

    RestAssured.baseURI = baseURI;
    RequestSpecification restClient = RestAssured.given();
    restClient.headers("content-type", "application/json");
    Headers headers = new Headers(headersList);
    restClient.headers(headers);
    restClient.body(jsonRequestBody);
    return restClient.request(Method.POST, serviceURL);
  }

  /**
   * {summary method to execute put API request}
   *
   * @param baseURI,serviceURL,headersList PUT API parameters
   * @return Response
   * @author deepak rai
   */
  public static Response putAPIRequest(
      String baseURI, String serviceURL, List<Header> headersList, String jsonRequestBody) {

    RestAssured.baseURI = baseURI;
    RequestSpecification restClient = RestAssured.given();
    restClient.headers("content-type", "application/json");
    Headers headers = new Headers(headersList);
    restClient.headers(headers);
    restClient.body(jsonRequestBody);
    return restClient.request(Method.PUT, serviceURL);
  }

  /**
   * {summary method to execute delete API request}
   *
   * @param baseURI,serviceURL Endpoint and Service URLs
   * @return Response
   * @author deepak rai
   */
  public static Response deleteAPIRequest(String baseURI, String serviceURL) {

    RestAssured.baseURI = baseURI;
    RequestSpecification restClient = RestAssured.given();
    return restClient.request(Method.DELETE, serviceURL);
  }
} // End of class RestCommonMethods
