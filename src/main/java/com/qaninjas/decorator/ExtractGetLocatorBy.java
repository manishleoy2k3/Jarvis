package com.qaninjas.decorator;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;

import com.qaninjas.api.implementation.AddHeaderParameter;
import com.qaninjas.framework.constants.FrameworkConstants;

public class ExtractGetLocatorBy {

	private String propertyValue = null;
	private RunTimeEnv valueFromPropertyFile = RunTimeEnv.valueOf(FrameworkConstants.EXECUTION_ON.toUpperCase());
	private Locator locatorStrategy;
	private static  Logger logger = Logger.getLogger(AddHeaderParameter.class);
	
	public static <T> T initAnnotation(Class<T> pageClassToProxy){
		ExtractGetLocatorBy getLocatorBy = new ExtractGetLocatorBy();
		T page = getLocatorBy.instantiatePage(pageClassToProxy);
		getLocatorBy.getAnnotationPropertyValue(page);
		return page;
	}
	
	private <T> T instantiatePage(Class<T> pageClassToProxy){
		try {
			Constructor<T> constructor = pageClassToProxy.getConstructor();
			return constructor.newInstance();
		}catch(ReflectiveOperationException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	private <T> void getAnnotationPropertyValue(T page){
		
		try {
			Field fields[] = page.getClass().getDeclaredFields();
			for(Field field: fields) {
				if(field.getType().getName().equals("org.openqa.selenium.By")) {
					getAnnotationPropertyValue(field);
					SetLocatorStrategyForField(field, page);
				}
			}
		}catch(IllegalArgumentException ex) 
		{
			logger.error("Got IllegalArgumentException " + ex.getCause());
		}catch(IllegalAccessException ex) 
		{
			logger.error("Got IllegalAccessException " + ex.getCause());
		}
		catch(Exception ex) {
			logger.debug("This class does not have any fields with annotation present " + ex.getCause());
		}
	}

	private void getAnnotationPropertyValues(Field currentField) {
		GetLocatorBy objAnnotation = currentField.getAnnotation(GetLocatorBy.class);
		switch(valueFromPropertyFile) {
			case MOBILE:
				RunTimeEnv valueFromPropertyMobile = RunTimeEnv.valueOf(FrameworkConstants.MOBILECONFIG.get("PLATFORM_OS").toUpperCase());
				switch(valueFromPropertyMobile) {
					case IPHONE:
						locatorStrategy = objAnnotation.iPhoneDeviceLocator();
						propertyValue = objAnnotation.iPhoneDeviceLocatorValue();
						break;
					case ANDROID:
						locatorStrategy = objAnnotation.androidDeviceLocator();
						propertyValue = objAnnotation.androidDeviceLocatorValue();
						break;
					default:
						logger.debug("Please define correct environment Type");
						break;
				}
			case DESKTOP:
				locatorStrategy = objAnnotation.desktopLocator();
				propertyValue = objAnnotation.desktopLocatorValue();
				break;
			default:
				logger.debug("Please define correct environment Type");
				break;
		}
	}
	
	private <T> void SetLocatorStrategyForField(Field currentField, T page) throws IllegalArgumentException, IllegalAccessException
	{
		switch(locatorStrategy) {
			case ID:
				logger.debug("Returning by object for value of id: " + propertyValue);
				currentField.set(page, By.id(propertyValue));
				break;
			case NAME:
				logger.debug("Returning by object for value of name: " + propertyValue);
				currentField.set(page, By.name(propertyValue));
				break;
			case CSS:
				logger.debug("Returning by object for value of css: " + propertyValue);
				currentField.set(page, By.cssSelector(propertyValue));
				break;
			case XPATH:
				logger.debug("Returning by object for value of xpath: " + propertyValue);
				currentField.set(page, By.xpath(propertyValue));
				break;
			case LINKTEXT:
				logger.debug("Returning by object for value of linkText: " + propertyValue);
				currentField.set(page, By.linkText(propertyValue));
				break;
			case PARTIALLINKTEXT:
				logger.debug("Returning by object for value of partial link Text: " + propertyValue);
				currentField.set(page, By.partialLinkText(propertyValue));
				break;
			case TAGNAME:
				logger.debug("Returning by object for value of tagname: " + propertyValue);
				currentField.set(page, By.tagName(propertyValue));
				break;
			case CLASSNAME:
				logger.debug("Returning by object for value of class name: " + propertyValue);
				currentField.set(page, By.className(propertyValue));
				break;
		}
	}

	public enum RunTimeEnv
	{
		IPHONE,
		ANDROID,
		DESKTOP,
		MOBILE
	}
	
	public enum Locator
	{
		ID,
		NAME,
		TAGNAME,
		CSS,
		LINKTEXT,
		PARTIALLINKTEXT,
		XPATH,
		CLASSNAME
	}
}
