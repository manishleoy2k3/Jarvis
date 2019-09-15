package com.qaninjas.framework.interfaces;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public interface IElementList {

	List<WebElement> getListOfElements(By elementLocator);
	
	List<String> getListOfElementByText(By elementLocator);
	
	int getElementListSize(By elementLocator);
	
	boolean isTextPresentInList(By elementLocator, String value);
	
	List<String> sortElementsInList(By elementLocator, String sortBy);
	
	String compareList(By elementLocator, List<String> list);
}
