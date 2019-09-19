package com.qaninjas.accessibility.selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaninjas.accessibility.framework.AccessibilityLibrary;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.selenium.Synchronization;

public class FindElement {

	private static FindElement instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();	
	protected Synchronization sync = Synchronization.getInstance();
	protected AccessibilityLibrary accLib = AccessibilityLibrary.getInstance();	
	
	protected FindElement() {
		
	}
		
	public static FindElement getInstance() {
		if(null == instance) {
			instance = new FindElement();
		}
		return instance;
	}
	
	public WebElement findElementWithAccessibility(By elementLocator) {
		sync.waitForElement(elementLocator);
		accLib.elementLevelAccessibilityTest(driver.findElement(elementLocator));
		return driver.findElement(elementLocator);
	}
	
	public List<WebElement> findElementsWithAccessibility(By elementLocator) {
		List<WebElement> elements = driver.findElements(elementLocator);
		for(int element=0; element < elements.size(); element++) {
			sync.waitForElement(elementLocator);
			accLib.elementLevelAccessibilityTest(elements.get(element));
		}		
		return elements; 
	}
}
