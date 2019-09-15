package com.qaninjas.framework.interfaces;

import org.openqa.selenium.WebElement;

public interface IMouseAction {

	void moveTo();
	
	void dragAndDrop(WebElement from, WebElement to);
}
