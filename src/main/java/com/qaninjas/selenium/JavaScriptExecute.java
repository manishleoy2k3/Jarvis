package com.qaninjas.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.interfaces.IJavascriptExecute;

public class JavaScriptExecute implements IJavascriptExecute{

	private static JavaScriptExecute instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(JavaScriptExecute.class);
	
	protected Elements browserElement = new Elements();
	private JavascriptExecutor jsExecutor  = (JavascriptExecutor) driver;
	protected Synchronization sync = Synchronization.getInstance();
	private Report report = Report.getInstance();
	
	protected JavaScriptExecute() {
		
	}
		
	public static JavaScriptExecute getInstance() {
		if(null == instance) {
			instance = new JavaScriptExecute();
		}
		return instance;
	}
	
	public void click(By elementLocator) {
		try {
			if(browserElement.isEnabled(elementLocator) && browserElement.isDisplayed(elementLocator)) {
				jsExecutor.executeScript("arguments[0].click()", driver.findElement(elementLocator));
				report.log("Method Pass : Element clicked", "", Status.PASS);
			}else {
				logger.info("");
				report.log("Method Failed : Element not clicked", "", Status.FAIL);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void scrollDownTo(int scroolFrom, int scrollTo) {
		jsExecutor.executeScript("scroll(" + Integer.toString(scroolFrom) + "," + Integer.toString(scrollTo) +")");
		report.log("Method Pass : Scrolled from" + scroolFrom + " "  + scrollTo, "", Status.PASS);
	}

	public void scrollToBottom() {
		jsExecutor.executeScript("window.scrollTo(0, Math.max(document.documentElement.scrollHeight, document.body.scrollHeight, document.body.clientHeight));");
		report.log("Method Pass : Scrolled to bottom of the page", "", Status.PASS);		
	}

	public void scrollToElement(By elementLocator) {
		if(browserElement.isEnabled(elementLocator) && browserElement.isDisplayed(elementLocator)) {
			jsExecutor.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(elementLocator));
			report.log("Method Pass : Scrolled by Element" + elementLocator, "", Status.PASS);
		}else {
			report.log("Method Failed : Not scrolled to Element", "", Status.FAIL);
		}		
	}

	public void scrollToElementClick(By elementLocator) {
		scrollToElement(elementLocator);
		click(elementLocator);
		report.log("Method Pass : Scrolled by element and click" + elementLocator, "", Status.PASS);		
	}

	public void sendKeys(By inputField, String value) {
		logger.info("Message to enter: " + value);
		if(browserElement.isEnabled(inputField) && browserElement.isDisplayed(inputField)) {
			jsExecutor.executeScript("arguments[0].value='" + value + "';", driver.findElement(inputField));
			report.log("Method Pass : Text entered" + value, "", Status.PASS);
		}else {
			report.log("Method Failed : Text not entered as Element not found " + inputField, "", Status.FAIL);
		}		
	}

	public void getTitleJS() {
		String titleName = jsExecutor.executeScript("return document.title;").toString();
		report.log("Method Pass : Title of the page: " + titleName, "", Status.PASS);		
	}

	public void refreshPage() {
		jsExecutor.executeScript("location.reload()");
		report.log("Method Pass : Page Refreshed", "", Status.PASS);		
	}

	public void hightlightElement(WebElement element) {
		logger.info("HighLighting element" + element);
		jsExecutor.executeScript("argument[0].style.border='3px solid red'", element);
		report.log("Method Pass : HighLight the Element" + element, "", Status.PASS);		
	}
	
	public void unHightlightElement(WebElement element) {
		logger.info("UnHighLighting element" + element);
		jsExecutor.executeScript("argument[0].style.border='0px'", element);
		report.log("Method Pass : HighLight the Element" + element, "", Status.PASS);		
	}
}
