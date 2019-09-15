package com.qaninjas.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qaninjas.driverfactory.DriverFactory;

public class Elements {

	private static Elements instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Elements.class);
	
	protected Synchronization browserSync = Synchronization.getInstance();
	
	private String currentHandle = "";
	private String newWindowHandle = "";
	private String mainWindowHandle = "";
	
	protected Elements() {
		
	}
		
	public static Elements getInstance() {
		if(null == instance) {
			instance = new Elements();
		}
		return instance;
	}

	public void click(By elementLocator, String string) {
		// TODO Auto-generated method stub
		
	}
	

}
