package com.qaninjas.framework.interfaces;

import org.openqa.selenium.By;

public interface ISynchronization {

	void pause(int sleep);
	
	void waitForSpinner(int waitTime);
	
	void waitForElement(By elementLocator);
	
	void waitForElementInTime(By elementLocator, int time);
	
	void elementToBeClickable(By elementLocator);
	
	void isElementClickableInTime(By elementLocator, int time);
	
	void waitForInvisibility(By elementLocator);
	
	void waitForInvisibilityInTime(By elementLocator, int time);
	
	void fluentWaitForElement(By elementLocator);
	
	void fluentWaitForElementInTime(By elementLocator, int time);
}
