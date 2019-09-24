package com.qaninjas.framework;

import org.apache.log4j.Logger;

import com.qaninjas.framework.constants.FrameworkConstants;

public class SetUpParameter {

	private static  Logger logger = Logger.getLogger(SetUpParameter.class);
	
	public void setExecutionParameter() {
		logger.info("Setting Execution parameter Configuration.");
		logger.info("EXECUTION ON : " + FrameworkConstants.EXECUTION_ON);
		logger.info("EXECUTION TARGETED ON : " + FrameworkConstants.TARGET);
	}
}
