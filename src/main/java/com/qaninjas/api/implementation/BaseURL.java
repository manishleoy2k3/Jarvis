package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.ISetURL;

public class BaseURL implements ISetURL, IGeneric{

	private String baseurl = "";
	private String resourcepath = ""; 
	private static BaseURL instance = null;
	private static  Logger logger = Logger.getLogger(BaseURL.class);
			
	protected BaseURL() {
		
	}
	
	public static BaseURL getInstance() {
		if(null == instance) {
			instance = new BaseURL();
		}
		return instance;
	}

	/**
	 * @return the baseURL
	 */
	public String getBaseURL() {
		//TODO: report log this
		return baseurl;
	}

	/**
	 * @param baseURL the baseURL to set
	 */
	public void setBaseURL(String baseURL) {
		try {
			if(!(baseURL == null)) {
				requestBuilder.getRequestSpecBuilder().setBaseUri(baseURL);
				logger.debug("Base URL set: " + baseURL);
				reportConstant.getParentTestNode().log(Status.PASS, baseURL);
				baseurl = baseURL;
			}else {
				util.failTestCase("Base URI cannot be set to null", "Base URI cannot be set to null");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Base URI failed");
		}
	}

	public void setBasePath(String resourcePath) {
		try {
			if(!(resourcePath == null)) {
				requestBuilder.getRequestSpecBuilder().setBasePath(resourcePath);
				logger.debug("Base Path set: " + resourcePath);
				reportConstant.getParentTestNode().log(Status.PASS, resourcePath);
				resourcepath = resourcePath;
			}else {
				util.failTestCase("Resource Path cannot be set to null", "Resource Path cannot be set to null");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Resource Path failed");
		}
	}

	/**
	 * @return the baseURL
	 */
	public String getBasePath() {
		//TODO: report log this
		return resourcepath;
	}

}
