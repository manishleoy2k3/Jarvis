package com.qaninjas.framework.api.interfaces;

public interface IResponseJsonPath {

	void isEqualTo(boolean compareType, Object expectedHeaderValue);
	void fieldLengthEqualTo(int expectedFieldLength);
	void fieldLengthGreatOrEqualTo(int expectedFieldLength);
	void fieldLengthLessOrEqualTo(int expectedFieldLength);
	void contains(String expectedValue);
	void isLessOrEqualTo(Object expectedValue);
	void isGreaterOrEqualTo(Object expectedValue);
}
