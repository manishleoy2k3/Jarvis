package com.qaninjas.framework.api.interfaces;

import java.util.concurrent.TimeUnit;

public interface IVerify {

	IResponseHeader header(String headerName);
	IStatusCode statusCode();
	IStatusLine statusLine();
	IResponseTime responseTime(TimeUnit tUnit);
	IResponseJsonPath JsonPath(String jpath);
	IXPath xPath(String xPath);
	void jsonResponseSchema(String filePath);
}
