package com.im.client.rest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

public class ApacheHttpClientUtil {

	public static HttpResponse sendGetRequest(String url) throws Exception {
		DefaultHttpClient httpClient = getHttpClient();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/JSON");
		HttpResponse response = httpClient.execute(getRequest);
		httpClient.getConnectionManager().shutdown();
		return response;
	}
	public static HttpResponse sendPOSTRequest(String url, StringEntity requestMsg) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(url);
		postRequest.setEntity(requestMsg);
		HttpResponse response = httpClient.execute(postRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
//		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
//		String output;
//		System.out.println("Output from Server .... \n");
//		while ((output = br.readLine()) != null) {
//			System.out.println(output);
//		}
//
		httpClient.getConnectionManager().shutdown();
		return response;
	}

	public static HttpResponse sendPUTRequest(String url, StringEntity requestMsg) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(url);
		putRequest.setEntity(requestMsg);
		HttpResponse response = httpClient.execute(putRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
		httpClient.getConnectionManager().shutdown();
		return response;
		
	}
	public static HttpResponse sendPUTRequestActivate(String url) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(url);
		//putRequest.setEntity(requestMsg);
		HttpResponse response = httpClient.execute(putRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
		httpClient.getConnectionManager().shutdown();
		return response;
		
	}

	public static HttpResponse sendDELETERequest(String url) {
		DefaultHttpClient httpClient = getHttpClient();
		return null;
	}

	public static DefaultHttpClient getHttpClient() {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		return httpClient;
	}

	public static boolean validateSuccessStatus(HttpResponse response) {
		return (response.getStatusLine().getStatusCode() != 200);
		// TODO Auto-generated method stub

	}

}
