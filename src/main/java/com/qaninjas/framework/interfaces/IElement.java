package com.qaninjas.framework.interfaces;

import org.openqa.selenium.By;

public interface IElement {

	IDropdown dropdown(By elementLocator);
	
	IAlert alert();
	
	IJavascriptExecute jsExecute();
	
	ISynchronization sync();
	
	IFrame frame();
	
	IWindows window();
	
	ITable table(By elementLocator);
	
	IUtility utility();
	
	IMouseAction mouseActions(By elementLocator);
	
	void getURL(String url);
	
	void sendKeys(By elementLocator, String value, String description);
	
	void click(By elementLocator, String description);
	
	void clear(By elementLocator);
	
	String getText(By elementLocator);
	
	String getAttribute(By elementLocator, String attribute);
	
	boolean isEnabled(By elementLocator);
	
	boolean isDisplayed(By elementLocator);
	
	boolean isSelected(By elementLocator);
	
	ITouchAction touchActions();
	
}
