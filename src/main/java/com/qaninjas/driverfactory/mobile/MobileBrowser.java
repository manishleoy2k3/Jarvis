package com.qaninjas.driverfactory.mobile;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qaninjas.driverfactory.AbstractBrowser;
import com.qaninjas.driverfactory.desktop.RemoteSetup;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.SetUpConstants;

import io.appium.java_client.remote.MobileBrowserType;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileBrowser extends AbstractBrowser{

	private WebDriver driver;
	
	private static MobileBrowser instance = null;
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	private RemoteSetup remote  = new RemoteSetup();
	private static  Logger logger = Logger.getLogger(MobileBrowser.class);
	
	protected MobileBrowser() {
		getPluginConnection();
	}
		
	public static synchronized MobileBrowser getInstance() {
		if(null == instance) {
			instance = new MobileBrowser();
		}
		return instance;
	}
	
	private enum modules{
		CHROME, SAFARI, OPERA
	};
	
	@Override
	public DesiredCapabilities setBrowserCapabilities() {
		switch(modules.valueOf(FrameworkConstants.MOBILECONFIG.get("BROWSER").toUpperCase())) {
		case CHROME:
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 
					FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 
					FrameworkConstants.MOBILECONFIG.get("DEVICE_NAME"));
			if(FrameworkConstants.GLOBALCONFIG.get("EXECUTION_MODE").equalsIgnoreCase("seetest"))
				capabilities.setBrowserName(MobileBrowserType.CHROMIUM);			
			else {
				ChromeOptions options = new ChromeOptions();
				options.setExperimentalOption("androidPackage", "com.android.chrome");
				capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			}
			break;
		case SAFARI:
			capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 
					FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE"));
			capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 
					FrameworkConstants.MOBILECONFIG.get("DEVICE_NAME"));
			capabilities.setBrowserName(MobileBrowserType.SAFARI);
			break;
			
		case OPERA:
			break;
		}
		logger.info(SetUpConstants.BROWSER + "browser capabilities are set");
		return capabilities;
	}

	@Override
	public WebDriver setBrowserProperties() {
		logger.info("DEVICE NAME : " + FrameworkConstants.MOBILECONFIG.get("DEVICE_NAME"));
		logger.info("EXECUTION MODE : " + FrameworkConstants.MOBILECONFIG.get("EXECUTION_MODE"));
		logger.info("DEFAULT TIMEOUT : " + SetUpConstants.DEFAULT_TIMEOUT);
		logger.info("PAGELOAD TIMEOUT : " + SetUpConstants.PAGELOAD_TIMEOUT);
		logger.info("SCREEN SHOT : " + SetUpConstants.SCREEN_SHOT);
		logger.info("HTML REPORT : " + SetUpConstants.HTML_REPORT);
		
		driver.manage().timeouts().implicitlyWait(SetUpConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(SetUpConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().setScriptTimeout(SetUpConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		return driver;
	}

	@Override
	public WebDriver getPluginConnection() {
		setBrowserCapabilities();
		driver = remote.getRemotePlugIn(capabilities);
		return driver;
	}

	public WebDriver getDriver() {
		return driver;
	}
}
