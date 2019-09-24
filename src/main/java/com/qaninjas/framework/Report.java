package com.qaninjas.framework;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.ReportConstants;
import com.qaninjas.framework.utility.report.ExtentReportConstant;
import com.qaninjas.selenium.ScreenShot;

public class Report {

	private static Report instance = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(Report.class);
	private ExtentReportConstant reportConstant = ExtentReportConstant.getInstance();
	private ScreenShot screenShot = new ScreenShot();
	private String appendScreenShot = "";
	
	
	protected Report() {
		
	}
		
	public static Report getInstance() {
		if(null == instance) {
			instance = new Report();
		}
		return instance;
	}
	
	public void log(String message, String value, WebElement element, Status status) {
		//reportConstant.getParentTestNode();
		String screenShotPath = "";
		FrameworkConstants.STEP_COUNTER++;
		logger.info("Step : " + FrameworkConstants.STEP_COUNTER + " : " + message + " Parameter : " + value);
		
		if(ReportConstants.SCREEN_SHOT) {
			screenShotPath = screenShot.takeSnapShot(driver, element);
			appendScreenShot = "<td nowrap>" + "<a href=\"" + ReportConstants.SCREENSHOT_LOCATION + File.separator
					+ screenShotPath + "\"> Screenshot</a></td>";
			if(FrameworkConstants.HTML_REPORT) {
				setStepStatus(message, value, status);
			}
		} else {
			if(FrameworkConstants.HTML_REPORT) {
				setStepStatus(message, value, status);
			}
		}
	}

	public void log(String message, String value, Status status) {
		reportConstant.getParentTestNode();
		String screenShotPath = "";
		FrameworkConstants.STEP_COUNTER++;
		logger.info("Step : " + FrameworkConstants.STEP_COUNTER + " : " + message + " Parameter : " + value);
		
		if(ReportConstants.SCREEN_SHOT) {
			screenShotPath = screenShot.takeSnapShot(driver);
			appendScreenShot = "<td nowrap>" + "<a href=\"" + ReportConstants.SCREENSHOT_LOCATION + File.separator
					+ screenShotPath + "\"> Screenshot</a></td>";
			if(FrameworkConstants.HTML_REPORT) {
				setStepStatus(message, value, status);
			}
		} else {
			if(FrameworkConstants.HTML_REPORT) {
				setStepStatus(message, value, status);
			}
		}
	}

	public void setStepStatus(String message, String value, Status status) {
		if(FrameworkConstants.GLOBALCONFIG.get("KEYWORD_EXECUTION").equalsIgnoreCase("yes")){
			reportConstant.getParentTestNode().log(status, message + "<span style='font-weight:bold;'>" + "" + value + "</span>" + appendScreenShot);
		} else {
			reportConstant.getTestCase().log(status, message + "<span style='font-weight:bold;'>" + "" + value + "</span>" + appendScreenShot);
		}
	}

	public void setNodeStatus(String message, String value, Status status) {
		reportConstant.getChildNode().log(status, message + "<span style='font-weight:bold;'>" + "" + value + "</span>" + appendScreenShot);
	}
}
