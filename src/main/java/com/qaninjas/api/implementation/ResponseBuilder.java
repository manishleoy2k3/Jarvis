package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.framework.api.interfaces.IGeneric;
import io.restassured.response.Response;

public class ResponseBuilder implements IGeneric{

	private Response response = null;
	private static ResponseBuilder instance = null;
	private static  Logger logger = Logger.getLogger(ResponseBuilder.class);
	
		
	protected ResponseBuilder() {
		
	}
	
	//RequestBuilder instance creation
	public static ResponseBuilder getResponseBuilder() {
		if(null == instance) {
			instance = new ResponseBuilder();
			logger.debug("ResponseBuilder Object is created.");
		}
		return instance;
	}

	public Response getResponse() {
		return response;
	}

	
	public void setResponse(Response response) {
		this.response = response;
		logger.debug("Response Object value set.");
		reportConstant.getParentTestNode().log(Status.INFO, "Request Executed");
		reportConstant.getParentTestNode().log(Status.INFO, "Response headers: " + response.getHeaders());
		
		if(!(response.getDetailedCookies().asList().isEmpty())) {
			reportConstant.getParentTestNode().log(Status.INFO, "Response detailed cookies: " + response.getDetailedCookies());
		}
		reportConstant.getParentTestNode().log(Status.INFO, "Response body: " + response.asString());
	}
}
