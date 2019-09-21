package com.qaninjas.api.utils;

import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.utility.DateTime;


public class SeeTestManageDevicesAPI {

	private static  Logger logger = Logger.getLogger(SeeTestManageDevicesAPI.class);
	//private DateTime getDate  = new DateTime();
	private String urlBase = FrameworkConstants.MOBILECONFIG.get("CLOUD_URL");
	private String user = FrameworkConstants.MOBILECONFIG.get("USERNAME");
	private String password = FrameworkConstants.MOBILECONFIG.get("PASSWORD");
	
	private HttpResponse<String> response;
	private String url = urlBase + "api/v1/devices";
	
	private static HashMap<String, HashMap<String, String>> seeTest = new HashMap<String, HashMap<String, String>>();
	
	
	public SeeTestManageDevicesAPI() {
		try {			
			response = Unirest.get(url).basicAuth(user, password).asString();
			logger.info("Json response: "  + response.getBody());
			getAllAvailableDevices();
		} catch (Exception e) {
			logger.error("Exception: "  + e.getMessage());
		}
	}
	
	private void getAllAvailableDevices() {
		try {	
			JSONObject jObject = new JSONObject(response.getBody());
			JSONArray data = jObject.getJSONArray("data");
			if(data != null){
				for(int i= 0; i< data.length(); i++){
					jsonToMap(data.get(i));
				}
			}
		} catch (Exception e) {
			logger.error("Exception: "  + e.getMessage());
		}
	}

	private void jsonToMap(Object object) throws JSONException{
		JSONObject jObject = new JSONObject(object.toString());
		Iterator<?> keys = jObject.keys();
		String count = null;
		HashMap<String, String> subMap = new HashMap<String, String>();
		while(keys.hasNext()) {
			String key = (String) keys.next();
			if(!key.substring(0,2).equalsIgnoreCase("is")) {
				String value = (String) jObject.getString(key);
				subMap.put(key, value);
				if(key.equalsIgnoreCase("deviceName")) {
					count = value;
				}
			}
		}
		seeTest.put(count, subMap);
	}

	public String getDeviceProperty(String deviceName, String deviceProperty) {
		String devicePropertyAPI = seeTest.get(deviceName).get(deviceProperty);
		System.out.println("devicePropertyAPI" + devicePropertyAPI);
		return devicePropertyAPI;
	}

	public void reserveSingleDevice(String deviceName, String startTime, String endTime) {
		String deviceId = getDeviceProperty(deviceName, "id");
		
		if(startTime.equals("now")) {
			startTime = DateTime.getCurrentTime("yyyy-MM-dd-HH-mm-ss");
		}		
		String availability = getDeviceProperty(deviceName, "displayStatus");		
		try {
			if(!availability.equalsIgnoreCase("In Use")) {
				String timestamp = DateTime.getCurrentTime("yyyy-MM-dd-HH-mm-ss");
				url = urlBase + "api/v1/devices/" + deviceId + "/reservations/new";
				Unirest.post(url).basicAuth(user, password).queryString("clientCurrentTimeStamp", timestamp)
				.queryString("start", startTime).queryString("end", endTime).asString();
				logger.info("Device : " + getDeviceProperty(deviceName, "deviceName") + "reserved for Start Time"  + startTime + "endTime" + endTime);
			}else {
				logger.info("Device already in use..");
			}
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	public void getSpecificDevice(String deviceId) {
		try {
			String url = urlBase + "api/v1/devices/" + deviceId;
			HttpResponse<String> response = Unirest.get(url).basicAuth(user, password).asString();
			logger.info("Device Details are: " + response.getBody());
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}

	public void releaseSpecificDevice(String deviceName) {
		try {
			String deviceId = getDeviceProperty(deviceName, "id");
			String url = urlBase + "api/v1/devices/" + deviceId + "/release";
			Unirest.get(url).basicAuth(user, password).asString();
			logger.info("Device: " + getDeviceProperty(deviceName, "deviceName") + "Released");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	public void rebootSpecificDevice(String deviceId) {
		try {
			String url = urlBase + "api/v1/devices/" + deviceId + "/reboot";
			Unirest.get(url).basicAuth(user, password).asString();
			logger.info("Device: " + getDeviceProperty(deviceId, "deviceName") + "Rebooted");
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	public void reserveMultiDevices(String deviceLists, String startTime, String endTime) {
		try {
			String timestamp = DateTime.getCurrentTime("yyyy-MM-dd-HH-mm-ss");
			url = urlBase + "api/v1/devices/reservations/new";
			
			Unirest.post(url).basicAuth(user, password).queryString("clientCurrentTimeStamp", timestamp)
			.queryString("start", startTime).queryString("end", endTime).queryString("deviceList", deviceLists)
			.queryString("userId", "3").queryString("projectId", "2").asString();
			
			logger.info(deviceLists);
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}
	
	public void resetDeviceUSBConnection(String deviceId) {
		try {
			String url = urlBase + "api/v1/devices/" + deviceId + "/resetusb";
			Unirest.get(url).basicAuth(user, password).asString();
			logger.info("USB Connection reset for " + getDeviceProperty(deviceId, "deviceName"));
		} catch (Exception e) {
			logger.error("Exception: " + e.getMessage());
		}
	}
}
