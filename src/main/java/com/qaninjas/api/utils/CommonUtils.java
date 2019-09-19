package com.qaninjas.api.utils;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.utility.report.ExtentReportConstant;

public class CommonUtils {

	private static CommonUtils instance = null;
	private ExtentReportConstant reportConstant = ExtentReportConstant.getInstance();
	
	protected CommonUtils() {
		
	}
		
	public static CommonUtils getInstance() {
		if(null == instance) {
			instance = new CommonUtils();
		}
		return instance;
	}

	public void failTestCase(String exceptionMsg, String failMsgDetails) {
		if(reportConstant.getParentTestNode() == null) {
			reportConstant.setParentTestNode("Faiure Details...");
			reportConstant.getParentTestNode().log(Status.FAIL, failMsgDetails);
			Assert.fail(exceptionMsg);
		}else {
			reportConstant.getParentTestNode().log(Status.FAIL, failMsgDetails);
			Assert.fail(exceptionMsg);
		}
	}

}
