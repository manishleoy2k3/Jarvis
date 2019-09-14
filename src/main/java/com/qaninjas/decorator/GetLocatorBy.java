package com.qaninjas.decorator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.qaninjas.decorator.ExtractGetLocatorBy.Locator;

@Retention(RUNTIME)
@Target(FIELD)
public @interface GetLocatorBy {

	public Locator androidDeviceLocator();
	public String androidDeviceLocatorValue();
	public Locator desktopLocator();
	public String desktopLocatorValue();
	public Locator iPhoneDeviceLocator();
	public String iPhoneDeviceLocatorValue();
}
