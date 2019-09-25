package com.qaninjas.api.utils;

import java.io.FileReader;
import java.io.FileWriter;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.aventstack.extentreports.Status;
import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class JsonHandler implements IGeneric{

	private Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
			.mappingProvider(new JacksonMappingProvider()).build();
	private static Logger logger = Logger.getLogger(JsonHandler.class);
	private static JsonHandler instance = null;
	private String primitiveTypeSubString[] = null;
	private JsonNode updatedJson = null;
	
	private enum primitiveTypes{
		INTEGER, FLOAT, DOUBLE, STRING, LONG, BOOLEAN, NULL
	}
	
	protected JsonHandler() {
		
	}
	
	public static JsonHandler getInstance() {
		if(null == instance) {
			instance = new JsonHandler();
		}
		return instance;
	}
	
	// writes JSON content to File
	public void writeJsonToFile(JSONObject jsonObject, String fileName) {
		writeJsonToFile(jsonObject.toJSONString(), fileName);
		reportConstant.getParentTestNode().log(Status.PASS, jsonObject.toJSONString());
	}

	// writes JSON string to File
	public void writeJsonToFile(String jsonString, String fileName) {
		try {
			FileWriter file = new FileWriter(fileName);
			file.write(jsonString);
			file.close();
			logger.info("Json value written to File: " + jsonString);
			if(reportConstant.getParentTestNode() != null){
				reportConstant.getParentTestNode().log(Status.PASS, jsonString);
			}else {
				reportConstant.setParentTestNode("Write to JSON file");
				reportConstant.getParentTestNode().log(Status.PASS, jsonString);
			}
			
		} catch(Exception e) {
			logger.error("Exception found: "+ e.getMessage());
			util.failTestCase(e.getMessage(), e.getMessage());
		}
	}
	
	// read JSON content from File based on JSON path
	public String readJsonFromFile(String fileName, String jsonPath) {
		String jsonvalue = "";
		try {
			jsonvalue = JsonPath.using(configuration).parse(readJsonFromFile(fileName)).read("$." + jsonPath).toString();
			logger.info("Json value read from File: " + jsonvalue);
			reportConstant.getParentTestNode().log(Status.PASS, jsonPath);
		} catch(Exception e) {
			logger.error("Exception found: "+ e.getMessage());
			util.failTestCase(e.getMessage(), e.getMessage());
		}
		return jsonvalue;
	}

	public String readJsonFromFile(String filePath) {
		JSONObject jsobObject = null;
		
		try {
			jsobObject =  (JSONObject) new JSONParser().parse(new FileReader(filePath));
			logger.info("Json value read from file: "+ jsobObject);
		} catch(Exception e) {
			logger.error("Exception found: "+ e.getMessage());
			logger.debug(e.getStackTrace());
			reportConstant.getParentTestNode().log(Status.FAIL, e.getMessage());
		}
		return jsobObject.toJSONString();
	}
	
	//modify the JSON content based on JSON path
	public String modifyJson(String jsonSource, String jsonPath, Object value) {
		try {
			primitiveTypeSubString = value.getClass().getName().split("java.lang.");
		} catch(Exception e) {
			e.getMessage();
			primitiveTypeSubString = "java.lang.NULL".split("java.lang.");
		}
		switch(primitiveTypes.valueOf(primitiveTypeSubString[1].toUpperCase())) {
		
		case LONG:
			updatedJson = JsonPath.using(configuration).parse(jsonSource).set("$." + jsonPath, (Long) value).json();
			logger.info("Modified Json: " + updatedJson);
			return updatedJson.toString();
		case INTEGER:
			updatedJson = JsonPath.using(configuration).parse(jsonSource).set("$." + jsonPath, (Integer) value).json();
			logger.info("Modified Json: " + updatedJson);
			return updatedJson.toString();
		case FLOAT:
			updatedJson = JsonPath.using(configuration).parse(jsonSource).set("$." + jsonPath, (Float) value).json();
			logger.info("Modified Json: " + updatedJson);
			return updatedJson.toString();
		case DOUBLE:
			updatedJson = JsonPath.using(configuration).parse(jsonSource).set("$." + jsonPath, (Double) value).json();
			logger.info("Modified Json: " + updatedJson);
			return updatedJson.toString();
		case STRING:
			updatedJson = JsonPath.using(configuration).parse(jsonSource).set("$." + jsonPath, (String) value).json();
			logger.info("Modified Json: " + updatedJson);
			return updatedJson.toString();
		case BOOLEAN:
			updatedJson = JsonPath.using(configuration).parse(jsonSource).set("$." + jsonPath, (Boolean) value).json();
			logger.info("Modified Json: " + updatedJson);
			return updatedJson.toString();
		case NULL:
			updatedJson = JsonPath.using(configuration).parse(jsonSource).set("$." + jsonPath, value).json();
			logger.info("Modified Json: " + updatedJson);
			return updatedJson.toString();
			
		}
		return updatedJson.toString();
	}
	
	//modify the JSON content based on JSON path
	public String modifyJsonFromFile(String jsonSourceFileName, String jsonPath, Object value) {
		
		try {
			primitiveTypeSubString = value.getClass().getName().split("java.lang.");
		} catch(Exception e) {
			e.getMessage();
			primitiveTypeSubString = "java.lang.NULL".split("java.lang.");
		}
		switch(primitiveTypes.valueOf(primitiveTypeSubString[1].toUpperCase())) {
		
		case LONG:
			updatedJson = JsonPath.using(configuration).parse(readJsonFromFile(jsonSourceFileName)).set("$." + jsonPath, (Long) value).json();
			writeJsonToFile(updatedJson.toString(), jsonSourceFileName);
			logger.info("File modified successfully and the updated Json is : " + updatedJson);
			return updatedJson.toString();
		case INTEGER:
			updatedJson = JsonPath.using(configuration).parse(readJsonFromFile(jsonSourceFileName)).set("$." + jsonPath, (Integer) value).json();
			writeJsonToFile(updatedJson.toString(), jsonSourceFileName);
			logger.info("File modified successfully and the updated Json is : " + updatedJson);
			return updatedJson.toString();
		case FLOAT:
			updatedJson = JsonPath.using(configuration).parse(readJsonFromFile(jsonSourceFileName)).set("$." + jsonPath, (Float) value).json();
			writeJsonToFile(updatedJson.toString(), jsonSourceFileName);
			logger.info("File modified successfully and the updated Json is : " + updatedJson);
			return updatedJson.toString();
		case DOUBLE:
			updatedJson = JsonPath.using(configuration).parse(readJsonFromFile(jsonSourceFileName)).set("$." + jsonPath, (Double) value).json();
			writeJsonToFile(updatedJson.toString(), jsonSourceFileName);
			logger.info("File modified successfully and the updated Json is : " + updatedJson);
			return updatedJson.toString();
		case STRING:
			updatedJson = JsonPath.using(configuration).parse(readJsonFromFile(jsonSourceFileName)).set("$." + jsonPath, (String) value).json();
			writeJsonToFile(updatedJson.toString(), jsonSourceFileName);
			logger.info("File modified successfully and the updated Json is : " + updatedJson);
			return updatedJson.toString();
		case BOOLEAN:
			updatedJson = JsonPath.using(configuration).parse(readJsonFromFile(jsonSourceFileName)).set("$." + jsonPath, (Boolean) value).json();
			writeJsonToFile(updatedJson.toString(), jsonSourceFileName);
			logger.info("File modified successfully and the updated Json is : " + updatedJson);
			return updatedJson.toString();
		case NULL:
			updatedJson = JsonPath.using(configuration).parse(readJsonFromFile(jsonSourceFileName)).set("$." + jsonPath, value).json();
			writeJsonToFile(updatedJson.toString(), jsonSourceFileName);
			logger.info("File modified successfully and the updated Json is : " + updatedJson);
			return updatedJson.toString();
		}
		return updatedJson.toString();
	}
}
