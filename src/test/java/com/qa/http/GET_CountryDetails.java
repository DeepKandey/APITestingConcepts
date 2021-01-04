package com.qa.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GET_CountryDetails {

  @Test
  public void getCountryDetails() throws ClientProtocolException, IOException, ParseException {
    CloseableHttpClient httpClient = HttpClients.createDefault();
    HttpGet httpGet = new HttpGet("https://restcountries.eu/rest/v2/capital/Washington");
    httpGet.addHeader("content-type", "application/json");
    CloseableHttpResponse httpRespone = httpClient.execute(httpGet);

    // Status Line Code
    StatusLine statusLine = httpRespone.getStatusLine();
    System.out.println("Status Line-->" + statusLine);
    Assert.assertEquals(statusLine.getStatusCode(), 200, "Status is not 200");

    // Header
    Header[] allHeader = httpRespone.getAllHeaders();
    System.out.println("----------Headers-----------");
    for (Header header : allHeader) {
      System.out.println(header.getName() + " : " + header.getValue());
    }

    HttpEntity httpEntity = httpRespone.getEntity();
    String httpResponseBody = EntityUtils.toString(httpEntity, "UTF-8");
    System.out.println("Http Body Response -->" + System.lineSeparator() + httpResponseBody);

    String jsonResponseBody = httpResponseBody.substring(1, httpResponseBody.length());
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
