package com.qa.http;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DELETE_DeleteUser {

	@Test
	public void deleteUser() throws ClientProtocolException, IOException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpDelete httpDelete = new HttpDelete("https://reqres.in/api/users/2");
		httpDelete.addHeader("content-type", "application/json");

		CloseableHttpResponse httpResponse = httpClient.execute(httpDelete);

		int statuscode = httpResponse.getStatusLine().getStatusCode();
		Assert.assertEquals(statuscode, 204); // Validating status Code

		// Validating if entity is null
		HttpEntity entity = httpResponse.getEntity();
		if (statuscode == 204) {
			Assert.assertNull(entity);
		}

		Header[] headers = httpResponse.getAllHeaders();
		System.out.println("Headers in the Response-->");
		for (Header header : headers) {
			System.out.println(header.getName() + " = " + header.getValue());
		}
	}
}