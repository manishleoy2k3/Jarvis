package com.qaninjas.framework.api.interfaces;

import java.util.List;

import javax.xml.soap.SOAPMessage;


public interface IValidateResponse {

	public Integer getAttributeValueInInteger(SOAPMessage soapMessage, String tagname);
	public String getAttributeValueInString(SOAPMessage soapMessage, String tagname);
	public Double getAttributeValueInDouble(SOAPMessage soapMessage, String tagname);
	public Float getAttributeValueInFloat(SOAPMessage soapMessage, String tagname);
	public List<String> getAttributeValueInInteger(String fileName, String xpathOfNode);
}
