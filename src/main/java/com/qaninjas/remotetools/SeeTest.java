package com.qaninjas.remotetools;

import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;

import com.qaninjas.framework.constants.FrameworkConstants;

public class SeeTest {

	private WebDriver driver;
	private static  Logger logger = Logger.getLogger(SeeTest.class);
	
	public WebDriver getSeeTestDriver(DesiredCapabilities capabilities) {
		if(FrameworkConstants.MOBILECONFIG.get("TARGET_MODE").equalsIgnoreCase("local")){
			getSeeTestLocalDriver(capabilities);
		}else if(FrameworkConstants.MOBILECONFIG.get("TARGET_MODE").equalsIgnoreCase("grid")){
			getSeeTestGridDriver(capabilities);
		}else {
			logger.info("Check Mobile config TARGET_MODE value");
		}
		return driver;
	}

	private WebDriver getSeeTestLocalDriver(DesiredCapabilities capabilities) {
		try {
			if(FrameworkConstants.MOBILECONFIG.get("").equalsIgnoreCase("android")) {
				driver = new AndroidDriver<AndroidElement>(new URL(FrameworkConstants.MOBILECONFIG.get("REMOTE_URL")), capabilities);
			}else if(FrameworkConstants.MOBILECONFIG.get("").equalsIgnoreCase("ios")) {
				driver = new IOSDriver<IOSElement>(new URL(FrameworkConstants.MOBILECONFIG.get("REMOTE_URL")), capabilities);
			}else {
				logger.info("Check Mobile config PLATFORM_OS value");
			}
		}catch(Exception e) {
			logger.error("Error in SeeTest Local driver initialization" + e.getMessage());
		}
		return driver;
	}

	private WebDriver getSeeTestGridDriver(DesiredCapabilities capabilities) {
		String testName = FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE") + "execution";
		capabilities.setCapability("testName", testName);
		capabilities.setCapability("accessKey", FrameworkConstants.MOBILECONFIG.get("ACCESS_KEY"));
		capabilities.setCapability("nativeWebScreenshot", true);
		try {
			if(FrameworkConstants.MOBILECONFIG.get("").equalsIgnoreCase("android")) {
				driver = new AndroidDriver<AndroidElement>(new URL(FrameworkConstants.MOBILECONFIG.get("CLOUD_URL")), capabilities);
			}else if(FrameworkConstants.MOBILECONFIG.get("").equalsIgnoreCase("ios")) {
				driver = new IOSDriver<IOSElement>(new URL(FrameworkConstants.MOBILECONFIG.get("CLOUD_URL")), capabilities);
			}
		}catch(Exception e) {
			e.printStackTrace();
			logger.error("Error in SeeTest Grid driver initialization" + e.getMessage());
		}
		return driver;
	}
}
