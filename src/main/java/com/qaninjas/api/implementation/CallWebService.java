package com.qaninjas.api.implementation;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeader;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.ICallWebService;
import com.qaninjas.framework.api.interfaces.IGeneric;

public class CallWebService implements ICallWebService, IGeneric{

	private static CallWebService instance = null;
	private static  Logger logger = Logger.getLogger(CallWebService.class);
	
	SOAPConnectionFactory soapConnectionFactory;
	SOAPConnection soapConnection;
	SOAPMessage soapMessage;
	MessageFactory messageFactory;
	MimeHeaders headers;
	SOAPPart sp;
	StreamSource prepMsg;
	
	protected CallWebService() {
		
	}
		
	public static CallWebService getInstance() {
		if(null == instance) {
			instance = new CallWebService();
		}
		return instance;
	}
	
	public SOAPMessage executeWebService(String soapEndPointUrl, String soapAction, String requestPath) {
		try {
			logger.info("Setting Connections....");
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
			soapMessage = soapConnection.call(createRequest(soapAction, requestPath), soapEndPointUrl);
			logger.info("Response SOAP Message:");
			soapConnection.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Base URI failed");
		}
		return soapMessage;
	}

	private SOAPMessage createRequest(String soapAction, String requestPath) throws SOAPException, FileNotFoundException {
		messageFactory = MessageFactory.newInstance();
		soapMessage = messageFactory.createMessage();
		headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);
		sp = soapMessage.getSOAPPart();
		prepMsg = new StreamSource(new FileInputStream(requestPath));
		sp.setContent(prepMsg);
		soapMessage.saveChanges();
		logger.info("Reuest SOAP Message:");
		return soapMessage;
	}

	public SOAPMessage executeWebServiceWithAuth(String soapEndPointUrl, String soapAction, String requestPath, 
			String userName, String password) {
		try {
			logger.info("Setting Connections....");
			soapConnectionFactory = SOAPConnectionFactory.newInstance();
			soapConnection = soapConnectionFactory.createConnection();
			soapMessage = soapConnection.call(createSOAPRequestWithBasicAuth(soapAction, requestPath, userName, password), soapEndPointUrl);
			logger.info("Response SOAP Message:");
			soapConnection.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Set Base URI failed");
		}
		return soapMessage;
	}

	private SOAPMessage createSOAPRequestWithBasicAuth(String soapAction, String requestPath, String userName,
			String password) throws SOAPException, FileNotFoundException {
		messageFactory = MessageFactory.newInstance();
		soapMessage = messageFactory.createMessage();
		headers = soapMessage.getMimeHeaders();
		headers.addHeader("SOAPAction", soapAction);
		
		new SoapAuthentication().sendBasicAuthForSoapAsHeader(userName, password, soapMessage);;
		
		sp = soapMessage.getSOAPPart();
		prepMsg = new StreamSource(new FileInputStream(requestPath));
		sp.setContent(prepMsg);
		soapMessage.saveChanges();
		logger.info("Reuest SOAP Message:");
		logger.info("\n");
		return soapMessage;
	}
}
