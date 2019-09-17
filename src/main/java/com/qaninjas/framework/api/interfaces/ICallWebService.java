package com.qaninjas.framework.api.interfaces;

import javax.xml.soap.SOAPMessage;

public interface ICallWebService {

	public SOAPMessage executeWebService(String soapEndPointUrl, String soapAction, String requestPath);
}
