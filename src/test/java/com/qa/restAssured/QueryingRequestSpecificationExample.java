package com.qa.restAssured;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.Test;

public class QueryingRequestSpecificationExample {

  /**
   * {summary to query requestSpecification details}
   *
   * @author deepak rai
   */
  @Test
  public void queryRequestSpecification() {

    // dummy request details for example
    String JsonBody = "{\"firstName\": \"Deepak\"}";

    // create RequestSpecification using given
    RequestSpecification reqSpecification = RestAssured.given();
    // setting base URI
    reqSpecification
        .baseUri("https://restful-booker.herokuapp.com")
        // setting base path
        .basePath("/booking")
        .body(JsonBody)
        .header("header1", "headerValue1")
        .header("header2", "headerValue2");

    // Querying RequestSpecification
    // Use query() method of SpecificationQuerier class to query
    QueryableRequestSpecification queryRequest = SpecificationQuerier.query(reqSpecification);

    // get base URI
    String retrieveBaseURI = queryRequest.getBaseUri();
    System.out.println("Base URI is :" + retrieveBaseURI);

    // get Base Path
    String retrieveBasePath = queryRequest.getBasePath();
    System.out.println("Base Path is :" + retrieveBasePath);

    // get Body
    String retrieveBody = queryRequest.getBody();
    System.out.println("Body is :" + retrieveBody);

    // get port By default REST assured assumes host localhost and port 8080 when doing a request.
    int retrievePort = queryRequest.getPort();
    System.out.println("Default Port is :" + retrievePort);

    // get Header
    Headers allHeaders = queryRequest.getHeaders();
    System.out.println("Printing all Headers:");

    for (Header h : allHeaders) {
      System.out.println("Header name: " + h.getName() + " Header value: " + h.getValue());
    }
  }
}
