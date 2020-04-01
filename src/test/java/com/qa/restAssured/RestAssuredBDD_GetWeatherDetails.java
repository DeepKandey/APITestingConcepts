package com.qa.restAssured;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class RestAssuredBDD_GetWeatherDetails {

	@Test
	public void getWeatherDetailsWithSingleParam() {
		given().
		        params("q", "Pune", "APPID","e049e82235e635d751effb42b7a7adae").
		when().
		        get("http://api.openweathermap.org/data/2.5/weather").
		then().
		       body("sys.country", equalTo("IN"));
		
	}
	
	@Test
	public void getWeatherDetailsWithMultiParam() {
		given().
		        params("q", "Pune", "mode", "xml", "APPID","e049e82235e635d751effb42b7a7adae").
		when().
		        get("http://api.openweathermap.org/data/2.5/weather").
		then().
		       body("current.city.country", equalTo("IN"));
		
	}
}