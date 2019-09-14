package com.qaninjas.driverfactory;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.qaninjas.driverfactory.desktop.DesktopFactory;
import com.qaninjas.driverfactory.mobile.MobileFactory;
import com.qaninjas.framework.constants.FrameworkConstants;

public class DriverFactory {

	private static DriverFactory instance = null;
	private WebDriver driver;
	private DesktopFactory desktop = new DesktopFactory();
	private MobileFactory mobile = new MobileFactory();
	
	private enum modules{
		DESKTOP, API, MOBILE
	}
	protected DriverFactory() {
		
	}
	
	public static DriverFactory getInstance() {
		if(null == instance) {
			instance = new DriverFactory();
		}
		return instance;
	}
	
	public WebDriver getDriver() {
		switch(modules.valueOf(FrameworkConstants.EXECUTION_ON.toUpperCase())) {
			case DESKTOP:
				driver = desktop.getDesktopInstance();
				break;
			case MOBILE:
				driver = mobile.getMobileInstance();
				break;
			case API:
				break;
			default:
				Assert.fail("Invalid platform selection");
				break;			
		}
		return driver;
	}
}
