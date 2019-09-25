package com.qaninjas.keyword.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.qaninjas.framework.constants.KeywordConstants;
import com.qaninjas.framework.utility.excel.ReadExcel;
import com.qaninjas.framework.utility.report.ExtentReportConstant;

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
		// TODO Auto-generated method stub
		
	}

}
