package com.qaninjas.framework.utility.selenium;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.annotations.ITestAnnotation;

public class AnnotationTransformer implements IAnnotationTransformer{
	
	@SuppressWarnings("rawtypes")
	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		String testCase = testMethod.getName();
		if(testCase.equalsIgnoreCase("")) {
			annotation.setAlwaysRun(true);
			annotation.setEnabled(true);
		} else {
			annotation.setAlwaysRun(false);
			annotation.setEnabled(false);
		}
	}

}
