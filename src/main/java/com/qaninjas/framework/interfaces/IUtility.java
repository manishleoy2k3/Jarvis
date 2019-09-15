package com.qaninjas.framework.interfaces;

import java.util.List;

import org.openqa.selenium.WebElement;

public interface IUtility {

	void selectOption(List<WebElement> list, String value);
	
	List<String> convertWebElementToString(List<WebElement> list);
}
