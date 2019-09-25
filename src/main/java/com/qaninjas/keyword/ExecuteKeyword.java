package com.qaninjas.keyword;

import java.io.File;

import org.apache.log4j.Logger;

import com.qaninjas.framework.Initialize;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.utility.report.ExtentManager;
import com.qaninjas.keyword.parser.TestCaseExecutor;
import com.qaninjas.keyword.parser.TestParser;

public class ExecuteKeyword extends TestParser{

	private static  Logger logger = Logger.getLogger(ExecuteKeyword.class);
	private KeywordData keyData = new KeywordData();
	private TestCaseExecutor testCaseExecutor = new TestCaseExecutor();
	ExtentManager extentManager;
	
	public void execute(String packageName, String sheetName) {
		try {
			extentManager = ExtentManager.getInstance();
			logger.debug("Parsing testcase from " + packageName + " package.");
			parseMethods(packageName);
			keyData.loadTestCaseData(sheetName);
			testCaseExecutor.executeTestCase(sheetName);
			extentManager.endReport();
		} catch (Exception e) {
			logger.info("Execution stopped due to " + e.getStackTrace());
		} finally {
			extentManager.endReport();
		}
	}
	
	public void startExecution() {
		Initialize.getInstance();
		String folderPath = System.getProperty("user.dir") + File.separator
				+ FrameworkConstants.KEYWORDCONFIG.get("EXCEL_PATH");
		String sheets = FrameworkConstants.KEYWORDCONFIG.get("SHEET_NAME");
		String[] executionSheets = sheets.split(",");
		for(int sheet = 0; sheet < executionSheets.length; sheet++) {
			execute(FrameworkConstants.KEYWORDCONFIG.get("TestCases"), folderPath + File.separator + executionSheets[sheet]);
		}
	}
}