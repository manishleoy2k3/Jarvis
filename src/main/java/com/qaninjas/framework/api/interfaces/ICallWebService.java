package com.qaninjas.framework.api.interfaces;

import org.apache.xmlbeans.impl.soap.SOAPMessage;

public interface ICallWebService {

	public SOAPMessage executeWebService(String soapEndPointUrl, String soapAction, String requestPath);
}
