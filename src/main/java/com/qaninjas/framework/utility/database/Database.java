package com.qaninjas.framework.utility.database;

public interface Database {

	void getConnection();
	public void getQueryData(String query);
	public int insertOrUpdate(String query);
}
