package com.qaninjas.framework.utility.report;

import java.net.InetAddress;

import org.apache.log4j.Logger;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.ReportConstants;

public class ExtentManager {

	private static ExtentReports extent;
	private ExtentHtmlReporter htmlReporter;
	private static ExtentManager instance = null;
	
	private InetAddress ip;
	private static Logger logger = Logger.getLogger(ExtentManager.class);
	
	protected ExtentManager() {

	}
	
	public static ExtentManager getInstance() {
		if(null == instance) {
			instance = new ExtentManager();
		}
		return instance;
	}

	public ExtentReports getExtent() {
		if(FrameworkConstants.HTML_REPORT) {
			if(extent != null)
				return extent;
			extent = new ExtentReports();
			extent.attachReporter(GetHtmlReporter());
			ExtentReportConstant.getInstance().SetExtent(extent);
		}
		return extent;
	}

	private ExtentHtmlReporter GetHtmlReporter() {

		String filePath = ReportConstants.OUTPUT_FOLDER + "/extentreport.html";
		try {
			ip = InetAddress.getLocalHost();
			
			htmlReporter = new ExtentHtmlReporter(filePath);
			
			htmlReporter.config().setDocumentTitle(FrameworkConstants.REPORTCONFIG.get("HTML_REPORT_TITLE"));
			htmlReporter.config().setReportName(ReportConstants.HTML_REPORT_TITLE);
			htmlReporter.config().setTheme(Theme.STANDARD);
			extent.setAnalysisStrategy(AnalysisStrategy.TEST);
			htmlReporter.config().setTimeStampFormat("MM/dd/yyyy hh:mm:ss a");
			htmlReporter.config().setChartVisibilityOnOpen(true);
			
			extent.setSystemInfo("OS", System.getProperty("os.name").toUpperCase());
			extent.setSystemInfo("Host Name", ip.getHostName());
			extent.setSystemInfo("IP", ip.toString());
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			return htmlReporter;
			
		} catch(Exception e) {
			logger.error("Extent Report file is not created successfully. " + e.getMessage());
		}
		return htmlReporter;
	}
	
	public void endReport() {
		if(FrameworkConstants.HTML_REPORT) {
			extent.flush();
		}
	}
}
