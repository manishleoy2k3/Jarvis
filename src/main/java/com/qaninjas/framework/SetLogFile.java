package com.qaninjas.framework;

import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.ReportConstants;
import com.qaninjas.framework.utility.files.PropertiesFiles;

public class SetLogFile {

	private static SetLogFile instance = null;
	private PropertiesFiles prop = new PropertiesFiles();
	private SetupExecutionFolder executionOn = new SetupExecutionFolder();
	private ExecutionParameters executionParam = new ExecutionParameters();
	
	protected SetLogFile() {
		
	}
	
	public static SetLogFile getInstance() {
		if(null == instance) {
			instance = new SetLogFile();
			instance.SetLogConfiguration();
		}
		return instance;
	}
	
	private void SetLogConfiguration() {
		SetLogPath();
		System.setProperty("logfile.name", ReportConstants.OUTPUT_FOLDER + "logs.log");
		SetUpParameter setUpParameter = new SetUpParameter();
		setUpParameter.setExecutionParameter();
	}

	private void SetLogPath() {
		FrameworkConstants.GLOBALCONFIG  = prop.readProperties(FrameworkConstants.CONFIGFILE);
		executionParam.setupGlobalConfigParameter();
		executionOn.folderStructure();
	}
}
