package com.qaninjas.driverfactory.desktop;

import org.openqa.selenium.WebDriver;

import com.qaninjas.framework.constants.FrameworkConstants;

public class DesktopFactory {

	private WebDriver driver;
	
	private enum platform{
		APP, BROWSER
	};
	
	public WebDriver getDesktopInstance() {

		switch(platform.valueOf(FrameworkConstants.TARGET.toUpperCase())) {
			case BROWSER:
				driver = DesktopBrowser.getInstance().getDriver();
				break;
			case APP:
				break;
			default:
				break;
		}
		return driver;
	}

}
