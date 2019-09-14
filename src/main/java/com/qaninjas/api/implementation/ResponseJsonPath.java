package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IResponseJsonPath;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.spi.json.JacksonJsonNodeJsonProvider;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

public class ResponseJsonPath implements IResponseJsonPath, IGeneric{

	private String jsonPath;
	private static ResponseJsonPath instance = null;
	private static  Logger logger = Logger.getLogger(ResponseJsonPath.class);
	
	private ResponseBuilder responseBuilder = ResponseBuilder.getResponseBuilder();
	private Configuration configuration = Configuration.builder().jsonProvider(new JacksonJsonNodeJsonProvider())
			.mappingProvider(new JacksonMappingProvider()).build();
	
	private enum jsonNodeTypes{
		DOUBLENODE, INTNODE, LONGNODE, BOOLEANNODE, TEXTNODE, NULLNODE
	}
			
	protected ResponseJsonPath() {
		
	}
	
	public static ResponseJsonPath getInstance() {
		if(null == instance) {
			instance = new ResponseJsonPath();
		}
		return instance;
	}

	/**
	 * @return the jsonPath
	 */
	public String getJsonPath() {
		return jsonPath;
	}

	/**
	 * @param jsonPath the jsonPath to set
	 */
	public void setJsonPath(String jsonPath) {
		this.jsonPath = jsonPath;
	}

	public void isEqualTo(boolean compareType, Object expectedValue) {
		logger.debug("Expected Value: " + expectedValue);
		compare.isEqualTo(compareType, expectedValue, (Object) getFieldValue());
	}

	
	public void fieldLengthEqualTo(int expectedFieldLength) {
		logger.debug("Actual Field length: " + (getFieldValue().toString().length()));
		logger.debug("Expected Field length: " + expectedFieldLength);
		logger.info("Validating Json Field Length: ");
		compare.isEqualTo(true, (Object) expectedFieldLength, (Object) (getFieldValue().toString().length()));		
	}

	public void fieldLengthGreatOrEqualTo(int expectedFieldLength) {
		logger.debug("Actual Field length: " + (getFieldValue().toString().length()));
		logger.debug("Expected Field length: " + expectedFieldLength);
		logger.info("Validating Json Field Length: ");
		compare.greaterOrEqualsTo((Object) expectedFieldLength, (Object) (getFieldValue().toString().length()));	
	}

	public void fieldLengthLessOrEqualTo(int expectedFieldLength) {
		logger.debug("Actual Field length: " + (getFieldValue().toString().length()));
		logger.debug("Expected Field length: " + expectedFieldLength);
		logger.info("Validating Json Field Length: ");
		compare.lessOrEqualsTo((Object) expectedFieldLength, (Object) (getFieldValue().toString().length()));
	}

	public void contains(String expectedValue) {
		logger.debug("Expected Value: " + expectedValue);
		compare.contains(expectedValue, (String) getFieldValue());		
	}

	public void isLessOrEqualTo(Object expectedValue) {
		logger.debug("Expected Value: " + expectedValue);
		compare.lessOrEqualsTo(expectedValue, (Object) getFieldValue());			
	}

	public void isGreaterOrEqualTo(Object expectedValue) {
		logger.debug("Expected Value: " + expectedValue);
		compare.greaterOrEqualsTo(expectedValue, (Object) getFieldValue());		
	}

	public Object getFieldValue() {
		Object jsonValobj = "";
		
		try {
			logger.debug("Actual value read from Json Path: " + JsonPath.using(configuration).parse(responseBuilder.getResponse().asString()).read("$." + getJsonPath().toString().trim()));
			Object jobj = JsonPath.using(configuration).parse(responseBuilder.getResponse().asString()).read("$." + getJsonPath());
							
			String jsonNodeTypeSubString[] = jobj.getClass().getName().split("databind.node.");
			
			switch(jsonNodeTypes.valueOf(jsonNodeTypeSubString[1].toUpperCase()))
			{
			case DOUBLENODE:
				return (Object) ((DoubleNode)jobj).asDouble();
			case INTNODE:
				return (Object) ((DoubleNode)jobj).asInt();
			case LONGNODE:
				return (Object) ((DoubleNode)jobj).asLong();
			case BOOLEANNODE:
				return (Object) ((DoubleNode)jobj).asBoolean();
			case TEXTNODE:
				return (Object) ((DoubleNode)jobj).asText();
			case NULLNODE:
				jsonValobj = "";
				return jsonValobj;
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get json field value failed");
		}
		
		return jsonValobj;
	}
}