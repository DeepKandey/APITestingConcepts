package com.qa.http;

import java.io.IOException;

import java.util.Iterator;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

public class HttpGet_SingleUser {

	@Test
	public void getSingleUserDetails() throws ClientProtocolException, IOException, ParseException {
		CloseableHttpClient httpclient = HttpClients.createDefault(); // create http connection
		org.apache.http.client.methods.HttpGet httpGet = new org.apache.http.client.methods.HttpGet(
				"https://reqres.in/api/users/2"); // create httpRequest
		httpGet.addHeader("Content-Type", "application/json");

		CloseableHttpResponse httpResponse = httpclient.execute(httpGet); // executing the request

		String jsonString = null;

		// For Response Body
		HttpEntity responseEntity = httpResponse.getEntity();
		if (responseEntity != null) {
			jsonString = EntityUtils.toString(responseEntity, "UTF-8");
		}

		JSONParser parser = new JSONParser();
		JSONObject jsonObject = (JSONObject) parser.parse(jsonString);

		// Printing JSON data response having data set in a single JSON object
		Map<String,String> userData = (Map<String,String>) jsonObject.get("data");

		Iterator<Map.Entry<String, String>> userDetail = userData.entrySet().iterator();
		while (userDetail.hasNext()) {
			Map.Entry detail = userDetail.next();
			System.out.println(detail.getKey() + " = " + detail.getValue());
		}
	}
}