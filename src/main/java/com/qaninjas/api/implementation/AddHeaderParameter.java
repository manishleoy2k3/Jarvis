package com.qaninjas.api.implementation;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IAddHeadersParameters;
import com.qaninjas.framework.api.interfaces.IAddParameter;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class AddHeaderParameter implements IAddHeadersParameters, IGeneric{

	private static AddHeaderParameter instance = null;
	private static  Logger logger = Logger.getLogger(AddHeaderParameter.class);
	
		
	protected AddHeaderParameter() {
		
	}
	
	public static AddHeaderParameter getInstance() {
		if(null == instance) {
			instance = new AddHeaderParameter();
		}
		return instance;
	}

	public IAddParameter addParameter(String paramKey) {
		try {
			if(!(paramKey == null)) {
				logger.debug("Setting header parameter key");
				addParameter.setParameterKey(paramKey);
				logger.debug("Setting header parameter Type");
				addParameter.setParameterType("HEADER");
			}else {
				util.failTestCase("Parameter Key cannot be set to null", "Parameter Key cannot be set to null");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Parameter Key failed");
		}
		
		return addParameter;
	}

	public void addParameterMap(HashMap<String, String> headerParametersHashMap) {
		try {
			if(!(headerParametersHashMap.isEmpty())) {
				reportConstant.setParentTestNode("Add Header parameter: ");
				logger.debug("Adding header parameters map to request");
				logger.debug("Adding Header parameters map to request");
				requestBuilder.getRequestSpecBuilder().addHeaders(headerParametersHashMap);
				reportConstant.getParentTestNode().log(Status.PASS, "Headers Parameter added");
			}else {
				util.failTestCase("Header Parameter Map cannot be empty", "Parameter Key cannot be set to null");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Header Parameters failed");
		}		
	}
}