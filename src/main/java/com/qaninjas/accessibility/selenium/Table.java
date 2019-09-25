package com.qaninjas.accessibility.selenium;

import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.qaninjas.framework.interfaces.ITable;
import com.qaninjas.selenium.Synchronization;

public class Table implements ITable{

	private static Table instance = null;
	private List<WebElement> tableData;
	private static  Logger logger = Logger.getLogger(Table.class);
	protected Synchronization browserSync = Synchronization.getInstance();
	protected FindElement find = FindElement.getInstance();
	
	
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
		tableData = find.findElementsWithAccessibility(elementLocator);
		return tableData;
	}

}