package com.qaninjas.selenium;

import java.util.Set;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.interfaces.IWindows;

public class Windows implements IWindows{

	private static Windows instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Windows.class);
	
	protected Elements browserElements = Elements.getInstance();
	protected Synchronization browserSync = Synchronization.getInstance();
	
	private String currentHandle = "";
	private String newWindowHandle = "";
	private String mainWindowHandle = "";
	
	protected Windows() {
		
	}
		
	public static Windows getInstance() {
		if(null == instance) {
			instance = new Windows();
		}
		return instance;
	}
	
	public void switchToWindow(By elementLocator, int timeout) {
		currentHandle = driver.getWindowHandle();
		browserElements.click(elementLocator, "");
		for(int windowsCount = 0; windowsCount < timeout; windowsCount++) {
			Set<String> allWindowHandles = driver.getWindowHandles();
			if(allWindowHandles.size() > 1) {
				for(String allHandles : allWindowHandles) {
					if(!allHandles.equals(currentHandle))
						newWindowHandle = allHandles;
				}
				driver.switchTo().window(newWindowHandle);
				break;
			}else {
				browserSync.pause(1);
			}
		}
		if(currentHandle == newWindowHandle) {
			throw new RuntimeException("Time Out : No Window found");
		}
	}

	public void closeAllOpenWindow(String titleMainWindow) {
		logger.info("Closing all open windows..");
		Set<String> allWindowHandles = driver.getWindowHandles();
		
		for(String currentWindowHandle : allWindowHandles) {
			String windowTitle = driver.switchTo().window(currentWindowHandle).getTitle();
			if(windowTitle.equals(titleMainWindow)) {
				mainWindowHandle = currentWindowHandle;
			}else {
				driver.close();
			}	
		}
		driver.switchTo().window(mainWindowHandle);
	}
}
