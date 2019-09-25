package com.qaninjas.framework.utility;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.winium.DesktopOptions;
import org.openqa.selenium.winium.WiniumDriver;
import org.openqa.selenium.winium.WiniumDriverService;

import com.qaninjas.driverfactory.DriverFactory;

public class FileUpload {

	WiniumDriver d;
	private WiniumDriverService service;
	
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(FileUpload.class);
	
	public void fileUpload(By locator, String uploafFilePath) {
		try {
			startService();
			driver.findElement(locator).click();
			d.findElement(By.name("path")).sendKeys(uploafFilePath);
			d.findElement(By.name("Open")).click();
			logger.info("File uploaded successfully");
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		stopService();
	}

	private void stopService() {
		try {
			if(null == service) {
				service = new WiniumDriverService.Builder()
						.usingDriverExecutable(new File(System.getProperty("user.dir")
								+ "/src/main/resource/lib/Winium.Desktop.Driver.exe"))
						.usingPort(9999).withVerbose(true).buildDesktopService();
				service.start();
				DesktopOptions options = new DesktopOptions();
				options.setApplicationPath("C:\\Windows\\System32\\notepad.exe");
				d = new WiniumDriver(service, options);
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}

	private void startService() {
		try {
			if(null != service && service.isRunning()) {
				d.close();
				d.quit();
				service.stop();
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}
