package com.qa.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.pojo.UpdateUserDetails;

public class PUT_UpdateUser {

	@Test
	public void updateUserTest() throws ClientProtocolException, IOException {
		CloseableHttpClient httpRequest = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut("https://reqres.in/api/users/2");

		UpdateUserDetails updateUserRequest = new UpdateUserDetails("Pankaj", "Testing");

		ObjectMapper mapper = new ObjectMapper();
		String jsonStringRequest = mapper.writeValueAsString(updateUserRequest);
		System.out.println(jsonStringRequest);

		// Using HashMap to add header to the PUT request
		HashMap<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("Content-type", "application/json");

		for (Entry<String, String> header : headerMap.entrySet()) {
			httpPut.setHeader(header.getKey(), header.getValue());
		}

		// Direct way of adding header :-
		// httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		HttpEntity entity = new StringEntity(jsonStringRequest);
		httpPut.setEntity(entity);

		CloseableHttpResponse httpResponse = httpRequest.execute(httpPut);

		System.out.println("Status Line Code-->" + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());

		String responseBody = null;
		HttpEntity responseEntity = httpResponse.getEntity();
		if (responseEntity != null) {
			responseBody = EntityUtils.toString(responseEntity);
		}
		System.out.println(responseBody);

		UpdateUserDetails updateUserResponse = mapper.readValue(responseBody, UpdateUserDetails.class);
		System.out.println(updateUserResponse.getJob());
		Assert.assertNotNull(updateUserResponse.getUpdatedAt()); //validating if UpdatedAt is not null
	}
}
