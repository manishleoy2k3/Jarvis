package com.qaninjas.framework.interfaces;

import org.openqa.selenium.By;

public interface ITouchAction {

	void swipeLeft(int numOfSwipe);
	
	void swipeLeft(By locatorElement, int numOfSwipe);
	
	void swipeRight(int numOfSwipe);
	
	void swipeRight(By locatorElement, int numOfSwipe);
	
	void scrollUp(int numOfScroll);
	
	void scrollDown(int numOfScroll);
	
	void scrollDown(By locatorElement, int numOfScroll);
	
	void scrollUp(By locatorElement, int numOfScroll);
	
	boolean scrollTo(int scrollViewIndex, String listElementClass, String textOnListElement);
	
	boolean scrollTo(String uiSelectorQuery);
	
	void dragFromTo(Integer xStart, Integer yStart, Integer xEnd, Integer yEnd);
	
	void scrollFromTo(Integer xStart, Integer yStart, Integer xEnd, Integer yEnd);
}
