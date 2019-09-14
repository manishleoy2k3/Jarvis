package com.qaninjas.framework;

import com.qaninjas.framework.utility.report.ExtentManager;

public class Initialize {

	private static Initialize intance  = null;
	
	protected Initialize() {
		SetLogFile setlog = SetLogFile.getInstance();
	}
	
	public static Initialize getInstance() {
		if(null == intance) {
			intance = new Initialize();
			ExtentManager.getInstance().getExtent();
		}
		return intance;
	}
}
