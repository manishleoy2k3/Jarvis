package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IStatusCode;


public class StatusCode implements IStatusCode, IGeneric{

	private int statusCode;
	private static StatusCode instance = null;
	private static  Logger logger = Logger.getLogger(StatusCode.class);
	
	protected StatusCode() {
		
	}
	
	public static StatusCode getInstance() {
		if(null == instance) {
			instance = new StatusCode();
		}
		return instance;
	}

	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
		logger.debug("Response status code set: " + statusCode);
	}

	public void isEqualTo(boolean compareType, Object expectedStatusCode) {
		logger.info("Validating response status code - Equal to");
		compare.isEqualTo(compareType, expectedStatusCode, (Object) getStatusCode());
	}
}
