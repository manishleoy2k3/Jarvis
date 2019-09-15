package com.qaninjas.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.constants.SetUpConstants;
import com.qaninjas.framework.interfaces.IAlert;

public class Alerts implements IAlert{

	private static Alerts instance = null;
	private static  Logger logger = Logger.getLogger(Alerts.class);
	
	private WebDriver driver = DriverFactory.getInstance().getDriver();
	private WebDriverWait wait = new WebDriverWait(driver, SetUpConstants.DEFAULT_TIMEOUT);
	private Alert simpleAlert;
	
	protected Alerts() {
		
	}
	
	public static Alerts getInstance() {
		if(null == instance) {
			instance = new Alerts();
		}
		return instance;
	}
	
	public boolean alertIsPresent() {
		logger.debug("Looking for element locator");
		return alertIsPresentInTime(SetUpConstants.DEFAULT_TIMEOUT);
	}

	public boolean alertIsPresentInTime(int time) {
		logger.debug("Looking for element locator");
		try {
			wait.until(ExpectedConditions.alertIsPresent());
			logger.debug("Alert is found ....");
			return true;
		} catch (Exception e) {
			logger.debug("Alert is not found ....");
			return false;
		}
	}

	public void close() {
		switchToAlert();
		simpleAlert.dismiss();
	}

	public void accept() {
		switchToAlert();
		simpleAlert.accept();
	}

	public void switchToAlert() {
		if(alertIsPresent()) {
			simpleAlert = driver.switchTo().alert();
		}else {
			Assert.fail("Alert is not found");
		}
	}

}
