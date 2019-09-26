package com.qaninjas.app.test;

import com.qaninjas.framework.RunTestNG;

public class FrameworkAppTest {

	private static RunTestNG testNG = new RunTestNG();
	
	public static void main(String[] args) {
		testNG.executeTestNGXML("src/test/resources/xml/mobileapp.xml");
	}

}
