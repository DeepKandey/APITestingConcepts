package com.qa.restAssured;

import org.testng.annotations.Test;

import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.*;

public class BDD_GetWeatherDetails {

	@Test
	public void getWeatherDetailsWithSingleParam() {
		given().log().all().params("q", "Pune", "APPID", "e049e82235e635d751effb42b7a7adae").when().log().all()
				.get("http://api.openweathermap.org/data/2.5/weather").then().log().all()
				.body("sys.country", equalTo("IN"));

	}

	@Test
	public void getWeatherDetailsWithMultiParam() {
		given().log().all().params("q", "Pune", "mode", "xml", "APPID", "e049e82235e635d751effb42b7a7adae").when().log()
				.all().get("http://api.openweathermap.org/data/2.5/weather").then().log().all()
				.body("current.city.country", equalTo("IN"));

	}
}