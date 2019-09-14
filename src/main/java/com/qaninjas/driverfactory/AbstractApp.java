package com.qaninjas.driverfactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public abstract class AbstractApp {

	public abstract DesiredCapabilities setAppDesiredCapabilities();
	public abstract WebDriver setAppProperties();
	public abstract WebDriver getPluginConnection();
}
