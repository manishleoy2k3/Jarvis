package com.qaninjas.framework;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.ITestNGListener;
import org.testng.TestNG;

import com.qaninjas.framework.utility.report.ExtentManager;
import com.qaninjas.framework.utility.selenium.DotTestListener;

public class RunTestNG {

	public void executeTestNGXML(String xmlPath) {
		ExtentManager extentManager = ExtentManager.getInstance();
		TestNG testNG = new TestNG();
		File file = new File(xmlPath);
		String absolutePath = file.getAbsolutePath();
		List<String> suite = new ArrayList<String>();
		DotTestListener testListener = new DotTestListener();
		testNG.addListener((ITestNGListener) testListener);
		
		suite.add(absolutePath);
		testNG.setTestSuites(suite);
		testNG.run();
		extentManager.endReport();
	}
}
