package com.qaninjas.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;

public class Elements {

	private static Elements instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Elements.class);
	
	protected Synchronization sync = Synchronization.getInstance();
	private Report report = Report.getInstance();
	
	protected Elements() {
		
	}
		
	public static Elements getInstance() {
		if(null == instance) {
			instance = new Elements();
		}
		return instance;
	}

	public void sendKeys(By elementLocator, String value, String description) {
		clear(elementLocator);
		driver.findElement(elementLocator).sendKeys(value);
		logger.debug("Enter value " + value + "" + elementLocator);
		report.log(description, value, Status.PASS);
	}
	
	private void clear(By elementLocator) {
		sync.waitForElement(elementLocator);
		driver.findElement(elementLocator).clear();	
	}

	public void click(By elementLocator, String description) {
		sync.waitForElement(elementLocator);
		driver.findElement(elementLocator).click();
		report.log(description, "", Status.PASS);
	}

	public String getText(By elementLocator) {
		sync.waitForElement(elementLocator);
		logger.debug("Element locator get Text... " + driver.findElement(elementLocator).getText());
		return driver.findElement(elementLocator).getText();
	}
	
	public String getAttribute(By elementLocator, String attribute) {
		sync.waitForElement(elementLocator);
		logger.debug("Element locator get Text... " + driver.findElement(elementLocator).getAttribute(attribute));
		return driver.findElement(elementLocator).getAttribute(attribute);
	}
	
	public boolean isEnabled(By elementLocator) {
		sync.waitForElement(elementLocator);
		return driver.findElement(elementLocator).isEnabled();
	}
	
	public boolean isDisplayed(By elementLocator) {
		sync.waitForElement(elementLocator);
		return driver.findElement(elementLocator).isDisplayed();
	}
	
	public boolean isSelected(By elementLocator) {
		sync.waitForElement(elementLocator);
		return driver.findElement(elementLocator).isSelected();
	}
	
	public void getURL(String url) {
		driver.get(url);
	}
}
