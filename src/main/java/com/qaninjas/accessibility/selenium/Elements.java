package com.qaninjas.accessibility.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.aventstack.extentreports.Status;
import com.qaninjas.accessibility.framework.AccessibilityLibrary;
import com.qaninjas.framework.Report;

public class Elements {

	private static Elements instance = null;
	private static  Logger logger = Logger.getLogger(Elements.class);
	private Report report = Report.getInstance();
	protected FindElement find = FindElement.getInstance();
	protected AccessibilityLibrary accLib = AccessibilityLibrary.getInstance();	
	
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
		find.findElementWithAccessibility(elementLocator).sendKeys(value);
		logger.debug("Enter value " + value + "" + elementLocator);
		report.log(description, value, Status.PASS);
	}
	
	private void clear(By elementLocator) {
		find.driver.findElement(elementLocator).clear();	
	}

	public void click(By elementLocator, String description) {
		find.driver.findElement(elementLocator).click();
		report.log(description, "", Status.PASS);
	}

	public String getText(By elementLocator) {
		logger.debug("Element locator get Text... " + find.driver.findElement(elementLocator).getText());
		return find.findElementWithAccessibility(elementLocator).getText();
	}
	
	public String getAttribute(By elementLocator, String attribute) {
		logger.debug("Element locator get Text... " + find.driver.findElement(elementLocator).getAttribute(attribute));
		return find.findElementWithAccessibility(elementLocator).getAttribute(attribute);
	}
	
	public boolean isEnabled(By elementLocator) {
		return find.findElementWithAccessibility(elementLocator).isEnabled();
	}
	
	public boolean isDisplayed(By elementLocator) {
		return find.findElementWithAccessibility(elementLocator).isDisplayed();
	}
	
	public boolean isSelected(By elementLocator) {
		return find.findElementWithAccessibility(elementLocator).isSelected();
	}
	
	public void getURL(String url) {
		find.driver.get(url);
		accLib.pageLevelAccessibilityTest();
	}
}
