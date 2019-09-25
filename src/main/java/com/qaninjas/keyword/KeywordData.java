package com.qaninjas.keyword;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Sheet;
import org.testng.Assert;

import com.qaninjas.framework.constants.KeywordConstants;
import com.qaninjas.framework.utility.excel.ReadExcel;

public class KeywordData {

	private ReadExcel rdExcel = new ReadExcel();
	String filePath = null;
	private static Sheet sheet;
	
	private static  Logger logger = Logger.getLogger(KeywordData.class);
	
	private static HashMap<String, HashMap<Integer, List<String>>> keywordInput = new HashMap<String, HashMap<Integer,List<String>>>();
	private static HashMap<Integer, String> testCases = new HashMap<Integer, String>();
	private static HashMap<String, HashMap<Integer, String>> testCaseKeyword = new HashMap<String, HashMap<Integer,String>>();
	private KeywordConstants keyConst = new KeywordConstants();
	
	public void loadTestCaseData(String sheetName) {
		filePath = sheetName;
		getTestSet();
		getTestCaseData();
	}

	private void getTestCaseData() {
		getExcelData(filePath, "TestCases");
	}

	private void getTestSet() {
		HashMap<String, HashMap<String, String>> testSet = rdExcel.getDataWithMap(filePath, "TestSet");
		Set<String> rows = testSet.keySet();
		int count = 1;
		for(String rowNo : rows) {
			if(testSet.get(rowNo).get("Execute").equalsIgnoreCase("yes")) {
				KeywordConstants.setTestSet(Integer.toString(count), testSet.get(rowNo));
				count++;
			}
		}				
	}

	private void getExcelData(String filePath, String sheetName) {
		int tcCount = 1;
		int keyCount = 1;
		
		String testCaseId = "";
		
		try {
			sheet = rdExcel.getSheet(filePath, sheetName);
			int totalRows = sheet.getLastRowNum();
			HashMap<Integer, String> keyword = new HashMap<Integer, String>();
			HashMap<Integer, List<String>> inputKeyword = new HashMap<Integer, List<String>>();
			
			for(int rowCount = 2; rowCount < totalRows; rowCount++) {
				if(!rdExcel.getCellValue(sheet.getRow(rowCount).getCell(0)).isEmpty()) {
					testCaseId = rdExcel.getCellValue(sheet.getRow(rowCount).getCell(0));
					testCases.put(tcCount, testCaseId);
					tcCount++;
					keyCount = 1;
					keyword = new HashMap<Integer, String>();
					inputKeyword = new HashMap<Integer, List<String>>();
					testCaseKeyword.put(testCaseId, keyword);
					keywordInput.put(testCaseId, inputKeyword);
				}
				keyword.put(keyCount, rdExcel.getCellValue(sheet.getRow(rowCount).getCell(1)));
				
				List<String> inputValue = parseRow(rowCount);
				inputKeyword.put(keyCount, inputValue);
				keyCount++;
			}
		} catch (Exception e) {
			logger.error("Not able to retrieve sheetName");
			Assert.fail("Not able to retrieve sheetName" + e.getMessage());
		}		
		updateValidOption();
	}

	private void updateValidOption() {
		Set<String> testSetKeys = KeywordConstants.getTestSet().keySet();
		
		for(String testSets : testSetKeys) {
			String tcID = KeywordConstants.getTestSet().get(testSets).get("TestCase Id");
			removeTestCases(tcID);
			removeKeywordInput(tcID);
			removeTestCaseKeyword(tcID);
		}
	}

	private void removeTestCaseKeyword(String tcID) {
		HashMap<Integer, String> temp = new HashMap<Integer, String>();
		int count = 1;
		Set<Integer> keys = testCases.keySet();
		for(Integer key : keys) {
			if(testCases.get(key).equalsIgnoreCase(tcID)) {
				temp.put(count, testCases.get(key));
			}
		}
		keyConst.setTestCases(temp);
	}

	private void removeKeywordInput(String tcID) {
		HashMap<String, HashMap<Integer, List<String>>> temp = new HashMap<String, HashMap<Integer,List<String>>>();
		Set<String> keys = keywordInput.keySet();
		for(String key : keys) {
			if(key.equalsIgnoreCase(tcID)) {
				temp.put(key, keywordInput.get(key));
			}
		}
		keyConst.setKeywordInput(temp);
	}

	private void removeTestCases(String tcID) {
		HashMap<String, HashMap<Integer, String>> temp = new HashMap<String, HashMap<Integer, String>>();
		Set<String> keys = testCaseKeyword.keySet();
		for(String key : keys) {
			if(key.equalsIgnoreCase(tcID)) {
				temp.put(key, testCaseKeyword.get(key));
			}
		}
		keyConst.setTestCaseKeyword(temp);
	}
	
	private List<String> parseRow(int rowCount){
		int totalColumns = 0;
		List<String> inputs = new ArrayList<String>();
		for(int columnCount = 2; columnCount < totalColumns; columnCount++){
			String value = rdExcel.getCellValue(sheet.getRow(rowCount).getCell(columnCount));
			inputs.add(value);
		}
		return inputs;
	}
}