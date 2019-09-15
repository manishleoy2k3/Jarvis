package com.qaninjas.selenium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.interfaces.IElementList;

public class BrowserElementsList implements IElementList{

	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(BrowserElementsList.class);
	
	protected Synchronization browserSync = Synchronization.getInstance();
	private static Report report = Report.getInstance();
	
	protected BrowserElementsList() {
		
	}

	public List<WebElement> getListOfElements(By elementLocator) {
		browserSync.waitForElement(elementLocator);
		List<WebElement> elementList = driver.findElements(elementLocator);
		logger.debug("Returning the list of elements. Size is...." + elementList.size());
		return elementList;
	}

	public List<String> getListOfElementByText(By elementLocator) {
		List<WebElement> listElements = driver.findElements(elementLocator);
		List<String> listValues = new ArrayList<String>();
		for(int i = 0; i< listElements.size(); i++) {
			listValues.add(listElements.get(i).getText());
		}
		return listValues;
	}

	public int getElementListSize(By elementLocator) {
		return getListOfElements(elementLocator).size();
	}

	public boolean isTextPresentInList(By elementLocator, String value) {
		List<String> listValues = getListOfElementByText(elementLocator);
		if(listValues.contains(value)) {
			return true;
		}
		return false;
	}

	public List<String> sortElementsInList(By elementLocator, String sortBy) {
		List<String> listValues = getListOfElementByText(elementLocator);
		
		if(sortBy.equalsIgnoreCase("Ascending")) {
			Collections.sort(listValues);
			return listValues;
		}
		if(sortBy.equalsIgnoreCase("Descending")) {
			Collections.sort(listValues);
			Collections.reverse(listValues);
			return listValues;
		}
		return null;
	}

	public String compareList(By elementLocator, List<String> list) {
		List<String> actualList = getListOfElementByText(elementLocator);
		
		List<String> actualArrayList = new ArrayList<String>();
		actualArrayList.addAll(actualList);
		
		List<String> expectedList = new ArrayList<String>();
		expectedList.addAll(list);
		
		if(!(actualArrayList.size() == expectedList.size())) {
			return "The size of the expected and actual list are not same";
		}else {
			for(int i = 0; i < actualList.size(); i++) {
				if(expectedList.contains(actualArrayList.get(i))) {
					continue;
				}else {
					return "The value " + actualArrayList.get(i) + "is not present in the expected list";
				}
			}
			return "Both the lists matched";
		}
	}

}
