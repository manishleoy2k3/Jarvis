package com.qaninjas.selenium;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qaninjas.framework.constants.FrameworkConstants;
import com.qaninjas.framework.constants.ReportConstants;
import com.qaninjas.framework.utility.DateTime;

public class ScreenShot {

	private static  Logger logger = Logger.getLogger(ScreenShot.class);
	
	public String takeSnapShot(WebDriver driver) {
		String screenshotPath = "";
		String fileName = DateTime.getCurrentTime("ddMMyyyyhhmmss");
		
		try {
			if(FrameworkConstants.EXECUTION_ON.equalsIgnoreCase("desktop"))
				driver.manage().window().maximize();
			TimeUnit.SECONDS.sleep(1);
			screenshotPath = ReportConstants.SCREENSHOT_LOCATION + File.separator  + fileName + ".png";
			File pngFile = new File(screenshotPath);
			File screenShot  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			logger.error("Screenshot saved at location..." + screenshotPath);
			FileUtils.copyFile(screenShot, pngFile);
		} catch (Exception e) {
			logger.error("Exception..." + e.getMessage());
		}
		return fileName + ".png";
	}
	
	public String takeSnapShot(WebDriver driver, WebElement element) {
		
		String fileName = DateTime.getCurrentTime("ddMMyyyyhhmmss");
		File screenShot  = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		Point p = element.getLocation();
		int width = element.getSize().getWidth();
		int height = element.getSize().getHeight();
		
		Rectangle rect = new Rectangle(width, height);
		BufferedImage img = null;
				
		try {
			img  = ImageIO.read(screenShot);
		} catch (Exception e) {
			logger.error("Exception..." + e.getMessage());
		}
		BufferedImage dest = img.getSubimage(p.getX(), p.getY(), rect.width, rect.height);

		try {
			ImageIO.write(dest, "png", screenShot);		
			FileUtils.copyFile(screenShot, new File(ReportConstants.SCREENSHOT_LOCATION + File.separator
					+ "" + File.separator + fileName + ".png"));
		} catch (IOException e) {
			logger.error("Exception..." + e.getMessage());
		}		
		return fileName + ".png";
	}
}
