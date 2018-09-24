package com.qa.http;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
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
import com.qa.parameters.UpdateUser;

public class HttpPut_UpdateUser {

	@Test
	public void updateUserTest() throws ClientProtocolException, IOException {
		CloseableHttpClient httpRequest = HttpClients.createDefault();
		HttpPut httpPut = new HttpPut("https://reqres.in/api/users/2");

		UpdateUser updateUserRequest = new UpdateUser("Pankaj", "Testing");

		ObjectMapper mapper = new ObjectMapper();
		String jsonStringRequest = mapper.writeValueAsString(updateUserRequest);
		System.out.println(jsonStringRequest);

		// HttpEntity entity = new StringEntity(jsonStringRequest);
		httpPut.setEntity(new StringEntity(jsonStringRequest));
		httpPut.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		CloseableHttpResponse httpResponse = httpRequest.execute(httpPut);

		System.out.println("Status Line Code-->" + httpResponse.getStatusLine().getStatusCode());
		Assert.assertEquals(200, httpResponse.getStatusLine().getStatusCode());

		String responseBody = null;
		HttpEntity responseEntity = httpResponse.getEntity();
		if (responseEntity != null) {
			responseBody = EntityUtils.toString(responseEntity);
		}
		System.out.println(responseBody);

		UpdateUser updateUserResponse = mapper.readValue(responseBody, UpdateUser.class);
		// System.out.println(updateUserResponse.getJob());

	}
}
