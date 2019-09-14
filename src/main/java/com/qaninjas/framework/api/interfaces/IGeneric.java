package com.qaninjas.framework.api.interfaces;

import com.qaninjas.api.implementation.AddParameter;
import com.qaninjas.api.implementation.BaseURL;
import com.qaninjas.api.implementation.CallWebService;
import com.qaninjas.api.implementation.RequestBody;
import com.qaninjas.api.implementation.RequestBuilder;
import com.qaninjas.api.implementation.ResponseBuilder;
import com.qaninjas.api.implementation.ResponseHeader;
import com.qaninjas.api.implementation.ResponseJsonPath;
import com.qaninjas.api.implementation.ResponseTime;
import com.qaninjas.api.implementation.StatusCode;
import com.qaninjas.api.implementation.StatusLine;
import com.qaninjas.api.utils.CommonUtils;
import com.qaninjas.api.utils.Comparison;
import com.qaninjas.api.utils.JsonHandler;
import com.qaninjas.framework.Initialize;
import com.qaninjas.framework.utility.report.ExtentManager;
import com.qaninjas.framework.utility.report.ExtentReportConstant;

public interface IGeneric {

	static Initialize init = Initialize.getInstance();	
	RequestBuilder requestBuilder = RequestBuilder.getRequestBuilder();
	ResponseBuilder responseBuilder = ResponseBuilder.getResponseBuilder();
	AddParameter addParameter = AddParameter.getInstance();
	StatusCode statusCode = StatusCode.getInstance();
	StatusLine statusLine = StatusLine.getInstance();
	ResponseHeader responseHeader = ResponseHeader.getInstance();
	ResponseTime responseTime = ResponseTime.getInstance();
	ResponseJsonPath responseJsonPath = ResponseJsonPath.getInstance();
	RequestBody requestBody = RequestBody.getInstance();
	BaseURL baseUrl = BaseURL.getInstance();
	JsonHandler jsonHandler = JsonHandler.getInstance();
	
	Comparison compare = Comparison.getInstance();
	ExtentManager extentManager = ExtentManager.getInstance();
	CommonUtils util = CommonUtils.getInstance();
	ExtentReportConstant reportConstant = ExtentReportConstant.getInstance();
	CallWebService callWebService = CallWebService.getInstance();
}
