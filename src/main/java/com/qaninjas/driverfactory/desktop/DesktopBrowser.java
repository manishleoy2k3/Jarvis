package com.qaninjas.driverfactory.desktop;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.qaninjas.driverfactory.AbstractBrowser;
import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.SetUpConstants;

public class DesktopBrowser extends AbstractBrowser{

	private WebDriver driver;
	private DesiredCapabilities capabilities;
	private File file = null;
	private ClassLoader classLoader = getClass().getClassLoader();
	private RemoteSetup remote = new RemoteSetup();
	
	private static DesktopBrowser instance = null;
	
	private enum browsers{
		CHROME, FIREFOX, IE
	};
	
	protected DesktopBrowser() {
		getPluginConnection();
	}
	
	public static synchronized DesktopBrowser getInstance() {
		if(null == instance) {
			instance = new DesktopBrowser();
		}
		return instance;
	}

	public WebDriver getDriver() {
		return driver;
	}

	@Override
	public DesiredCapabilities setBrowserCapabilities() {
		switch(browsers.valueOf(FrameworkConstants.DESKTOPCONFIG.get("BROWSER").toUpperCase()))
		{
			case IE:
				capabilities = getIECapabilities(DesiredCapabilities.internetExplorer());
				break;
			case CHROME:
				capabilities = getChromeCapabilities(DesiredCapabilities.chrome());
				break;
			case FIREFOX:
				capabilities = getFirefoxCapabilities(DesiredCapabilities.firefox());
				break;
			default:
				break;
		}
		return capabilities;
	}

	@Override
	public WebDriver setBrowserProperties() {
		driver.manage().timeouts().implicitlyWait(SetUpConstants.DEFAULT_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(SetUpConstants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().setScriptTimeout(SetUpConstants.PAGELOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		return driver;
	}

	@Override
	public WebDriver getPluginConnection() {
		setBrowserCapabilities();
		driver = remote.getRemotePlugIn(capabilities);
		return driver;
	}
	
	private DesiredCapabilities getIECapabilities(DesiredCapabilities ieCapabilities) {
	
		file = new File(classLoader.getResource(FrameworkConstants.DESKTOPCONFIG.get("CHROME_DRIVER")).getFile());
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		ieCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
		ieCapabilities.setCapability("requireWindowFocus", true);
		ieCapabilities.setBrowserName(DesiredCapabilities.internetExplorer().getBrowserName());
		ieCapabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		
		ieCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.DISMISS);
		
		ieCapabilities.setCapability("enablePersistentHover", false);
		ieCapabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		ieCapabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
		ieCapabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-private");
		ieCapabilities.setJavascriptEnabled(true);
		return ieCapabilities;
	}
	
	private DesiredCapabilities getChromeCapabilities(DesiredCapabilities chromeCapabilities) {
		//file = new File(classLoader.getResource(FrameworkConstants.DESKTOPCONFIG.get("CHROME_DRIVER")).getFile());
		//System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		
		System.setProperty("webdriver.chrome.driver", "G:\\workspace\\Jarvis\\src\\test\\resources\\lib\\chromedriver.exe");
		
		chromeCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		chromeCapabilities.setCapability(CapabilityType.SUPPORTS_ALERTS, true);
		chromeCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
		return chromeCapabilities;
	}
	
	private DesiredCapabilities getFirefoxCapabilities(DesiredCapabilities firefoxCapabilities) {
		file = new File(classLoader.getResource(FrameworkConstants.DESKTOPCONFIG.get("FIREFOX_DRIVER")).getFile());
		System.setProperty("webdriver.gecko.driver", file.getAbsolutePath());
		return firefoxCapabilities;
	}
}
