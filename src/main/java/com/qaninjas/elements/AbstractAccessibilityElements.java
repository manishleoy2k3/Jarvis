package com.qaninjas.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Initialize;
import com.qaninjas.framework.interfaces.IAlert;
import com.qaninjas.framework.interfaces.IDropdown;
import com.qaninjas.framework.interfaces.IFrame;
import com.qaninjas.framework.interfaces.IJavascriptExecute;
import com.qaninjas.framework.interfaces.ISynchronization;
import com.qaninjas.framework.interfaces.ITable;
import com.qaninjas.framework.interfaces.ITouchAction;
import com.qaninjas.framework.interfaces.IWindows;
import com.qaninjas.framework.utility.report.ExtentManager;
import com.qaninjas.framework.utility.selenium.Locator;
import com.qaninjas.selenium.Alerts;
import com.qaninjas.selenium.Dropdown;
import com.qaninjas.selenium.Elements;
import com.qaninjas.selenium.Frames;
import com.qaninjas.selenium.JavaScriptExecute;
import com.qaninjas.selenium.Synchronization;
import com.qaninjas.selenium.Table;
import com.qaninjas.selenium.TouchActions;
import com.qaninjas.selenium.Windows;

public class AbstractAccessibilityElements extends AbstractElements{

	private static Initialize init = Initialize.getInstance();
	private WebDriver driver = DriverFactory.getInstance().getDriver();
	protected Elements field = Elements.getInstance();
	protected Locator locator = Locator.getInstance();
	protected Synchronization browserSync = Synchronization.getInstance();
	protected ExtentManager extentManager = ExtentManager.getInstance();
	
	public IDropdown dropdown(By elementLocator) {
		locator.setLocator(elementLocator);
		return Dropdown.getInstance();
	}

	public IAlert alert() {
		return Alerts.getInstance();
	}

	public IJavascriptExecute jsExecute() {
		return JavaScriptExecute.getInstance();
	}

	public ISynchronization sync() {
		return Synchronization.getInstance();
	}

	public IFrame frame() {
		return Frames.getInstance();
	}

	public IWindows window() {
		return Windows.getInstance();
	}

	public ITable table(By elementLocator) {
		return Table.getInstance();
	}

	public ITouchAction touchActions() {
		return TouchActions.getInstance();
	}
	
	public void getURL(String url) {
		field.getURL(url);	
	}

	public void sendKeys(By elementLocator, String value, String description) {
		field.sendKeys(elementLocator, value, description);
	}

	public void click(By elementLocator, String description) {
		field.click(elementLocator, description);
	}

	public void clear(By elementLocator) {
		field.clear(elementLocator);
	}

	public String getText(By elementLocator) {
		return field.getText(elementLocator);
	}

	public String getAttribute(By elementLocator, String attribute) {
		return field.getAttribute(elementLocator, attribute);
	}

	public boolean isEnabled(By elementLocator) {
		return field.isEnabled(elementLocator);
	}

	public boolean isDisplayed(By elementLocator) {
		return field.isDisplayed(elementLocator);
	}

	public boolean isSelected(By elementLocator) {
		return field.isSelected(elementLocator);
	}
}
