package com.qaninjas.api.implementation;


import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import com.qaninjas.framework.api.interfaces.IGeneric;
import com.qaninjas.framework.api.interfaces.IResponseTime;

public class ResponseTime implements IResponseTime, IGeneric{

	private int responseTime;
	private TimeUnit timeUnit;
	private static ResponseTime instance = null;
	private static  Logger logger = Logger.getLogger(ResponseTime.class);
	
		
	protected ResponseTime() {
		
	}
	
	public static ResponseTime getInstance() {
		if(null == instance) {
			instance = new ResponseTime();
		}
		return instance;
	}

	/**
	 * @return the statusCode
	 */
	public int getResponseTime() {
		return responseTime;
	}

	/**
	 * @param statusCode the statusCode to set
	 */
	public void setResponseTime(int responseTime) {
		this.responseTime = responseTime;
		logger.debug("Response time value set." + responseTime);
	}

	/**
	 * @return the timeUnit
	 */
	public TimeUnit getResponseTimeUnit() {
		return timeUnit;
	}

	/**
	 * @param timeUnit the timeUnit to set
	 */
	public void setResponseTimeUnit(TimeUnit timeUnit) {
		this.timeUnit = timeUnit;
		logger.debug("Response time Unit set." + timeUnit);
	}

	public void isLessOrEqualTo(Object responseTime) {
		logger.info("Validating response time - Less than or Equal to - Time Unit " + getResponseTimeUnit());
		compare.lessOrEqualsTo((Object) responseTime, (Object) getResponseTime());
	}

	public void isGreaterOrEqualTo(Object responseTime) {
		logger.info("Validating response time - Less than or Equal to - Time Unit " + getResponseTimeUnit());
		compare.greaterOrEqualsTo((Object) responseTime, (Object) getResponseTime());
	}
}
