package com.qaninjas.selenium;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.interfaces.IElementList;

public class BrowserElementsList implements IElementList{

	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(BrowserElementsList.class);
	
	protected Synchronization browserSync = Synchronization.getInstance();
	
	protected BrowserElementsList() {
		
	}

	public List<WebElement> getListOfElements(By elementLocator) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getListOfElementByText(By elementLocator) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getElementListSize(By elementLocator) {
		// TODO Auto-generated method stub
		return 0;
	}

	public boolean isTextPresentInList(By elementLocator, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	public List<String> sortElementsInList(By elementLocator, String sortBy) {
		// TODO Auto-generated method stub
		return null;
	}

	public String compareList(By elementLocator, List<String> list) {
		// TODO Auto-generated method stub
		return null;
	}

}
