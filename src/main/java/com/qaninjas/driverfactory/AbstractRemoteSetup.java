package com.qaninjas.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qaninjas.api.utils.SeeTestManageDevicesAPI;
import com.qaninjas.framework.constants.FrameworkConstants;

public abstract class AbstractRemoteSetup {

	protected abstract WebDriver setRemotePlugIn(DesiredCapabilities capabilities);
	public abstract WebDriver getRemotePlugIn(DesiredCapabilities capabilities);
	
	public void getEnvironmentDetails(String environment) {
		
	}
	
	public void setSeeTestEnvironmentDetails() {
		SeeTestManageDevicesAPI objSeeTestManageDevicesAPI = new SeeTestManageDevicesAPI();
		objSeeTestManageDevicesAPI.getDeviceProperty(FrameworkConstants.MOBILECONFIG.get("DEVICE_ID_API"), "displayStatus");
		objSeeTestManageDevicesAPI.reserveSingleDevice(FrameworkConstants.MOBILECONFIG.get("DEVICE_ID_API"), 
				FrameworkConstants.MOBILECONFIG.get("START_TIME"), FrameworkConstants.MOBILECONFIG.get("END_TIME"));
	}
}
