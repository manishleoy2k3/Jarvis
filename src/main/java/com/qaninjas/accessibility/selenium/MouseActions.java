package com.qaninjas.accessibility.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.interfaces.IMouseAction;
import com.qaninjas.framework.utility.selenium.Locator;

public class MouseActions implements IMouseAction{

	private static MouseActions instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(MouseActions.class);
	protected Locator locator = Locator.getInstance();
	
	private Actions builder = new Actions(driver);
	private Report report = Report.getInstance();
	
	protected MouseActions() {
		
	}
		
	public static MouseActions getInstance() {
		if(null == instance) {
			instance = new MouseActions();
		}
		return instance;
	}
	
	public void moveTo() {
		builder.moveToElement(driver.findElement(locator.getLocator())).build().perform();
		logger.debug("Move to Element using mouse actions..." + locator.getLocator());
		report.log("Method Pass : Moved to Element", "", Status.PASS);
	}

	public void rightClick() {
		builder.contextClick(driver.findElement(locator.getLocator())).build().perform();
		logger.debug("Right click on an Element using mouse actions..." + locator.getLocator());
		report.log("Method Pass : Right clicked on Element", "", Status.PASS);
	}

	public void doubleClick() {
		builder.doubleClick(driver.findElement(locator.getLocator())).build().perform();
		logger.debug("Double click on an Element using mouse actions..." + locator.getLocator());
		report.log("Method Pass : Double clicked on Element", "", Status.PASS);
	}
	
	public void dragAndDrop(WebElement from, WebElement to) {
		builder.dragAndDrop(from, to).build().perform();
		report.log("Method Pass: Element Dragged and Dropped from" + from + "-->" + to, "", Status.PASS);
	}

	
}
