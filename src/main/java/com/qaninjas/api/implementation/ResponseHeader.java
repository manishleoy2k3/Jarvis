package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IResponseHeader;


public class ResponseHeader implements IResponseHeader, IGeneric{

	private String headerVal = "";
	private static ResponseHeader instance = null;
	private static  Logger logger = Logger.getLogger(ResponseHeader.class);
	
		
	protected ResponseHeader() {
		
	}
	
	public static ResponseHeader getInstance() {
		if(null == instance) {
			instance = new ResponseHeader();
		}
		return instance;
	}

	/**
	 * @return the statusCode
	 */
	public String getHeaderVal() {
		return headerVal;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setHeaderVal(String headerVal) {
		this.headerVal = headerVal;
		logger.debug("Header response value set.");
	}

	public void isEqualTo(boolean compareType, Object expectedHeaderVal) {
		logger.info("Validating response header field - Equal to");
		compare.isEqualTo(compareType, expectedHeaderVal, (Object) getHeaderVal());
	}

	public void contains(String expectedHeaderVal) {
		logger.info("Validating header field- Equal to");
		compare.contains(expectedHeaderVal.trim(), getHeaderVal().trim());
	}
}
