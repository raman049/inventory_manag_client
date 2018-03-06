package com.in.cleint.cleintClass;

import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;

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
		StringEntity input = JSONUtil.getJSONFromEntity(vo, VendorVO.class);
		HttpResponse response = ApacheHttpClientUtil.sendPOSTRequest(VENDOR_SERVICE_URL, input);
	}

	public static void updateData(VendorVO vo) throws Exception {
		StringEntity input = JSONUtil.getJSONFromEntity(vo, VendorVO.class);
		HttpResponse response = ApacheHttpClientUtil.sendPUTRequest(VENDOR_SERVICE_URL + "/update", input);
	}
	public static void activateById(Long id) throws Exception{
		//StringEntity input = JSONUtil.getJSONFromEntity(vo, VendorVO.class);
		HttpResponse response = ApacheHttpClientUtil.sendPUTRequestActivate(VENDOR_SERVICE_URL + "/activate/"+id);
	}
	public static void deactivateById(Long id) throws Exception{
		//StringEntity input = JSONUtil.getJSONFromEntity(vo, VendorVO.class);
		HttpResponse response = ApacheHttpClientUtil.sendPUTRequestActivate(VENDOR_SERVICE_URL + "/deactivate/"+id);
	}

}
