package com.qaninjas.framework.utility.selenium;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Initialize;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.utility.report.ExtentReportConstant;
import com.qaninjas.selenium.Alerts;

public class DotTestListener implements ITestListener{

	private static Initialize instance = Initialize.getInstance();
	private static  Logger logger = Logger.getLogger(DotTestListener.class);
	private Report report = Report.getInstance();
	private ExtentReportConstant reportConstant = ExtentReportConstant.getInstance();
	
	String description = "";
	
	public void onTestStart(ITestResult result) {
		if(FrameworkConstants.HTML_REPORT) {
			getTestDescription(result);
			reportConstant.setTestCase(description);
			reportConstant.getTestCase().assignCategory(getGroupName(result));
		}
		logger.info("Test case start execution " + description);
		logger.info("Test case catgory is " + getGroupName(result));
	}

	public void onTestSuccess(ITestResult result) {
		report.log("Test case is Pass.", "", Status.PASS);
	}

	public void onTestFailure(ITestResult result) {
		logger.error(result.getThrowable().getMessage());
		if(result.getThrowable().getMessage() != "null") {
			report.log(result.getThrowable().getMessage(), "", Status.FAIL);	
		}else {
			report.log("Test case is Failed", "", Status.FAIL);
		}
	}

	public void onTestSkipped(ITestResult result) {
		report.log("Test case is skipped.", "", Status.SKIP);	
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub		
	}
	
	private String getTestDescription(ITestResult result) {
		try {
			description = result.getMethod().getDescription().trim();
			logger.info(result.getMethod().getDescription() + "Test case started");
		} catch (Exception e) {
			logger.info(result.getMethod().getDescription() + "Test case started");
			description = "Description not present. MethodName " + result.getName();
		}
		return description;
	}

	private String[] getGroupName(ITestResult result) {
		try {
			return result.getMethod().getGroups();
		} catch (Exception e) {
			return new String[] { "Un-Categories" };
		}
	}
}
