package com.qaninjas.api.implementation;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IStatusLine;

public class StatusLine implements IStatusLine, IGeneric{

	private String StatusLine;
	private static StatusLine instance = null;
	private static  Logger logger = Logger.getLogger(StatusLine.class);
	
		
	protected StatusLine() {
		
	}
	
	public static StatusLine getInstance() {
		if(null == instance) {
			instance = new StatusLine();
		}
		return instance;
	}

	/**
	 * @return the statusCode
	 */
	public String getStatusLine() {
		return StatusLine;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusLine(String StatusLine) {
		this.StatusLine = StatusLine;
		logger.debug("Response status line set: " + StatusLine);
	}

	public void isEqualTo(boolean compareType, Object expectedStatusLine) {
		logger.info("Validating response status Line - Equal to");
		compare.isEqualTo(compareType, expectedStatusLine, (Object) getStatusLine());
	}

	public void contains(String expectedStatusLine) {
		logger.info("Validating response status Line - Equal to");
		compare.contains(expectedStatusLine.trim(), getStatusLine().trim());
	}

}
