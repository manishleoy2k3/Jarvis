package com.qaninjas.framework.utility;

import org.testng.Assert;

public class StringUtility {

	public boolean returnBoolean(String value) {
		if(value.equalsIgnoreCase("yes")) {
			return true;
		}else if(value.equalsIgnoreCase("no")) {
			return false;
		} else {
			Assert.fail("Value should be Yes/No" + value);
		}
		return false;
	}
}
