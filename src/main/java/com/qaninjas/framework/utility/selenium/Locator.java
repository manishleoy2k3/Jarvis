package com.qaninjas.framework.utility.selenium;

import org.openqa.selenium.By;

public class Locator {

	private By elementLocator;
	private static Locator instance = null;
	
	protected Locator() {
		
	}
	
	public static Locator getInstance() {
		if(null == instance) {
			instance = new Locator();
		}
		return instance;
	}
		
	public By getLocator() {
		return elementLocator;
	}

	public void setLocator(By elementLocator) {
		this.elementLocator = elementLocator;
	}

}
