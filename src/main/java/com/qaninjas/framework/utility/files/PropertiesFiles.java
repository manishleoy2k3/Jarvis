package com.qaninjas.framework.utility.files;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

import org.testng.Assert;

public class PropertiesFiles {

	private HashMap<String, String> propData = new HashMap<String, String>();
	
	public HashMap<String, String> readProperties(String filePath) {

		propData = new HashMap<String, String>();
		InputStream input = null;
			
		try {
			input = PropertiesFiles.class.getClassLoader().getResourceAsStream(filePath);
			if(input == null) {
				Assert.fail("Properties file not found at " + filePath + " location");
			}
			
			Properties properties = new Properties();
			properties.load(input);
			
			Enumeration<Object> enuKeys = properties.keys();
			while(enuKeys.hasMoreElements()) {
				String key = (String) enuKeys.nextElement();
				String value = properties.getProperty(key);
				if(value.isEmpty())
					throw new Exception("Value is missing");
				propData.put(key.trim(), value.trim());				
			}			
		} catch (FileNotFoundException e) {
			Assert.fail("Properties file not found at " + e.getMessage());
		} catch (IOException e) {
			Assert.fail("Error while reading Properties file as it is not found....");
		} catch (Exception e) {
			Assert.fail("Error while reading Properties file.." + e.getMessage());
		}		
		return propData;
	}
}
