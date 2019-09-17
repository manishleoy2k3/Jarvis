package com.qaninjas.driverfactory.mobile;

import java.awt.PageAttributes.OrientationRequestedType;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qaninjas.driverfactory.AbstractApp;
import com.qaninjas.driverfactory.desktop.RemoteSetup;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.SetUpConstants;

import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;

public class MobileApp extends AbstractApp{

	private WebDriver driver;
	
	private static MobileApp instance = null;
	private DesiredCapabilities capabilities = new DesiredCapabilities();
	private RemoteSetup remote  = new RemoteSetup();
	private static  Logger logger = Logger.getLogger(MobileApp.class);
	
	protected MobileApp() {
		getPluginConnection();
	}
		
	public static synchronized MobileApp getInstance() {
		if(null == instance) {
			instance = new MobileApp();
		}
		return instance;
	}
	
	private enum targetMode{
		LOCAL, GRID
	};
	
	
	@Override
	public DesiredCapabilities setAppDesiredCapabilities() {
		switch(targetMode.valueOf(FrameworkConstants.MOBILECONFIG.get("TARGET_MODE").toUpperCase())) {
		case LOCAL:
			if(FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE").equalsIgnoreCase("Android")) {
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 
						FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE"));
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 
						FrameworkConstants.MOBILECONFIG.get("DEVICE_NAME"));
				capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, 
						FrameworkConstants.MOBILECONFIG.get("APP_PACKAGE"));
				capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, 
						FrameworkConstants.MOBILECONFIG.get("APP_ACTIVITY"));				
			}else {
				capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, 
						FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE"));
				capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, 
						FrameworkConstants.MOBILECONFIG.get("DEVICE_NAME"));
				capabilities.setCapability(MobileCapabilityType.ORIENTATION, 
						OrientationRequestedType.PORTRAIT);
				capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, 
						FrameworkConstants.MOBILECONFIG.get("BUNDLE_ID"));
			}
			break;
		case GRID:
			if(FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE").equalsIgnoreCase("Android")) {
				String queryForCloud = "@os='"+ FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE")+"' and @category='PHONE' and @name='"
						+ FrameworkConstants.MOBILECONFIG.get("") + "'";
				capabilities.setCapability("deviceQuery", queryForCloud);
				capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, 
						FrameworkConstants.MOBILECONFIG.get("APP_PACKAGE"));
				capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, 
						FrameworkConstants.MOBILECONFIG.get("APP_ACTIVITY"));				
			}else if(FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE").equalsIgnoreCase("ios")){
				String queryForCloud = "@os='"+ FrameworkConstants.MOBILECONFIG.get("PLATFORM_TYPE")+"' and @category='PHONE' and @name='"
						+ FrameworkConstants.MOBILECONFIG.get("") + "'";
				capabilities.setCapability("deviceQuery", queryForCloud);
				capabilities.setCapability(MobileCapabilityType.ORIENTATION, 
						OrientationRequestedType.PORTRAIT);
				capabilities.setCapability(IOSMobileCapabilityType.BUNDLE_ID, 
						FrameworkConstants.MOBILECONFIG.get("BUNDLE_ID"));
				capabilities.setCapability("unicodeKeyboard", true);
				capabilities.setCapability("resetKeyboard", true);
			}else{
				logger.info("Check config values...");
			}
			break;
		}
		return capabilities;
	}

	@Override
	public WebDriver setAppProperties() {
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
		setAppDesiredCapabilities();
		driver = remote.getRemotePlugin(capabilities);
		return driver;	}

	public WebDriver getDriver() {
		return driver;
	}
}
