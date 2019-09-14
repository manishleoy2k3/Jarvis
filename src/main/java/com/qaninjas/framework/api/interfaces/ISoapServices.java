package com.qaninjas.framework.api.interfaces;

public interface ISoapServices {

	ICallWebService callWebService(String soapEndPointUrl, String soapAction, String requestPath);
	
	ICallWebService callWebServiceWithBasicAuth(String soapEndPointUrl, String soapAction, String requestPath, String userName, String password);
	
	IValidateResponse validateResponse();
}
