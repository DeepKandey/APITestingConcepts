package com.qa.restAssured;

import java.util.HashMap;
import java.util.Map.Entry;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssuredGet_CountryDetails {

	@SuppressWarnings("unchecked")
	@Test
	public void getCountryDetails() throws ParseException {
		RestAssured.baseURI = "https://restcountries.eu";
		RequestSpecification restClient = RestAssured.given();
		restClient.headers("content-type", "application/json");
		Response restResponse = restClient.request(Method.GET, "/rest/v2/capital/Washington");

		String statusLine = restResponse.getStatusLine();
		System.out.println("Status Line-->" + statusLine);

		Headers headers = restResponse.headers();
		System.out.println("Headers in Response-->" + System.lineSeparator() + headers);

		String restResponseBody = restResponse.getBody().asString();
		System.out.println("Response Body -->" + System.lineSeparator() + restResponseBody);

		String jsonResponseBody = restResponseBody.substring(1, restResponseBody.length());
		jsonResponseBody = jsonResponseBody.substring(0, jsonResponseBody.length() - 1);
		System.out.println("JSON Response-->" + System.lineSeparator() + jsonResponseBody);

		// JSON Simple
		JSONParser parser = new JSONParser();
		JSONObject jsonString = (JSONObject) parser.parse(jsonResponseBody);

		// Printing JSON Object
		String countryName = jsonString.get("name").toString();
		System.out.println("Name of the Country-->" + countryName);

		//
		String translations = jsonString.get("translations").toString();
		System.out.println("Translations-->" + translations);
		String[] translationArray = translations.split(",");
		for (int i = 0; i < translationArray.length; i++) {
			System.out.println(translationArray[i]);
		}

		// Printing JSON array with only values
		JSONArray countrySpelling = (JSONArray) jsonString.get("altSpellings");
		String firstSpelling = countrySpelling.get(0).toString();
		System.out.println("First Spelling -->" + firstSpelling);

		// Country Domain Array
		JSONArray countryDomain = (JSONArray) jsonString.get("topLevelDomain");
		String domainValue = countryDomain.get(0).toString();
		System.out.println("Domain of the Country -->" + domainValue);

		// Border Values Array
		JSONArray borderValues = (JSONArray) jsonString.get("borders");
		String firstBorderValue = borderValues.get(0).toString();
		System.out.println("Borders of the country-->" + borderValues);
		System.out.println("First Value of border of the Country -->" + firstBorderValue);

		// Printing Array with Name - Value pairs
		JSONArray Currencies = (JSONArray) jsonString.get("currencies");
		System.out.println("Currencies Array-->" + Currencies);
		for (int i = 0; i < Currencies.size(); i++) {
			HashMap<String, String> currencyMap = (HashMap<String, String>) Currencies.get(i);
			for (Entry<String, String> currencyValuePair : currencyMap.entrySet()) {
				System.out.println(currencyValuePair.getKey() + " : " + currencyValuePair.getValue());
			}
		}
	}
}