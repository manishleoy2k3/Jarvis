package com.qaninjas.remotetools;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qaninjas.framework.constants.FrameworkConstants;


public class Appium {

	private WebDriver driver;
	private static  Logger logger = Logger.getLogger(Appium.class);
	
	public WebDriver getAppiumDriver(DesiredCapabilities capabilities) {
		if(FrameworkConstants.MOBILECONFIG.get("TARGET_MODE").equalsIgnoreCase("local")){
			getAppiumLocalDriver(capabilities);
		}else {
			getAppiumGridDriver(capabilities);
		}
		return driver;
	}

	private void getAppiumGridDriver(DesiredCapabilities capabilities) {
		try {
			driver = new RemoteWebDriver(new URL(FrameworkConstants.MOBILECONFIG.get("REMOTE_URL")), capabilities);
			logger.info("Appium Grid driver is initialized");
		}catch(MalformedURLException e) {
			logger.error("Error in Appium Local driver initialization" + e.getMessage());
		}
	}

	private void getAppiumLocalDriver(DesiredCapabilities capabilities) {
		try {
			driver = new RemoteWebDriver(new URL(FrameworkConstants.MOBILECONFIG.get("REMOTE_URL")), capabilities);
			logger.info("Appium Local driver is initialized");
		}catch(MalformedURLException e) {
			logger.error("Error Appium Local driver is initialization" + e.getMessage());
		}
	}
}
