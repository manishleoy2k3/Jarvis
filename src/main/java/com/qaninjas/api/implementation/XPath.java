package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IXPath;

public class XPath implements IXPath, IGeneric{

	private String xpath = "";
	private static XPath instance = null;
	private static  Logger logger = Logger.getLogger(XPath.class);
	
	protected XPath() {
		
	}
	
	public static XPath getInstance() {
		if(null == instance) {
			instance = new XPath();
		}
		return instance;
	}

	
	/**
	 * @return the xpath
	 */
	public String getXpathValue() {
		return xpath;
	}
	/**
	 * @param xpath the xpath to set
	 */
	public void setXpathValue(String xpath) {
		this.xpath = xpath;
	}

	public void isEqualTo(boolean compareType, Object xPathVal) {
		logger.info("Validating Xpath value - Equal to");
		compare.isEqualTo(compareType, xPathVal, responseBuilder.getResponse().then().extract().path(getXpathValue()));
	}

}
