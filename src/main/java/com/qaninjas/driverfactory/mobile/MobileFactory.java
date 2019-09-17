package com.qaninjas.driverfactory.mobile;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.qaninjas.framework.constants.FrameworkConstants;


public class MobileFactory {

	private WebDriver driver;
	private static  Logger logger = Logger.getLogger(MobileApp.class);
	
	private enum platform{
		APP, BROWSER
	};
	
	public WebDriver getMobileInstance() {
		switch(platform.valueOf(FrameworkConstants.TARGET.toUpperCase())){
		case APP:
			driver = MobileApp.getInstance().getDriver();
			break;
		case BROWSER:
			driver = MobileBrowser.getInstance().getDriver();
			break;
		default:
			logger.info("Select appropriate platform");
			break;
		}
		return driver;
	}

}
