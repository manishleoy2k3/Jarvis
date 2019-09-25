package com.qaninjas.api;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPPart;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.qaninjas.api.implementation.CallWebService;
import com.qaninjas.api.implementation.ValidateResponse;
import com.qaninjas.framework.Initialize;
import com.qaninjas.framework.api.interfaces.ICallWebService;
import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.ISoapServices;
import com.qaninjas.framework.api.interfaces.IValidateResponse;

public class AbstractSoapAPI implements ISoapServices, IGeneric{

	private static Initialize init = Initialize.getInstance();
	private static  Logger logger = Logger.getLogger(AbstractSoapAPI.class);
	
	SOAPPart soappart = null;
	SOAPEnvelope incomingEnvelop = null;
	SOAPBody body = null;
	DocumentBuilderFactory factory = null;
	DocumentBuilder builder = null;
	ArrayList<String> nodeValue = null;
	XPathFactory xpathFactory = null;
	XPath xpath = null;
	Document doc = null;
	XPathExpression expr = null;
	Object result = null;
	NodeList nodes = null;
	CallWebService cws = CallWebService.getInstance();
	
	public ICallWebService callWebService(String soapEndPointUrl, String soapAction, String requestPath) {
		try {
			logger.info("Getting request from path: " + requestPath);
			cws.executeWebService(soapEndPointUrl, soapAction, requestPath);
			return cws;
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Failed to make a SOAP call");
		}
		return cws;
	}
	public ICallWebService callWebServiceWithBasicAuth(String soapEndPointUrl, String soapAction, String requestPath,
			String userName, String password) {
		try {
			logger.info("Getting request from path: " + requestPath);
			cws.executeWebServiceWithAuth(soapEndPointUrl, soapAction, requestPath, userName, password);
			return cws;
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Failed to make a SOAP call");
		}
		return cws;
	}
	public IValidateResponse validateResponse() {
		reportConstant.setParentTestNode("Validate Response: ");
		return new ValidateResponse();
	}
}
