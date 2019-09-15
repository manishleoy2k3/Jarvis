package com.qaninjas.framework.interfaces;

import org.openqa.selenium.By;

public interface IJavascriptExecute {

	void click(By elementLocator);
	
	void scrollDownTo(int scroolFrom, int scrollTo);
	
	void scrollToBottom();
	
	void scrollToElement(By elementLocator);
	
	void scrollToElementClick(By elementLocator);
	
	void sendKeys(By inputField, String value);
	
	void getTitleJS();
	
	void refreshPage();
}
