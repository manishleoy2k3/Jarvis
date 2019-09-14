package com.qaninjas.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class AbstractBrowser {

	public abstract DesiredCapabilities setBrowserCapabilities();
	public abstract WebDriver setBrowserProperties();
	public abstract WebDriver getPluginConnection();
}
