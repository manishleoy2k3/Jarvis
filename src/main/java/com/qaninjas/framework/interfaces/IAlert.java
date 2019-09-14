package com.qaninjas.framework.interfaces;

public interface IAlert {

	boolean alertIsPresent();
	boolean alertIsPresentInTime(int time);
	void close();
	void accept();
	void switchToAlert();
}
