package com.qa.restAssured;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.qa.constants.CommonAPIConstants;
import com.qa.util.RestCommonMethods;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import static com.qa.util.LoggerUtil.log;

public class GET_CountryDetails {

  @Test
  public void getCountryDetails() throws ParseException {

    // Headers details
    Header h1 = new Header("content-type", "application/json");
    List<Header> headerList = new ArrayList<>();
    headerList.add(h1);

    Response restResponse =
        RestCommonMethods.getAPIRequest(
            CommonAPIConstants.RESTCOUNTRIES_ENDPOINT_URL,
            CommonAPIConstants.WASHINGTON_COUNTRY_URL,
            headerList);

    // Status Line
    log("Status Line--> " + restResponse.getStatusLine());
    // Headers
    log("Headers in Response-->" + System.lineSeparator() + restResponse.headers());

    // Response Body
    log("Response body in json-->");
    restResponse.body().prettyPrint();

    String jsonResponseBody =
        restResponse.body().asString().substring(1);
    jsonResponseBody = jsonResponseBody.substring(0, jsonResponseBody.length() - 1);
    log("JSON Response-->" + System.lineSeparator() + jsonResponseBody);

    // JSON Simple
    JsonFactory factory = new JsonFactory();
    factory.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
    JSONParser parser = new JSONParser();
    JSONObject jsonString = (JSONObject) parser.parse(jsonResponseBody.trim());

    // Printing JSON Object
    String countryName = jsonString.get("name").toString();
    log("Name of the Country--> " + countryName);

    // Translations
    String translations = jsonString.get("translations").toString();
    log("Translations--> " + translations);
    String[] translationArray = translations.split(",");
    for (String s : translationArray) {
      log(s);
    }

    // Printing JSON array with only values
    JSONArray countrySpelling = (JSONArray) jsonString.get("altSpellings");
    String firstSpelling = countrySpelling.get(0).toString();
    log("First Spelling --> " + firstSpelling);

    // Country Domain Array
    JSONArray countryDomain = (JSONArray) jsonString.get("topLevelDomain");
    String domainValue = countryDomain.get(0).toString();
    log("Domain of the Country --> " + domainValue);

    // Border Values Array
    JSONArray borderValues = (JSONArray) jsonString.get("borders");
    String firstBorderValue = borderValues.get(0).toString();
    log("Borders of the country--> " + borderValues);
    log("First Value of border of the Country --> " + firstBorderValue);

    // Printing Array with Name - Value pairs
    JSONArray Currencies = (JSONArray) jsonString.get("currencies");
    log("Currencies Array--> " + Currencies);
    for (Object currency : Currencies) {
      HashMap<String, String> currencyMap = (HashMap<String, String>) currency;
      for (Entry<String, String> currencyValuePair : currencyMap.entrySet()) {
        log(currencyValuePair.getKey() + " : " + currencyValuePair.getValue());
      }
    }
  } // end of method getCountryDetails
} // end of class RestAssuredGet_CountryDetails
