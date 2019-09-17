package com.qaninjas.driverfactory.desktop;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qaninjas.driverfactory.AbstractRemoteSetup;
import com.qaninjas.driverfactory.mobile.MobileBrowser;
import com.qaninjas.remotetools.Appium;
import com.qaninjas.remotetools.SeeTest;

public class RemoteSetup extends AbstractRemoteSetup{

	private static  Logger logger = Logger.getLogger(RemoteSetup.class);
	private WebDriver driver;
	private Appium appium = new Appium();
	private SeeTest seetest = new SeeTest();
	
	private enum browsers{
		CHROME, FIREFOX, IE
	};
	
	private enum remote{
		LOCAL, SELENIUMGRID, SEETEST, APPIUM
	};
	
	@Override
	protected WebDriver setRemotePlugIn(DesiredCapabilities capabilities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WebDriver getRemotePlugIn(DesiredCapabilities capabilities) {
		return setRemotePlugIn(capabilities);
	}

}
