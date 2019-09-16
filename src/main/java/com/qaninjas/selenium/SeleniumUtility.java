package com.qaninjas.selenium;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.interfaces.IUtility;

public class SeleniumUtility implements IUtility{

	private static SeleniumUtility instance = null;
	private static  Logger logger = Logger.getLogger(SeleniumUtility.class);
	
	private Report report = Report.getInstance();
	
	protected SeleniumUtility() {
		
	}
		
	public static SeleniumUtility getInstance() {
		if(null == instance) {
			instance = new SeleniumUtility();
		}
		return instance;
	}

	public List<String> convertWebElementToString(List<WebElement> optionsList) {
		List<String> lists = new ArrayList<String>();
		for(WebElement element : optionsList) {
			lists.add(element.getText().trim());
		}
		return lists;
	}

	public void selectOption(List<WebElement> list, String value) {
		logger.info("Selecting option from list.");
		for(WebElement element : list) {
			if(element.getText().equalsIgnoreCase(value)) {
				element.click();
				report.log("Method Pass : Option selected from list", "", Status.PASS);
				break;
			}
		}	
	}

}
