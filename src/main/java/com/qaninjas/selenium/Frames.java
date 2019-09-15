package com.qaninjas.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.constants.SetUpConstants;
import com.qaninjas.framework.interfaces.IFrame;

public class Frames implements IFrame{

	private static Frames instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Frames.class);
	private WebDriverWait wait = new WebDriverWait(driver, SetUpConstants.DEFAULT_TIMEOUT);
	
	protected Frames() {
		
	}
		
	public static Frames getInstance() {
		if(null == instance) {
			instance = new Frames();
		}
		return instance;
	}
	
	public void setDefaultFrame() {
		driver.switchTo().defaultContent();		
	}

	public void switchToFrame(By elementLocator) {
		logger.debug("Looking for element locator" + elementLocator);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementLocator));
			logger.debug("Element locator found" + elementLocator);
		} catch (Exception e) {
			logger.error("Element locator not found" + elementLocator);
			Assert.fail("Frame locator" + elementLocator + "not found");
		}		
	}

	public void switchToFrame(int frameIndex) {
		logger.debug("Looking for element locator" + frameIndex);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
			logger.debug("Element locator found" + frameIndex);
		} catch (Exception e) {
			logger.error("Element locator not found" + frameIndex);
			Assert.fail("Frame index" + frameIndex + "not found");
		}	
	}

	public void switchToFrame(String frameName) {
		logger.debug("Looking for element locator" + frameName);
		try {
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameName));
			logger.debug("Element locator found" + frameName);
		} catch (Exception e) {
			logger.error("Element locator not found" + frameName);
			Assert.fail("Frame Name" + frameName + "not found");
		}
	}

}
