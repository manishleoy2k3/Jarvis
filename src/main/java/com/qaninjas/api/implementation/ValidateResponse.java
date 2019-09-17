package com.qaninjas.api.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.qaninjas.api.utils.NamespaceResolver;
import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IValidateResponse;

public class ValidateResponse implements IValidateResponse, IGeneric{

	private static  Logger logger = Logger.getLogger(ValidateResponse.class);
	
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
	
	public Integer getAttributeValueInInteger(SOAPMessage soapMessage, String tagname) {
		try {
			soappart = soapMessage.getSOAPPart();
			incomingEnvelop = soappart.getEnvelope();
			body = incomingEnvelop.getBody();
			return Integer.valueOf(body.getElementsByTagName(tagname).item(0).getTextContent());
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Error in getAttributeValueInInteger().");
		}
		return Integer.valueOf(body.getElementsByTagName(tagname).item(0).getTextContent());
	}

	public String getAttributeValueInString(SOAPMessage soapMessage, String tagname) {
		try {
			soappart = soapMessage.getSOAPPart();
			incomingEnvelop = soappart.getEnvelope();
			body = incomingEnvelop.getBody();
			return body.getElementsByTagName(tagname).item(0).getTextContent();
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Error in getAttributeValueInString().");
		}
		return body.getElementsByTagName(tagname).item(0).getTextContent();
	}

	public Double getAttributeValueInDouble(SOAPMessage soapMessage, String tagname) {
		try {
			soappart = soapMessage.getSOAPPart();
			incomingEnvelop = soappart.getEnvelope();
			body = incomingEnvelop.getBody();
			return Double.valueOf(body.getElementsByTagName(tagname).item(0).getTextContent());
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Error in getAttributeValueInDouble().");
		}
		return Double.valueOf(body.getElementsByTagName(tagname).item(0).getTextContent());
	}

	public Float getAttributeValueInFloat(SOAPMessage soapMessage, String tagname) {
		try {
			soappart = soapMessage.getSOAPPart();
			incomingEnvelop = soappart.getEnvelope();
			body = incomingEnvelop.getBody();
			return Float.valueOf(body.getElementsByTagName(tagname).item(0).getTextContent());
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Error in getAttributeValueInFloat().");
		}
		return Float.valueOf(body.getElementsByTagName(tagname).item(0).getTextContent());
	}

	public List<String> getAttributeValueInInteger(String fileName, String xpathOfNode) {
		try {
			nodeValue = new ArrayList<String>();
			factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			builder = factory.newDocumentBuilder();
			doc = builder.parse(new FileInputStream(new File(System.getProperty("user.dir") + System.lineSeparator() + fileName + ".xml")));
			xpathFactory = XPathFactory.newInstance();
			xpath = xpathFactory.newXPath();
			xpath.setNamespaceContext(new NamespaceResolver(doc));
			expr = xpath.compile(xpathOfNode + "/text()");
			result = expr.evaluate(doc, XPathConstants.NODESET);
			nodes = (NodeList) result;
			for(int nodeIterator=0; nodeIterator < nodes.getLength(); nodeIterator++){
				nodeValue.add(nodes.item(nodeIterator).getNodeValue());
			}
			return nodeValue;
		} catch (Exception e) {
			logger.error(e.getMessage());
			util.failTestCase(e.getMessage(), "Error in getAttributeValueInInteger().");
		}
		return nodeValue;
	}

}
