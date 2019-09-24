package com.qaninjas.framework;

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

	public By getElementLocator() {
		return elementLocator;
	}

	public void setElementLocator(By elementLocator) {
		this.elementLocator = elementLocator;
	}
}
