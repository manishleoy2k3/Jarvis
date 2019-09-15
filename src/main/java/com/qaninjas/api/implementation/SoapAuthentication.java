package com.qaninjas.api.implementation;

import javax.xml.soap.*;

public class SoapAuthentication {

	public void sendBasicAuthForSoapAsHeader(String userName, String password, SOAPMessage soapMessage) {
		 String authorization = null; 
		//= new sun.misc.BASE64Encoder().encode((userName + ":" + password).getBytes());
		
		javax.xml.soap.MimeHeaders hd = soapMessage.getMimeHeaders();
		hd.addHeader("Authorization", "Basic" + authorization);
	}
}
