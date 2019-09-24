package com.qaninjas.framework;

import java.io.File;

import com.qaninjas.accessibility.framework.ExcelUtility;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.ReportConstants;
import com.qaninjas.framework.utility.DateTime;
import com.qaninjas.framework.utility.files.PropertiesFiles;

public class SetupExecutionFolder {

	private String folderPath = "";
	private PropertiesFiles prop = new PropertiesFiles();
	private ExecutionParameters executionParam = new ExecutionParameters();
	private ExcelUtility excelUtility = new ExcelUtility();
	
	public void folderStructure() {
		killBrowserProcesses();
		if(FrameworkConstants.GLOBALCONFIG.get("KEYWORD_EXECUTION").equalsIgnoreCase("YES"))
			FrameworkConstants.KEYWORDCONFIG = prop.readProperties(FrameworkConstants.GLOBALCONFIG.get("KEYWORD.PROPERTIES"));
		
		FrameworkConstants.REPORTCONFIG = prop.readProperties(FrameworkConstants.GLOBALCONFIG.get("REPORT.PROPERTIES"));
		if(FrameworkConstants.EXECUTION_ON.equalsIgnoreCase("desktop")) {
			desktopResultFolder();
		} else if(FrameworkConstants.EXECUTION_ON.equalsIgnoreCase("api")) {
			apiResultFolder();
		} else {
			mobileResultFolder();
		}
		File directory = new File(ReportConstants.OUTPUT_FOLDER);
		directory.mkdirs();
		if(FrameworkConstants.EXECUTION_ON.equalsIgnoreCase("desktop") || FrameworkConstants.EXECUTION_ON.equalsIgnoreCase("mobile")) {
			executionParam.setupReportConfigParameter();
			screenShotFolder();
			voilationFolder();
		}
	}

	private void mobileResultFolder() {
		FrameworkConstants.MOBILECONFIG = prop.readProperties(FrameworkConstants.GLOBALCONFIG.get("MOBILE.PROPERTIES"));
		if(FrameworkConstants.TARGET.equalsIgnoreCase("browser")){
			folderPath = "Execution" + File.separator + "Build_MOBILE_BROWSER_"
						+ DateTime.getCurrentTime("ddMMyyyyhhmmss") + "_" 
						+ FrameworkConstants.MOBILECONFIG.get("PLATFORM_OS").toUpperCase() + ""
						+ FrameworkConstants.MOBILECONFIG.get("BROWSER").toUpperCase() + File.separator;
		} else {
			folderPath = "Execution" + File.separator + "Build_MOBILE_NATIVEAPP_"
					+ DateTime.getCurrentTime("ddMMyyyyhhmmss") + "_" 
					+ FrameworkConstants.MOBILECONFIG.get("PLATFORM_OS").toUpperCase() + File.separator;
		}		
		getOutputFolder(folderPath);
		executionParam.setupDesktopConfigParameter();
	}

	private void apiResultFolder() {
		folderPath = "Execution" + File.separator + "Build_API_" + DateTime.getCurrentTime("ddMMyyyyhhmmss") + File.separator;
		getOutputFolder(folderPath);
	}
	
	private void desktopResultFolder() {
		FrameworkConstants.DESKTOPCONFIG = prop.readProperties(FrameworkConstants.GLOBALCONFIG.get("DESKTOP.PROPERTIES"));
		
		folderPath = "Execution" + File.separator + "Build_API_" + DateTime.getCurrentTime("ddMMyyyyhhmmss") + File.separator;
		
		getOutputFolder(folderPath);
		executionParam.setupDesktopConfigParameter();
	}

	private void voilationFolder() {
		if(FrameworkConstants.ACCESSIBILITY_TEST) {
			FrameworkConstants.ACCESSCONFIG = prop.readProperties(FrameworkConstants.GLOBALCONFIG.get("ACCESSIBILITY.PROPERTIES"));
			
			ReportConstants.VOILATIONS_LOCATION = ReportConstants.OUTPUT_FOLDER + File.separator + "voilations" + File.separator;
			
			File directory = new File(ReportConstants.OUTPUT_FOLDER);
			directory.mkdirs();
			excelUtility.createWorkBook();
		}
		
	}

	private void screenShotFolder() {
		ReportConstants.SCREENSHOT_LOCATION = ReportConstants.OUTPUT_FOLDER + File.separator + "screenShots" + File.separator;
		File directory = new File(ReportConstants.OUTPUT_FOLDER);
		directory.mkdirs();
	}

	private void getOutputFolder(String folderPath2) {
		if(FrameworkConstants.REPORTCONFIG.get("OUTPUT_FOLDER").equalsIgnoreCase("projectPath")){
			ReportConstants.OUTPUT_FOLDER = System.getProperty("user.dir") + File.separator ;
		} else {
			ReportConstants.OUTPUT_FOLDER = FrameworkConstants.REPORTCONFIG.get("OUTPUT_FOLDER") + File.separator ;
		}
	}
	
	private void killBrowserProcesses() {
		ProcessBuilder processBuilder;		
		try {
			processBuilder = new ProcessBuilder(System.getProperty("user.dir") + "/src/test/resources/lib/browserProcessKill.bat");
			processBuilder.start();
		} catch (Exception e) {
			e.getStackTrace();
		} finally {
			processBuilder = null;
		}
	}
}
