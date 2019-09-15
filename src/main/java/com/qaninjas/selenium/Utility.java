package com.qaninjas.selenium;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.Report;

public class Utility {

	private static Utility instance = null;
	private static  Logger logger = Logger.getLogger(Utility.class);
	
	private Report report = Report.getInstance();
	
	protected Utility() {
		
	}
		
	public static Utility getInstance() {
		if(null == instance) {
			instance = new Utility();
		}
		return instance;
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
	
	public List<String> convertWebElementToString(List<WebElement> list){
		List<String> lists = new ArrayList<String>();
		for(WebElement element : list) {
			lists.add(element.getText().trim());
		}
		return lists;
	}
}
