package com.qaninjas.api.utils;

import java.util.HashMap;;

public class SeeTestManageDevicesAPI {

	private static HashMap<String, HashMap<String, String>> seeTest = new HashMap<String, HashMap<String, String>>();
	
	public String getDeviceProperty(String deviceName, String deviceProperty) {
		String devicePropertyAPI = seeTest.get(deviceName).get(deviceProperty);
		System.out.println("devicePropertyAPI" + devicePropertyAPI);
		return devicePropertyAPI;
	}

	public void reserveSingleDevice(String deviceName, String startTime, String endTime) {
		
	}

}
