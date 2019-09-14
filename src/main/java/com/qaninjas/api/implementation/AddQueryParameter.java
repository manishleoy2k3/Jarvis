package com.qaninjas.api.implementation;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IAddParameter;
import com.qaninjas.framework.api.interfaces.IAddQueryParameters;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class AddQueryParameter implements IAddQueryParameters, IGeneric{

	private static AddQueryParameter instance = null;
	private static  Logger logger = Logger.getLogger(AddQueryParameter.class);
	
		
	protected AddQueryParameter() {
		
	}
	
	public static AddQueryParameter getInstance() {
		if(null == instance) {
			instance = new AddQueryParameter();
		}
		return instance;
	}

	public IAddParameter addParameter(String paramKey) {
		try {
			if(!(paramKey == null)) {
				logger.debug("Setting query parameter key");
				addParameter.setParameterKey(paramKey);
				logger.debug("Setting query parameter Type");
				addParameter.setParameterType("QUERY");
			}else {
				util.failTestCase("Parameter Key cannot be set to null", "Parameter Key cannot be set to null");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Parameter Key failed");
		}
		return addParameter;
	}

	public void addParameterMap(HashMap<String, String> queryParametersHashMap) {
		try {
			if(!(queryParametersHashMap.isEmpty())) {
				logger.debug("Adding query parameters map to request");
				requestBuilder.getRequestSpecBuilder().addQueryParams(queryParametersHashMap);
				reportConstant.getParentTestNode().log(Status.PASS, "Query Parameters added");
			}else {
				util.failTestCase("Query Parameter Map cannot be empty", "Query Parameter Map cannot be empty");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Query Parameters failed");
		}		
	}
}
