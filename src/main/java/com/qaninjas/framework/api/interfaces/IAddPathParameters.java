package com.qaninjas.framework.api.interfaces;

import java.util.HashMap;

public interface IAddPathParameters {

	IAddParameter addParameter(String parameter);
	
	void addParameterMap(HashMap<String, String> pathParametersHashMap);
}
