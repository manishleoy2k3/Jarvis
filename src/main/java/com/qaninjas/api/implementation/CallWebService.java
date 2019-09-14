package com.qaninjas.api.implementation;

import org.apache.xmlbeans.impl.soap.SOAPMessage;

import com.qaninjas.framework.api.interfaces.ICallWebService;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class CallWebService implements ICallWebService, IGeneric{

	
	protected CallWebService() {
		
	}
	
	public static CallWebService getInstance() {
		// TODO Auto-generated method stub
		return null;
	}

	public SOAPMessage executeWebService(String soapEndPointUrl, String soapAction, String requestPath) {
		// TODO Auto-generated method stub
		return null;
	}

}
