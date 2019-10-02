package com.qaninjas.unittest.desktop.browser.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.utility.FileUpload;
import com.qaninjas.framework.utility.report.ExtentManager;
import com.qaninjas.test.BaseBrowserTest;
import com.qaninjas.unittest.mobile.browser.pages.UnitTestPages;

//@Listeners(com.qaninjas.framework.utility.selenium.DotTestListener.class)
public class FileUploadTest extends BaseBrowserTest implements UnitTestPages {

	private WebDriver driver = DriverFactory.getInstance().getDriver();
	private ExtentManager extentManager = ExtentManager.getInstance();
	private FileUpload fileUpload = new FileUpload();
	
	By button = By.xpath("//input[@type='file']");
	
	@Test(description = "")
	public void sendTextToTextBox(){
		driver.get("https://tus.io/demo.html");
		//fileUpload.fileUpload(button, FrameworkConstants.DESKTOPCONFIG.get("FILE_PATH"));
		fileUpload.fileUpload(button, "C:\\Users\\manish\\Desktop\\Download\\DefenceEnclaveLease.docx");
	}
	
	@AfterSuite
	public void tearDown(){
		extentManager.endReport();
		driver.quit();		
	}
}
