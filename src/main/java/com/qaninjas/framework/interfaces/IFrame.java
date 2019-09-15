package com.qaninjas.framework.interfaces;

import org.openqa.selenium.By;

public interface IFrame {

	void setDefaultFrame();
	
	void switchToFrame(By elementLocator);
	
	void switchToFrame(int frameIndex);
	
	void switchToFrame(String frameName);
}
