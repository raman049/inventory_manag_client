package com.in.cleint.cleintClass;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.eclipse.persistence.internal.oxm.Marshaller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.im.client.entity.VendorVO;
import com.im.client.rest.ApacheHttpClientUtil;
import com.im.client.rest.JSONUtil;

public class VendorVOClient {

	public static final String VENDOR_SERVICE_URL = "http://localhost:8090/vendor";

	public VendorVO findByID(long id) throws Exception {
		HttpResponse response = ApacheHttpClientUtil.sendGetRequest(VENDOR_SERVICE_URL + "/id/" + id);
		VendorVO vendorVO = JSONUtil.getEntityFromJsonString(response, VendorVO.class);
		return vendorVO;
	}

	public VendorVO findByCode(String code) throws Exception {
		HttpResponse response = ApacheHttpClientUtil.sendGetRequest(VENDOR_SERVICE_URL + "/code/" + code);
		VendorVO vendorVO = JSONUtil.getEntityFromJsonString(response, VendorVO.class);
		return vendorVO;
	}

	public List<VendorVO> findActiveVendor() throws Exception {
		HttpResponse response = ApacheHttpClientUtil.sendGetRequest(VENDOR_SERVICE_URL + "/active");
		List<VendorVO> vendorVO = (List<VendorVO>) JSONUtil.getEntityFromJsonStringList(response);
		return vendorVO;
	}

	public static void dataEntry(VendorVO vo) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(VENDOR_SERVICE_URL);
	
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(vo);
		jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(vo);
		System.out.println(jsonInString);
		StringEntity input = new StringEntity(jsonInString.toString());
		input.setContentType("application/json");
		postRequest.setEntity(input);

		HttpResponse response = httpClient.execute(postRequest);
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String output;
		System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
		}

		httpClient.getConnectionManager().shutdown();

	}
}
