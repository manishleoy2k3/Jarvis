package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IExecute;
import com.qaninjas.framework.api.interfaces.IGeneric;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class Execute implements IExecute, IGeneric{

	private static Logger logger = Logger.getLogger(Execute.class);
	private RequestSpecification requestSpec = requestBuilder.getRequestSpecBuilder().build();
	
	public void postRequest() {
		try {
			requestBuilder.getRequestSpecBuilder().build().log().all();
			logger.info("Executing REST POST request.");
			responseBuilder.setResponse(RestAssured.given().spec(requestSpec).relaxedHTTPSValidation().post());
			logger.info("POST request executed.");
			logger.info(responseBuilder.getResponse().then().log().all());
		}catch(Exception e){
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Request Execution Failed");
		}		
	}

	public void putRequest() {
		try {
			requestBuilder.getRequestSpecBuilder().build().log().all();
			logger.info("Executing REST PUT request.");
			responseBuilder.setResponse(RestAssured.given().spec(requestSpec).relaxedHTTPSValidation().put());
			logger.info("PUT request executed.");
			logger.info(responseBuilder.getResponse().then().log().all());
		}catch(Exception e){
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Request Execution Failed");
		}
	}

	public void getRequest() {
		try {
			if(requestBuilder.getRequestSpecBuilder() == null) {
				logger.error("Request Specification is null.");
			} else {			
				requestBuilder.getRequestSpecBuilder().build().log().all();
				logger.info("Executing REST GET request.");
				responseBuilder.setResponse(RestAssured.given().spec(requestSpec).relaxedHTTPSValidation().get());
				logger.info("GET request executed.");
				logger.info(responseBuilder.getResponse().then().log().all());
			}
		}catch(Exception e){
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Request Execution Failed");
		}		
	}

	public void deleteRequest() {
		try {
			requestBuilder.getRequestSpecBuilder().build().log().all();
			logger.info("Executing REST DELETE request.");
			responseBuilder.setResponse(RestAssured.given().spec(requestSpec).relaxedHTTPSValidation().delete());
			logger.info("DELETE request executed.");
			logger.info(responseBuilder.getResponse().then().log().all());
		}catch(Exception e){
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Request Execution Failed");
		}		
	}
}
