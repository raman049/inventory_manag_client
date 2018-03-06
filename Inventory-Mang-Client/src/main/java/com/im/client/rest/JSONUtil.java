package com.im.client.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.im.client.entity.VendorVO;

public class JSONUtil {

	public static VendorVO getEntityFromJsonString(HttpResponse response, Class<?> clz) throws IOException {
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String output;
		StringBuffer sb = new StringBuffer();
		// System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			// System.out.println(output);
			sb.append(output);
		}
		ObjectMapper mapper = new ObjectMapper();
		VendorVO vendorVO = mapper.readValue(sb.toString(), VendorVO.class);
		return vendorVO;
	}
	public String getJSONFromEntity(Object obj, Class<?> clz) throws Exception{
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = mapper.writeValueAsString(obj);
		return jsonInString;
	}

	public static List<VendorVO> getEntityFromJsonStringList(HttpResponse response) throws IOException {
		if (response.getStatusLine().getStatusCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));
		String output;
		StringBuffer sb = new StringBuffer();
		// System.out.println("Output from Server .... \n");
		while ((output = br.readLine()) != null) {
			System.out.println(output);
			sb.append(output);
		}
		ObjectMapper mapper = new ObjectMapper();
		List<VendorVO> vendorVO = mapper.readValue(sb.toString(), List.class);
		return vendorVO;
	}

	

}
