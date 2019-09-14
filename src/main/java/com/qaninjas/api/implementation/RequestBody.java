package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.ISetRequestBody;


public class RequestBody implements ISetRequestBody, IGeneric{

	private static RequestBody instance = null;
	private static  Logger logger = Logger.getLogger(RequestBody.class);
	
		
	protected RequestBody() {
		
	}
	
	public static RequestBody getInstance() {
		if(null == instance) {
			instance = new RequestBody();
		}
		return instance;
	}

	public void setRequestBodyFromFile(String fileName) {
		setRequestBody(jsonHandler.readJsonFromFile(fileName));
	}
	
	public void setRequestBody(Object strBody) {
		try {
			if(!(strBody == null)) {
				logger.debug("Setting request body from string value ");
				requestBuilder.getRequestSpecBuilder().setBody(strBody);
				reportConstant.getParentTestNode().log(Status.PASS, strBody.toString());
			}else {
				util.failTestCase("Request Body cannot be null", "Request Body cannot be null");
			}
		}catch(Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Request Body failed");
		}

				
		
	}
}
