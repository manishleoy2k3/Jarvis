package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import io.restassured.builder.RequestSpecBuilder;

public class RequestBuilder {

	private static RequestBuilder builder = null;
	private static RequestSpecBuilder instance = null;
	private static Logger logger = Logger.getLogger(RequestBuilder.class);
	
		
	protected RequestBuilder() {
		
	}
	
	//RequestBuilder instance creation
	public static RequestBuilder getRequestBuilder() {
		if(null == builder) {
			builder = new RequestBuilder();
			logger.debug("RequestBuilder Object is created.");
		}
		return builder;
	}
	
	// RequestSpecBuilder instance creation
	public RequestSpecBuilder getRequestSpecBuilder() {
		if(null == instance) {
			instance = new RequestSpecBuilder();
			logger.debug("RequestSpecBuilder Object is created.");
		}
		return instance;
	}
	
	// Killing RequestSpecBuilder instance
	public static void killRequestSpecBuilder() {
		if(!(null == instance)) {
			instance = null;
			instance = new RequestSpecBuilder();
		}
	}

}
