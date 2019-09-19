package com.qaninjas.selenium;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.interfaces.ITable;

public class Table implements ITable{

	private static Table instance = null;
	
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Table.class);
	
	private List<WebElement> tableData;
	protected Synchronization browserSync = Synchronization.getInstance();
	
	protected Table() {

	}
		
	public static Table getInstance() {
		if(null == instance) {
			instance = new Table();
		}
		return instance;
	}
	
	public List<WebElement> getTable(By elementLocator) {
		logger.debug("Get Table data of element locator " + elementLocator);
		browserSync.waitForElement(elementLocator);
		tableData = driver.findElements(elementLocator);
		return tableData;
	}

}
