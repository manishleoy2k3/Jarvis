package com.qaninjas.api.implementation;

import java.util.HashMap;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IAddParameter;
import com.qaninjas.framework.api.interfaces.IAddPathParameters;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class AddPathParameter implements IAddPathParameters, IGeneric{

	private static AddPathParameter instance = null;
	private static  Logger logger = Logger.getLogger(AddPathParameter.class);
	
		
	protected AddPathParameter() {
		
	}
	
	public static AddPathParameter getInstance() {
		if(null == instance) {
			instance = new AddPathParameter();
		}
		return instance;
	}

	public IAddParameter addParameter(String paramKey) {
		try {
			if(!(paramKey == null)) {
				logger.debug("Setting path parameter key");
				addParameter.setParameterKey(paramKey);
				logger.debug("Setting path parameter Type");
				addParameter.setParameterType("PATH");
			}else {
				util.failTestCase("Header Parameter Map cannot be empty", "Parameter Key cannot be set to null");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Header Parameters failed");
		}
		
		return addParameter;
	}

	public void addParameterMap(HashMap<String, String> pathParametersHashMap) {
		try {
			if(!(pathParametersHashMap.isEmpty())) {
				logger.debug("Adding path parameters map to request");
				requestBuilder.getRequestSpecBuilder().addPathParams(pathParametersHashMap);
				reportConstant.getParentTestNode().log(Status.PASS, "Path Parameters added");
			}else {
				util.failTestCase("Path Parameter Map cannot be empty", "Path Parameter Map cannot be empty");
			}
			}catch(Exception e) {
				logger.error(e.getMessage());
				util.failTestCase(e.getMessage(), "Set Path Parameters failed");
			}
		
	}

}
