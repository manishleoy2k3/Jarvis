package com.qaninjas.api.implementation;

import java.util.HashMap;

import org.apache.log4j.Logger;

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

	@Override
	public IAddParameter addParameter(String paramKey) {
		logger.debug("Setting header parameter key");
		addParameter.setParameterKey(paramKey);
		logger.debug("Setting header parameter Type");
		addParameter.setParameterType("HEADER");
		return addParameter;
	}

	@Override
	public void addParameterMap(HashMap<String, String> headerParametersHashMap) {
		extentManager.createNode("Add Header Parameter: ");
		logger.debug("Adding Header parameters map to request");
		requestBuilder.getRequestSpecBuilder().addHeaders(headerParametersHashMap);
	}
}