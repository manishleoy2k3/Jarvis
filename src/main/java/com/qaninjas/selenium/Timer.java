package com.qaninjas.selenium;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;


public class Timer {

	private static Timer instance = null;
	
	private static  Logger logger = Logger.getLogger(Timer.class);
	
	protected Timer() {

	}
	
	public static Timer getInstance() {
		if(null == instance) {
			instance = new Timer();
		}
		return instance;
	}
	
	public void pause(int sleep) {
		try {
			TimeUnit.SECONDS.sleep(sleep);
		} catch (InterruptedException e) {
			logger.error("Error " + e.getMessage());
			Thread.currentThread().interrupt();
		}
	}
}
