package com.qaninjas.keyword.parser;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.KeywordConstants;
import com.qaninjas.framework.utility.excel.ReadExcel;
import com.qaninjas.framework.utility.report.ExtentReportConstant;
import com.qaninjas.keyword.Keywords;

public class TestCaseExecutor {

	private List<String> keywordInfo = new ArrayList<String>();
	private static  Logger logger = Logger.getLogger(TestCaseExecutor.class);
	private KeywordConstants keyConst = new KeywordConstants();
	private ExtentReportConstant reportConst = ExtentReportConstant.getInstance();
	String filePath = null;
	private ReadExcel rdExcel = new ReadExcel();
	
	
	public void executeTestCase(String sheetName) {
		filePath = sheetName;
		for(Integer testCaseKey : keyConst.getTestCases().keySet()) {
			String tcId = keyConst.getTestCases().get(testCaseKey);
			executeTestDataset(tcId);
		}		
	}

	private void executeTestDataset(String tcId) {
		HashMap<Integer, String> keywords = keyConst.getTestCaseKeyword().get(tcId);
		testCaseDataSet(tcId);
		Set<String> dataSet = KeywordConstants.getTestDataSet().keySet();
		
		for(String dataSetCount : dataSet){
			
			KeywordConstants.setKeyWordData(KeywordConstants.getTestDataSet().get(dataSetCount));
			String scenarioName = KeywordConstants.getTestDataSet().get(dataSetCount).get("scenario");
			if(FrameworkConstants.HTML_REPORT) {
				String tcDescription = getTCDescription(tcId);
				reportConst.setTestCase(tcDescription + " - " + scenarioName);
			}
			
			for(Integer keyword : keywords.keySet()) {
				String testKeyword = keywords.get(keyword);
				executeTestCase(testKeyword, tcId);
			}
		}
	}

	private void executeTestCase(String keyword, String testCase) {
		keywordInfo = new ArrayList<String>();
		keywordInfo = keyConst.getTestCaseParseData().get(keyword);
		String className = keywordInfo.get(0);
		String methodName = keywordInfo.get(1);
		String category = keywordInfo.get(3);
		
		if(keywordInfo != null) {
			invokeMethod(className, methodName, keyword, category);
		}
	}

	private void invokeMethod(String className, String methodName, String keyword, String category) {
		logger.info("Test Case start Execution " + keyword);
		logger.info("Test Case category is " + getGroupName(category));
		
		reportConst.setParentTestNode(keyword);
		reportConst.getParentTestNode().assignCategory(category);
		
		try {
			Class<?> myClass = Class.forName(className);
			Method[] methods = myClass.getMethods();
			Object obj = myClass.newInstance();
			
			for(Method method : methods){
				Keywords anno = method.getAnnotation(Keywords.class);
				if(anno != null && anno.description().equalsIgnoreCase(keyword) && method.getName().equalsIgnoreCase(keyword)){
					method.invoke(obj);
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private String getGroupName(String arg0) {
		if(arg0.isEmpty()) {
			return "Un-Categories";
		} else {
			return arg0;
		}
	}

	private String getTCDescription(String tcId) {
		Set<String> keys = KeywordConstants.getTestSet().keySet();
		String testDescription = "";
		
		for(String key : keys) {
			if(KeywordConstants.getTestSet().get(key).get("TestCase Id").equalsIgnoreCase(tcId)) {
				testDescription = KeywordConstants.getTestSet().get(key).get("TestCase Description").toString();
				break;
			}
		}
		return testDescription;
	}

	private void testCaseDataSet(String tcId) {
		HashMap<String, HashMap<String, String>> testSet = rdExcel.getDataWithMap(filePath, "Data-" + tcId);
		Set<String> rows = testSet.keySet();
		KeywordConstants.initSetTestDataSet();
		
		int count = 1;
		
		for(String rowNo : rows){
			if(testSet.get(rowNo).get("Execute").equalsIgnoreCase("yes")){
				KeywordConstants.setTestDataSet(Integer.toString(count), testSet.get(rowNo));
				count++;
			}
		}		
	}
}
