package com.qaninjas.accessibility.selenium;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.interfaces.IDropdown;
import com.qaninjas.framework.utility.selenium.Locator;
import com.qaninjas.selenium.SeleniumUtility;
import com.qaninjas.selenium.Synchronization;

public class Dropdown implements IDropdown{

	private static Dropdown instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Dropdown.class);
	
	protected Synchronization sync = Synchronization.getInstance();
	private Report report = Report.getInstance();
	protected Locator locator = Locator.getInstance();
	protected FindElement find = FindElement.getInstance();
	
	protected SeleniumUtility utility = SeleniumUtility.getInstance();
	
	List<WebElement> optionsList;
	
	protected Dropdown() {
		
	}
		
	public static Dropdown getInstance() {
		if(null == instance) {
			instance = new Dropdown();
		}
		return instance;
	}

	public List<String> getAllOptions() {
		sync.waitForElement(locator.getLocator());
		Select options = new Select(find.findElementWithAccessibility(locator.getLocator()));
		
		optionsList = options.getOptions();
		List<String> listOfOptions = utility.convertWebElementToString(optionsList);
		
		logger.debug("Dropdown locator found..." + locator.getLocator());
		return listOfOptions;
	}

	public void selectbyVisibleText(String value) {
		sync.waitForElement(locator.getLocator());
		Select options = new Select(find.findElementWithAccessibility(locator.getLocator()));
		
		options.selectByVisibleText(value);
		
		logger.debug("Element locator found..." + locator.getLocator());
		report.log("Selected value by VisibileText", value, Status.PASS);
	}

	public void selectbyIndex(int index) {
		sync.waitForElement(locator.getLocator());
		Select options = new Select(find.findElementWithAccessibility(locator.getLocator()));
		
		options.selectByIndex(index);
		
		logger.debug("Element locator found..." + locator.getLocator());
		report.log("Selected value by Index", "", Status.PASS);
	}

	public void selectbyValue(String value) {
		sync.waitForElement(locator.getLocator());
		Select options = new Select(find.findElementWithAccessibility(locator.getLocator()));
		
		options.selectByValue(value);
		
		logger.debug("Element locator found..." + locator.getLocator());
		report.log("Selected value by Value", value, Status.PASS);		
	}

	public String getFirstSelectedOption() {
		sync.waitForElement(locator.getLocator());
		Select options = new Select(find.findElementWithAccessibility(locator.getLocator()));
		
		String firstValue = options.getFirstSelectedOption().getText();
		report.log("Selected value by Value", firstValue, Status.PASS);
		return firstValue;
	}	
}