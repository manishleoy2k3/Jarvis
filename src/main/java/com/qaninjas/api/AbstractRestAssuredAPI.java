package com.qaninjas.api;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.Status;
import com.qaninjas.api.implementation.AddHeaderParameter;
import com.qaninjas.api.implementation.AddPathParameter;
import com.qaninjas.api.implementation.AddQueryParameter;
import com.qaninjas.api.implementation.BaseURL;
import com.qaninjas.api.implementation.Execute;
import com.qaninjas.api.implementation.RequestBody;
import com.qaninjas.api.implementation.ResponseBuilder;
import com.qaninjas.api.implementation.Verify;
import com.qaninjas.framework.api.interfaces.IAddHeadersParameters;
import com.qaninjas.framework.api.interfaces.IAddPathParameters;
import com.qaninjas.framework.api.interfaces.IAddQueryParameters;
import com.qaninjas.framework.api.interfaces.IExecute;
import com.qaninjas.framework.api.interfaces.ISetRequestBody;
import com.qaninjas.framework.api.interfaces.ISetURL;
import com.qaninjas.framework.api.interfaces.IVerify;

import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IServices;
import com.qaninjas.framework.Initialize;


public class AbstractRestAssuredAPI implements IServices, IGeneric{

	private static Initialize init = Initialize.getInstance();
	private static  Logger logger = Logger.getLogger(AbstractRestAssuredAPI.class);
	
	
	public ISetURL setBaseURL(String baseURL) {
		reportConstant.setParentTestNode("Set Base URL: ");
		BaseURL.getInstance().setBaseURL(baseURL);
		return BaseURL.getInstance();
	}

	public ISetRequestBody setRequestBodyFromFile(String filePath) {
		reportConstant.setParentTestNode("Set Request Body from File");
		requestBody.setRequestBodyFromFile(filePath);
		return requestBody;
	}

	public ISetRequestBody setRequestBody(String strBody) {
		reportConstant.setParentTestNode("Set Request Body");
		RequestBody.getInstance().setRequestBody(strBody);
		return RequestBody.getInstance();
	}

	public IExecute execute() {
		reportConstant.setParentTestNode("Execute Request: ");
		return new Execute();
	}

	public IVerify verify() {
		if(!(reportConstant.getParentTestNode() != null) && (!(reportConstant.getParentTestNode().getModel().getName().toString().contains("Field Verification:")))) {
			reportConstant.setParentTestNode("Field Verification: ");
		}
		return new Verify();
	}

	public IAddQueryParameters queryParam() {
		if(!(reportConstant.getParentTestNode() != null) && (!(reportConstant.getParentTestNode().getModel().getName().toString().contains("Add Query Parameter:")))) {
			reportConstant.setParentTestNode("Add Query Parameter: ");
		}
		return AddQueryParameter.getInstance();
	}

	public IAddPathParameters pathParam() {
		if(!(reportConstant.getParentTestNode() != null) && (!(reportConstant.getParentTestNode().getModel().getName().toString().contains("Add Path Parameter:")))) {
			reportConstant.setParentTestNode("Add Path Parameter: ");
		}
		return AddPathParameter.getInstance();
	}

	public IAddHeadersParameters header() {
		if(!(reportConstant.getParentTestNode() != null) && (!(reportConstant.getParentTestNode().getModel().getName().toString().contains("Add Header Parameter:")))) {
			reportConstant.setParentTestNode("Add Header Parameter: ");
		}
		return AddHeaderParameter.getInstance();
	}

	public String getResponseBody() {
		reportConstant.setParentTestNode("Get Response Body: ");
		String responsebody = "";
		try {
			responsebody = ResponseBuilder.getResponseBuilder().getResponse().getBody().asString();
			reportConstant.getParentTestNode().log(Status.PASS, "Response Body: " + responsebody);
			return responsebody;
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Get Response Body failed");
		}
		return responsebody;
	}

	public void removeQueryParameters() {
		reportConstant.setParentTestNode("Remove Query Parameters: ");
		for(String temp : addParameter.getQueryParamList()) {
			logger.debug("Query Parameter to be removed is: " + temp);
			requestBuilder.getRequestSpecBuilder().removeQueryParam(temp);
		}
		addParameter.clearQueryParamList();
	}

	public void removePathParameters() {
		reportConstant.setParentTestNode("Remove Path Parameters: ");
		for(String temp : addParameter.getPathParamList()) {
			logger.debug("Path Parameter to be removed is: " + temp);
			requestBuilder.getRequestSpecBuilder().removePathParam(temp);
		}
		addParameter.clearPathParamList();
	}
	
	public QueryableRequestSpecification queryableReq = SpecificationQuerier.query(requestBuilder.getRequestSpecBuilder().build());

}
