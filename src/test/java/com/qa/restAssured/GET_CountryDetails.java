package com.qa.restAssured;

import com.qa.constants.CommonAPIConstants;
import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class GET_CountryDetails {

  @Test
  public void getCountryDetails() throws ParseException {

    // Headers details
    Header h1 = new Header("content-type", "application/json");
    List<Header> headerList = new ArrayList<Header>();
    headerList.add(h1);

    Response restResponse =
        RestCommonMethods.getAPIRequest(
            CommonAPIConstants.RESTCOUNTRIES_ENDPOINT_URL,
            CommonAPIConstants.WASHINGTON_COUNTRY_URL,
            headerList);

    // Status Line
    System.out.println("Status Line--> " + restResponse.getStatusLine());
    // Headers
    System.out.println("Headers in Response-->" + System.lineSeparator() + restResponse.headers());

    // Response Body
    System.out.println("Response body in json-->");
    restResponse.body().prettyPrint();

    String jsonResponseBody =
        restResponse.body().asString().substring(1, restResponse.body().asString().length());
    jsonResponseBody = jsonResponseBody.substring(0, jsonResponseBody.length() - 1);
    System.out.println("JSON Response-->" + System.lineSeparator() + jsonResponseBody);

    // JSON Simple
    JSONParser parser = new JSONParser();
    JSONObject jsonString = (JSONObject) parser.parse(jsonResponseBody);

    // Printing JSON Object
    String countryName = jsonString.get("name").toString();
    System.out.println("Name of the Country--> " + countryName);

    // Translations
    String translations = jsonString.get("translations").toString();
    System.out.println("Translations--> " + translations);
    String[] translationArray = translations.split(",");
    for (int i = 0; i < translationArray.length; i++) {
      System.out.println(translationArray[i]);
    }

    // Printing JSON array with only values
    JSONArray countrySpelling = (JSONArray) jsonString.get("altSpellings");
    String firstSpelling = countrySpelling.get(0).toString();
    System.out.println("First Spelling --> " + firstSpelling);

    // Country Domain Array
    JSONArray countryDomain = (JSONArray) jsonString.get("topLevelDomain");
    String domainValue = countryDomain.get(0).toString();
    System.out.println("Domain of the Country --> " + domainValue);

    // Border Values Array
    JSONArray borderValues = (JSONArray) jsonString.get("borders");
    String firstBorderValue = borderValues.get(0).toString();
    System.out.println("Borders of the country--> " + borderValues);
    System.out.println("First Value of border of the Country --> " + firstBorderValue);

    // Printing Array with Name - Value pairs
    JSONArray Currencies = (JSONArray) jsonString.get("currencies");
    System.out.println("Currencies Array--> " + Currencies);
    for (int i = 0; i < Currencies.size(); i++) {
      HashMap<String, String> currencyMap = (HashMap<String, String>) Currencies.get(i);
      for (Entry<String, String> currencyValuePair : currencyMap.entrySet()) {
        System.out.println(currencyValuePair.getKey() + " : " + currencyValuePair.getValue());
      }
    }
  } // end of method getCountryDetails
} // end of class RestAssuredGet_CountryDetails
