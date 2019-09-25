package com.qaninjas.accessibility.framework;

import java.io.File;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import com.qaninjas.framework.constants.FrameworkConstants;

public class GetHeadLessBrowser {

	private ClassLoader classLoader = getClass().getClassLoader();
	private File file = null;
	private WebDriver driver = null;	
	private static  Logger logger = Logger.getLogger(GetHeadLessBrowser.class);
	
	public void openHeadLessBrowser(){
		file = new File(classLoader.getResource(FrameworkConstants.DESKTOPCONFIG.get("CHROME_DRIVER")).getFile());
		System.getProperty("webdriver.chrome.driver", file.getAbsolutePath());
		ChromeOptions options = new ChromeOptions();
		options.addArguments("headless");
		options.addArguments("window-size=1200x600");
		driver = new ChromeDriver(options);
		logger.info("Headless browser open to get WCAG guideline. ");
	}
	
	public String getWCAGGuideline(String url){
		String guideline = null;
		driver.get(url);
		guideline = driver.findElement(By.xpath("//div[@class='wcagSc']")).getText().split("WCAG:")[1].trim();
		logger.info("Found WCAG Guideline : " + guideline);
		return guideline;		
	}
	
	public void closeHeadLessBrowser(){
		driver.quit();
		logger.info("Headless browser closed.");
	}
}
