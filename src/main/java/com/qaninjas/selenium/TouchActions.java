package com.qaninjas.selenium;

import java.util.HashMap;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.qaninjas.framework.utility.selenium.Locator;

import io.appium.java_client.PerformsTouchActions;
import io.appium.java_client.TouchAction;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.interfaces.ITouchAction;

public class TouchActions implements ITouchAction{

	private static TouchActions instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(TouchActions.class);
	
	private Report report = Report.getInstance();
	protected Locator locator = Locator.getInstance();
	
	protected TouchActions() {
		
	}
		
	public static TouchActions getInstance() {
		if(null == instance) {
			instance = new TouchActions();
		}
		return instance;
	}
	
	public void swipeLeft(int numOfSwipe) {
		for(int num=0; num<numOfSwipe; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "left");
			js.executeScript("mobile: swipe", scrollObject);
			logger.debug("Swipe left");
			report.log("Swipe left successfully ", "", Status.PASS);
		}
	}

	public void swipeLeft(By locatorElement, int numOfSwipe) {
		WebElement element = (WebElement) locatorElement;
		for(int num=0; num<numOfSwipe; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "left");
			scrollObject.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: swipe", scrollObject);
			logger.debug("Swipe left");
			report.log("Swipe left successfully ", "", Status.PASS);
		}
	}

	public void swipeRight(int numOfSwipe) {
		for(int num=0; num<numOfSwipe; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "right");
			js.executeScript("mobile: swipe", scrollObject);
			logger.debug("Swipe right");
			report.log("Swipe right successfully ", "", Status.PASS);
		}
	}

	public void swipeRight(By locatorElement, int numOfSwipe) {
		WebElement element = (WebElement) locatorElement;
		for(int num=0; num<numOfSwipe; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "right");
			scrollObject.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: swipe", scrollObject);
			logger.debug("Swipe right");
			report.log("Swipe right successfully ", "", Status.PASS);
		}
	}

	public void scrollUp(int numOfScroll) {
		for(int num=0; num<numOfScroll; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "up");
			js.executeScript("mobile: scroll", scrollObject);
			logger.debug("Scroll up");
			report.log("Scroll up successfully ", "", Status.PASS);
		}
	}

	public void scrollDown(int numOfScroll) {
		for(int num=0; num<numOfScroll; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "down");
			js.executeScript("mobile: scroll", scrollObject);
			logger.debug("Scroll down");
			report.log("Scroll down successfully ", "", Status.PASS);
		}	
	}

	public void scrollDown(By locatorElement, int numOfScroll) {
		WebElement element = (WebElement) locatorElement;
		for(int num=0; num<numOfScroll; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "down");
			scrollObject.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: scroll", scrollObject);
			logger.debug("Scroll down");
			report.log("Scroll down successfully ", "", Status.PASS);
		}
	}

	public void scrollUp(By locatorElement, int numOfScroll) {
		WebElement element = (WebElement) locatorElement;
		for(int num=0; num<numOfScroll; num++) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			HashMap<String, String> scrollObject = new HashMap<String, String>();
			scrollObject.put("direction", "up");
			scrollObject.put("element", ((RemoteWebElement) element).getId());
			js.executeScript("mobile: scroll", scrollObject);
			logger.debug("Scroll up");
			report.log("Scroll up successfully ", "", Status.PASS);
		}
	}

	public boolean scrollTo(int scrollViewIndex, String listElementClass, String textOnListElement) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean scrollTo(String uiSelectorQuery) {
		// TODO Auto-generated method stub
		return false;
	}

	public void dragFromTo(Integer xStart, Integer yStart, Integer xEnd, Integer yEnd) {
		// TODO Auto-generated method stub
		
	}

	public void scrollFromTo(Integer xStart, Integer yStart, Integer xEnd, Integer yEnd) {
		new TouchAction((PerformsTouchActions) driver).press(xStart, yStart).waitAction().moveTo(xEnd, yEnd).release().perform();
		logger.debug("Scroll down on the basis of coordinate");
		report.log("Scroll down successfully ", "", Status.PASS);		
	}

}
