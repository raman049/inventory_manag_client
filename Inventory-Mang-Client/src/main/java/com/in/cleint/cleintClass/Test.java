package com.in.cleint.cleintClass;
import java.util.List;

import com.im.client.entity.VendorStatus;
import com.im.client.entity.VendorVO;
import com.in.cleint.cleintClass.VendorVOClient;

public class Test {

	public static void main(String args[]) throws Exception {
		VendorVO vo = new VendorVO();
		VendorVOClient client = new VendorVOClient();
		vo = client.findByID(new Long(1095));
		System.out.println("findByID output "+vo);
		vo = client.findByCode("HK101");
		System.out.println("findByCode output "+vo);
		List<VendorVO> volist= client.findActiveVendor();
		System.out.println("findActiveVendor output "+volist);
		VendorVO vo2 = new VendorVO();
		vo2.setAddress1("Key West");
		vo2.setAddress2("blue sky drive");
		vo2.setCity("Key west");
		vo2.setEmail("cometoKey@west.com");
		vo2.setFax("494949");
		vo2.setPhone("394439");
		vo2.setState("Florida");
		vo2.setStatus(VendorStatus.active);
		vo2.setVendorName("West Supplier");
		vo2.setZip("90393");
		vo2.setVendorCode("KEYW");
		VendorVOClient.dataEntry(vo2);
	}
}
