package com.qaninjas.framework.interfaces;

import java.util.List;

public interface IDropdown {

	List<String> getAllOptions();
	
	void selectbyVisibleText(String value);
	
	void selectbyIndex(int index);
	
	void selectbyValue(String value);
	
	String getFirstSelectedOption();
	
}
