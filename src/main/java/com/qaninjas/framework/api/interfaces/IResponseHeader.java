package com.qaninjas.framework.api.interfaces;

public interface IResponseHeader {

	void isEqualTo(boolean compareType, Object expectedHeaderValue);
	void contains(String expectedHeaderValue);
}
