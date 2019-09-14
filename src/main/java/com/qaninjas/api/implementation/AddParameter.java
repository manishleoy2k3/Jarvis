package com.qaninjas.api.implementation;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IAddParameter;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class AddParameter implements IAddParameter, IGeneric{

	private String paramKey = "";
	private String paramType = "";
	
	
	private static AddParameter instance = null;	
	private static  Logger logger = Logger.getLogger(AddParameter.class);
	
	private List<String> queryParamList = new ArrayList<String>();
	private List<String> pathParamList = new ArrayList<String>();
	
	protected AddParameter() {
		
	}

	public static AddParameter getInstance() {
		if(null == instance) {
			instance = new AddParameter();
		}
		return instance;
	}

	/**
	 * @return the paramKey
	 */
	public String getParameterKey() {
		return paramKey;
	}

	/**
	 * @param paramKey the paramKey to set
	 */
	public void setParameterKey(String paramKey) {
		this.paramKey = paramKey;
	}

	/**
	 * @return the paramType
	 */
	public String getParameterType() {
		return paramType;
	}

	/**
	 * @param paramType the paramType to set
	 */
	public void setParameterType(String paramType) {
		this.paramType = paramType;
	}

	public void value(String paramValue) {
		
		if(addParameter.getParameterType().toUpperCase().trim() == "QUERY") {
			logger.debug("Adding query parameter to request");
			requestBuilder.getRequestSpecBuilder().addQueryParam(addParameter.getParameterKey(), paramValue);
			queryParamList.add(addParameter.getParameterKey());
			logger.debug("Query parameter added to request");
			reportConstant.getParentTestNode().log(Status.PASS, addParameter.getParameterKey() + ": " + paramValue);
		} else if(addParameter.getParameterType().toUpperCase().trim() == "PATH") {
			logger.debug("Adding path parameter to request");
			requestBuilder.getRequestSpecBuilder().addPathParam(addParameter.getParameterKey(), paramValue);
			queryParamList.add(addParameter.getParameterKey());
			logger.debug("Path parameter added to request");
			reportConstant.getParentTestNode().log(Status.PASS, addParameter.getParameterKey() + ": " + paramValue);
		} else if(addParameter.getParameterType().toUpperCase().trim() == "HEADER") {
			logger.debug("Adding header parameter to request");
			requestBuilder.getRequestSpecBuilder().addHeader(addParameter.getParameterKey(), paramValue);
			queryParamList.add(addParameter.getParameterKey());
			logger.debug("Header parameter added to request");
			reportConstant.getParentTestNode().log(Status.PASS, addParameter.getParameterKey() + ": " + paramValue);
		}
	}

	/**
	 * @return the pathParamList
	 */
	public List<String> getQueryParamList() {
		return queryParamList;
	}

	/**
	 * @param pathParamList the pathParamList to set
	 */
	public void clearQueryParamList() {
		queryParamList.clear();
		reportConstant.getParentTestNode().log(Status.PASS, "Removed Query Parameters");
	}
	
	/**
	 * @return the pathParamList
	 */
	public List<String> getPathParamList() {
		return pathParamList;
	}

	/**
	 * @param pathParamList the pathParamList to set
	 */
	public void clearPathParamList() {
		pathParamList.clear();
		reportConstant.getParentTestNode().log(Status.PASS, "Removed Path Parameters");
	}

}
