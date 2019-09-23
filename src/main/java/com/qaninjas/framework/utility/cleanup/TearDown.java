package com.qaninjas.framework.utility.cleanup;

import org.openqa.selenium.WebDriver;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.utility.report.ExtentManager;

public class TearDown {

	private static ExtentManager extentManager = ExtentManager.getInstance();
	private WebDriver driver = DriverFactory.getInstance().getDriver();
	
	public void tearDown() {
		extentManager.endReport();
		driver.quit();
	}
	
}
