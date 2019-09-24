package com.qaninjas.framework.utility;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.qaninjas.driverfactory.DriverFactory;

public class FileUpload {

	// WiniumDriver d;
	// private WiniumDriverService service;
	
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(FileUpload.class);
	
}
