package com.qaninjas.accessibility.framework;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.qaninjas.driverfactory.DriverFactory;
import com.qaninjas.framework.Report;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.utility.excel.UpdateExcel;
import com.qaninjas.framework.utility.report.ExtentReportConstant;

public class AccessibilityLibrary {

	URL url = null;
	protected WebDriver driver = DriverFactory.getInstance().getDriver();
	private static  Logger logger = Logger.getLogger(AccessibilityLibrary.class);
	
	private ExtentReportConstant reportConstant = ExtentReportConstant.getInstance();
	private Report report = Report.getInstance();
	private AccessibilityUtils accessibilityUtils = new AccessibilityUtils();
	UpdateExcel updateExcel = new UpdateExcel();
	
	private static AccessibilityLibrary instance = null;
	
	String voilations;
	
	protected AccessibilityLibrary() {
		
	}
		
	public static AccessibilityLibrary getInstance() {
		if(null == instance) {
			instance = new AccessibilityLibrary();
		}
		return instance;
	}

	public void elementLevelAccessibilityTest(WebElement element) {
		if(FrameworkConstants.ACCESSIBILITY_TEST)
			if(FrameworkConstants.ACCESSCONFIG.get("ACCESSIBILITY_ELEMENT_TEST").equalsIgnoreCase("yes")){
				getWCAGVersion();
				logger.debug("The Accessibility Test is enabled");
				JSONObject obj = new Builder(driver, url).analyze();
				JSONArray arr = obj.getJSONArray("voilations");
				reportConstant.setParentTestNode("Element Level Accessibility Test logs for : " + driver.getTitle());
				
				if(arr.length() == 0){
					logger.info("Accessibility voilations were not found for passed Webelement.");
					reportConstant.getParentTestNode().createNode("element level voilations.")
					.log(Status.PASS, driver.getTitle() + " No accessibility voilations found for element");
				} else {
					logger.info("Accessibility voilations have been found and captured in the report for Webelement.");
					voilations = accessibilityUtils.htmlReport(arr);
					List<String> voilationList = new ArrayList<String>(
							Arrays.asList(voilations.toString().trim().split("<--JSTORM REPORT-->")));
					
					for(int count = 1; count < voilationList.size(); count++){
						reportConstant.getParentTestNode();
						reportConstant.setChildNode("Element Voilation " + count);
						reportConstant.getChildNode().log(Status.FAIL, count + ")" 
						+ voilationList.get(count).substring(0, voilationList.get(count).length() - 2));
					}
				}
			}	
	}

	public void pageLevelAccessibilityTest() {
		if(FrameworkConstants.ACCESSIBILITY_TEST)
			if(FrameworkConstants.ACCESSCONFIG.get("ACCESSIBILITY_PAGE_TEST").equalsIgnoreCase("yes")){
				getWCAGVersion();
				logger.debug("The Accessibility Test is enabled");
				JSONObject obj = new Builder(driver, url).analyze();
				JSONArray voilationArray = obj.getJSONArray("voilations");
				reportConstant.setParentTestNode("Page Level Accessibility Test logs for : " + driver.getTitle());
				
				if(voilationArray.length() == 0){
					logger.info("Accessibility voilations were not found on the page.");
					reportConstant.getParentTestNode().createNode("No Accessibility voilations found.")
					.log(Status.PASS, driver.getTitle() + " doesn't have accessibility page voilations");
				} else {
					logger.info("Accessibility voilations have been found and captured in the report.");
					accessibilityUtils.excelReport(voilationArray);
					voilations = accessibilityUtils.htmlReport(voilationArray);
					List<String> voilationList = new ArrayList<String>(
							Arrays.asList(voilations.toString().trim().split("<--JSTORM REPORT-->")));
					
					for(int count = 1; count < voilationList.size(); count++){
						reportConstant.getParentTestNode();
						reportConstant.setChildNode("Page Voilation " + count);
						reportConstant.getChildNode().log(Status.FAIL, count + ")" 
						+ voilationList.get(count).substring(0, voilationList.get(count).length() - 2));
					}
				}
			}
	}

	public void getWCAGVersion() {
		String version = FrameworkConstants.ACCESSCONFIG.get("ACCESSIBILITY_VERSION");
		switch(version) {
		case "2.0":
			url = AccessibilityLibrary.class.getResource("/wcagrules/WCAG2.0.js");
			break;
		case "2.1":
			url = AccessibilityLibrary.class.getResource("/wcagrules/WCAG2.1.js");
			break;
		default:
			url = AccessibilityLibrary.class.getResource("/wcagrules/WCAG2.0.js");
		}
		logger.info("Accessibility test will run on WCAG " + version);
	}
}
