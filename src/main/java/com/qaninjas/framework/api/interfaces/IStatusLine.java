package com.qaninjas.framework.api.interfaces;

public interface IStatusLine {

	void isEqualTo(boolean compareType, Object expectedStatusLine);
	void contains(String statusLine);
}
