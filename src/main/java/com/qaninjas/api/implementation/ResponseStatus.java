package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IRespStatus;
import com.qaninjas.framework.api.interfaces.IStatusCode;
import com.qaninjas.framework.api.interfaces.IStatusLine;

public class ResponseStatus implements IRespStatus, IGeneric{

	private ResponseBuilder responseBuilder = ResponseBuilder.getResponseBuilder();
	private static ResponseStatus instance = null;
	private static  Logger logger = Logger.getLogger(ResponseStatus.class);
	
		
	protected ResponseStatus() {
		
	}
	
	public static ResponseStatus getInstance() {
		if(null == instance) {
			instance = new ResponseStatus();
		}
		return instance;
	}
	
	
	public IStatusCode statusCode() {
		try {
			StatusCode.getInstance().setStatusCode(responseBuilder.getResponse().getStatusCode());
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get Status Code failed");
		}
		return StatusCode.getInstance();
	}

	
	public IStatusLine statusLine() {
		try {
			StatusLine.getInstance().setStatusLine(responseBuilder.getResponse().getStatusLine());
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get Status Line failed");
		}
		return StatusLine.getInstance();
	}

	
}