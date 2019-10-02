package com.qaninjas.driverfactory.desktop;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.qaninjas.driverfactory.AbstractRemoteSetup;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.remotetools.Appium;
import com.qaninjas.remotetools.SeeTest;

@SuppressWarnings("deprecation")
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
		try {
			switch(remote.valueOf(FrameworkConstants.EXECUTION_MODE.toUpperCase())) {			
			case LOCAL:
				driver = getLocalDriver(capabilities);
				break;
			case SELENIUMGRID:
				driver = new RemoteWebDriver(new URL(FrameworkConstants.GLOBALCONFIG.get("REMOTE_URL")), capabilities);
				break;
			case APPIUM:
				driver = appium.getAppiumDriver(capabilities);
				break;
			case SEETEST:
				driver = seetest.getSeeTestDriver(capabilities);
				break;
			default:
				logger.info("Select appropriate execution mode");
				break;		
			}
		} catch (MalformedURLException e) {
			logger.info("Exception...." + e.getMessage());
		}
		return driver;
	}

	private WebDriver getLocalDriver(DesiredCapabilities capabilities) {
		switch(browsers.valueOf(FrameworkConstants.DESKTOPCONFIG.get("BROWSER").toUpperCase())) {			
			case IE:
				driver = new InternetExplorerDriver(capabilities);
				break;
			case CHROME:
				//ChromeOptions options = new ChromeOptions();
				//options.addArguments("start-maximized");
				//driver = new ChromeDriver(options);
				driver = new ChromeDriver(capabilities);
				break;
			case FIREFOX:
				driver = new FirefoxDriver(capabilities);
				break;
			default:
				logger.info("Select appropriate browser to execute");
				break;		
		}
		return driver;
	}

	@Override
	public WebDriver getRemotePlugIn(DesiredCapabilities capabilities) {
		return setRemotePlugIn(capabilities);
	}

}
