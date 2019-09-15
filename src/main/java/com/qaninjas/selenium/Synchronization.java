package com.qaninjas.selenium;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.constants.SetUpConstants;
import com.qaninjas.framework.interfaces.ISynchronization;

public class Synchronization implements ISynchronization{

	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Synchronization.class);
	private WebDriverWait wait = new WebDriverWait(driver, SetUpConstants.DEFAULT_TIMEOUT);
	
	private static Synchronization instance = null;
	
	protected Synchronization() {

	}
	
	public static Synchronization getInstance() {
		if(null == instance) {
			instance = new Synchronization();
		}
		return instance;
	}
	
	public void pause(int sleep) {
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			logger.error("Exception...." + e.getMessage());
		}
	}

	public void waitForSpinner(int waitTime) {
		WebDriverWait wait = new WebDriverWait(driver, waitTime, 100);
		wait.until(angularHasFinishedProcessing());
	}

	private static ExpectedCondition<Boolean> angularHasFinishedProcessing() {
		return new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return Boolean.valueOf(((JavascriptExecutor) driver).executeScript("return (window.angular !== undefined) && (angular.element(document).injector() !== undefined) && (angular.element(document).injector().get('$http').pendingRequests.length === 0)").toString());
			}
		};
	}

	public void waitForElement(By elementLocator) {
		waitForElementInTime(elementLocator, SetUpConstants.DEFAULT_TIMEOUT);
	}

	public void waitForElementInTime(By elementLocator, int time) {
		logger.debug("Looking for Element locator " + elementLocator + "for time...." + time);
		wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(elementLocator));
			logger.debug("Element locator found " + elementLocator);
		} catch (Exception e) {
			logger.debug("Element locator not found " + elementLocator);
			Assert.fail("Element locator id not found");
		}
	}

	public void elementToBeClickable(By elementLocator) {
		isElementClickableInTime(elementLocator, SetUpConstants.DEFAULT_TIMEOUT);
	}

	public void isElementClickableInTime(By elementLocator, int time) {
		logger.debug("Looking for Element locator to clickable" + elementLocator + "for time...." + time);
		wait = new WebDriverWait(driver, time);
		try {
			wait.until(ExpectedConditions.elementToBeClickable(elementLocator));
			logger.debug("Element locator clickable " + elementLocator);
		} catch (Exception e) {
			logger.debug("Element locator not clickable " + elementLocator);
			Assert.fail("Element locator not clickable");
		}
	}

	public void waitForInvisibility(By elementLocator) {
		logger.debug("Looking for Element locator for Invisibility " + elementLocator + "in default time...." + SetUpConstants.DEFAULT_TIMEOUT);
		waitForInvisibilityInTime(elementLocator, SetUpConstants.DEFAULT_TIMEOUT);
	}

	public void waitForInvisibilityInTime(By elementLocator, int time) {
		wait = new WebDriverWait(driver, time);
		try {
			logger.debug("Looking for Element locator for Invisibility" + elementLocator + "for time...." + time);
			wait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
			logger.debug("Element locator found..." + elementLocator);
		} catch (Exception e) {
			logger.debug("Element locator not invisible " + elementLocator);
			Assert.fail("Element locator not invisible");
		}
	}

	public void fluentWaitForElement(By elementLocator) {
		fluentWaitForElementInTime(elementLocator, SetUpConstants.DEFAULT_TIMEOUT);
	}

	public void fluentWaitForElementInTime(final By elementLocator, int time) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(time, TimeUnit.SECONDS);
		wait.withTimeout(SetUpConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		wait.ignoring(NoSuchElementException.class);
		
		Function<WebDriver, WebElement> function = new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				WebElement element = driver.findElement(elementLocator);
				if(element != null) {
				}
				return element;
			}
		};
		wait.until(function);
	}

}
