package com.qaninjas.framework.interfaces;

import org.openqa.selenium.By;

public interface IWindows {

	void switchToWindow(By elementLocator, int timeout);
	
	void closeAllOpenWindow(String titleMainWindow);
}
