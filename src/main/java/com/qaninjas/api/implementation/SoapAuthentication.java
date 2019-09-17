package com.qaninjas.api.implementation;

import java.nio.charset.StandardCharsets;

import javax.xml.soap.*;

public class SoapAuthentication {

	public void sendBasicAuthForSoapAsHeader(String userName, String password, SOAPMessage soapMessage) {
		 String authorization = new String(java.util.Base64.getMimeEncoder().encode((userName + ":" + password).getBytes()),
                 StandardCharsets.UTF_8);		
		javax.xml.soap.MimeHeaders hd = soapMessage.getMimeHeaders();
		hd.addHeader("Authorization", "Basic" + authorization);
	}
}
