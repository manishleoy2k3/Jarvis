package com.qaninjas.framework.utility.report;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ExtentReportConstant {

	private ExtentTest parentTestNode;
	private ExtentReports extent;
	private static ExtentReportConstant instance = null;
	public ExtentTest testcase;
	public ExtentTest childNode;
	
	protected ExtentReportConstant() {

	}
	
	public static ExtentReportConstant getInstance() {
		if(null == instance) {
			instance = new ExtentReportConstant();
			ExtentManager.getInstance().getExtent();
		}
		return instance;
	}

	public void SetExtent(ExtentReports extent) {
		this.extent = extent;
	}
	
	public ExtentReports getExtent() {
		return extent;
	}

	public ExtentTest getTestCase() {
		return testcase;
	}
	
	public void  setTestCase(String testName) {
		this.testcase = getExtent().createTest(testName);
	}
	
	public ExtentTest getParentTestNode() {
		return parentTestNode;
	}
	
	public void  setParentTestNode(String parentNode) {
		this.parentTestNode = getTestCase().createNode(parentNode);
	}
	
	public ExtentTest getChildNode() {
		return childNode;
	}
	
	public void  setChildNode(String childNode) {
		this.childNode = getTestCase().createNode(childNode);
	}
}
