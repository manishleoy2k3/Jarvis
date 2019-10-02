package com.qaninjas.framework.constants;

import java.io.File;
import java.util.HashMap;

public class FrameworkConstants {

	public static final String CONFIGFILE = "configuration" + File.separator + "configuration.properties";
	
	public static HashMap<String, String> GLOBALCONFIG = new HashMap<String, String>();
	public static HashMap<String, String> DESKTOPCONFIG = new HashMap<String, String>();
	public static HashMap<String, String> MOBILECONFIG = new HashMap<String, String>();
	public static HashMap<String, String> APICONFIG = new HashMap<String, String>();
	public static HashMap<String, String> SOAPAPICONFIG = new HashMap<String, String>();
	public static HashMap<String, String> REPORTCONFIG = new HashMap<String, String>();
	public static HashMap<String, String> KEYWORDCONFIG = new HashMap<String, String>();
	public static HashMap<String, String> REMOTECONFIG = new HashMap<String, String>();
	public static HashMap<String, String> ACCESSCONFIG = new HashMap<String, String>();
	
	public static final String EXECUTOR_NAME = System.getProperty("user.name");
	
	public static String EXECUTION_MODE = "";
	public static String TARGET = "browser";
	public static Integer STEP_COUNTER = 0;
	public static String EXECUTION_ON = "desktop";
	
	public static boolean HTML_REPORT = false;
	public static boolean ACCESSIBILITY_TEST = false;
}
