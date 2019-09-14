package com.qaninjas.framework.constants;

import java.util.HashMap;
import java.util.List;

public class KeywordConstants {

	private static HashMap<String, List<String>> testCaseParseData = new HashMap<String, List<String>>();
	private static HashMap<String, HashMap<Integer, List<String>>> keywordInput = new HashMap<String, HashMap<Integer, List<String>>>();
	private static HashMap<Integer, String> testCases = new HashMap<Integer, String>();
	private static HashMap<String, HashMap<Integer, String>> testCaseKeyword = new HashMap<String, HashMap<Integer, String>>();
	private static HashMap<String, HashMap<String, String>> testSet = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, HashMap<String, String>> testDataSet = new HashMap<String, HashMap<String, String>>();
	private static HashMap<String, String> keywordData = new HashMap<String, String>();
	
	public static HashMap<String, String> getKeywordData(){
		return keywordData;
	}
	
	public static void setKeyWordData(HashMap<String, String> keyWordData) {
		KeywordConstants.keywordData = new HashMap<String, String>();
		KeywordConstants.keywordData = keyWordData;
	}
	
	public static HashMap<String, HashMap<String, String>> getTestDataSet(){
		return testDataSet;
	}

	public static void setTestDataSet(String row, HashMap<String, String> hashmap) {
		KeywordConstants.testDataSet.put(row, hashmap);
	}
	
	public static void initSetTestDataSet() {
		KeywordConstants.testDataSet = new HashMap<String, HashMap<String,String>>();
	}
	
	public static HashMap<String, HashMap<String, String>> getTestSet(){
		return testSet;
	}
	
	public static void setTestSet(String row, HashMap<String, String> hashmap) {
		KeywordConstants.testSet.put(row, hashmap);
	}
	
	public HashMap<String, HashMap<Integer, List<String>>> getKeywordInput(){
		return keywordInput;
	}
	
	public static void setKeywordInput(HashMap<String, HashMap<Integer, List<String>>> keywordInput) {
		KeywordConstants.keywordInput = keywordInput;
	}
	
	public HashMap<Integer, String> getTestCases(){
		return testCases;
	}
	
	public static void setTestCases(HashMap<Integer, String> testCases) {
		KeywordConstants.testCases = testCases;
	}
	
	public HashMap<String, HashMap<Integer, String>> getTestCaseKeyword(){
		return testCaseKeyword;
	}
	
	public static void setTestCaseKeyword(HashMap<String, HashMap<Integer, String>> testCaseKeyword) {
		KeywordConstants.testCaseKeyword = testCaseKeyword;
	}
	
	public HashMap<String, List<String>> getTestCaseParseData(){
		return testCaseParseData;
	}
	
	public static void setTestCaseParseData(HashMap<String, List<String>> testCaseParseData) {
		KeywordConstants.testCaseParseData = testCaseParseData;
	}
	
	private boolean verifyDuplicateKeyword(String key, List<String> testCaseParseData2) {
		return true;
	}
	
	public void updateTestCaseParseData(String key, List<String> testCaseClassParseData) throws Exception {
		if(verifyDuplicateKeyword(key, testCaseClassParseData)) {
			testCaseParseData.put(key, testCaseClassParseData);
			setTestCaseParseData(testCaseParseData);
		}else {
			throw new Exception("Duplicate keyword found.");
		}
	}
	
	public static HashMap<String, String> getScenarioDataSet(String scenario){
		HashMap<String, String> scenarioDataSet = new HashMap<String, String>();
		for(HashMap<String, String> dataset : getTestDataSet().values()) {
			if(testDataSet.containsValue(scenario)) {
				scenarioDataSet = dataset;
				break;
			}
		}
		return scenarioDataSet;
	}
}
