package com.qaninjas.framework.api.interfaces;

public interface IServices {

	ISetURL setBaseURL(String baseURL);
	ISetRequestBody setRequestBodyFromFile(String filePath);
	ISetRequestBody setRequestBody(String strBody);
	
	IExecute execute();
	IVerify verify();
	
	IAddQueryParameters queryParam();
	IAddPathParameters pathParam();
	IAddHeadersParameters header();
	
	String getResponseBody();
	
	void removeQueryParameters();
	void removePathParameters();

}
