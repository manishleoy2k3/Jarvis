package com.qaninjas.framework;

import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.ReportConstants;
import com.qaninjas.framework.constants.SetUpConstants;

public class ExecutionParameters {

	/**
	 * Setup constant parameter from Global config parameter
	 */
	public void setupGlobalConfigParameter() {
		FrameworkConstants.EXECUTION_ON = FrameworkConstants.GLOBALCONFIG.get("EXECUTION_ON");
		FrameworkConstants.EXECUTION_MODE = FrameworkConstants.GLOBALCONFIG.get("EXECUTION_MODE");
		FrameworkConstants.TARGET = FrameworkConstants.GLOBALCONFIG.get("TARGET");
		FrameworkConstants.HTML_REPORT = getBooleanStatus(FrameworkConstants.GLOBALCONFIG.get("EXECUTION_REPORT"));
	}

	/**
	 * Setup constant parameter from Desktop config parameter
	 */
	public void setupDesktopConfigParameter() {
		SetUpConstants.BROWSER = FrameworkConstants.DESKTOPCONFIG.get("BROWSER");
		SetUpConstants.DEFAULT_TIMEOUT = Integer.parseInt(FrameworkConstants.DESKTOPCONFIG.get("DEFAULT_TIMEOUT"));
		SetUpConstants.PAGELOAD_TIMEOUT = Integer.parseInt(FrameworkConstants.DESKTOPCONFIG.get("PAGELOAD_TIMEOUT"));
		FrameworkConstants.ACCESSIBILITY_TEST = getBooleanStatus(FrameworkConstants.DESKTOPCONFIG.get("ACCESSIBILITY_TEST"));
	}
	
	/**
	 * Setup constant parameter from Mobile config parameter
	 */
	public void setupMobileConfigParameter() {
		SetUpConstants.BROWSER = FrameworkConstants.MOBILECONFIG.get("BROWSER");
		SetUpConstants.DEFAULT_TIMEOUT = Integer.parseInt(FrameworkConstants.MOBILECONFIG.get("DEFAULT_TIMEOUT"));
		SetUpConstants.PAGELOAD_TIMEOUT = Integer.parseInt(FrameworkConstants.MOBILECONFIG.get("PAGELOAD_TIMEOUT"));
	}
	
	/**
	 * Setup constant parameter from Mobile config parameter
	 */
	public void setupReportConfigParameter() {
		ReportConstants.HTML_REPORT_TITLE = FrameworkConstants.REPORTCONFIG.get("HTML_REPORT_TILE");
		ReportConstants.SCREEN_SHOT = getBooleanStatus(FrameworkConstants.REPORTCONFIG.get("SCREEN_SHOT"));
	}
	
	/**
	 * convert string yes/no into boolean true/false
	 * @param status
	 * @return
	 */
	private boolean getBooleanStatus(String status) {
		if(status.equals("yes")) {
			return true;
		}else {
			return false;
		}
		
	}

	

}
