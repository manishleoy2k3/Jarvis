package com.qaninjas.framework.api.interfaces;

import java.util.HashMap;

public interface IRequest {

	void addHeadersParameters(HashMap<String, String> addHeadersParameters);
	void queryParameters(HashMap<String, String> queryParameters);
	IAddHeadersParameters addHeader();
	IAddQueryParameters addQuery();
}
