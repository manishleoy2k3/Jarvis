package com.qaninjas.framework.utility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTime {

	public static String getCurrentTime(String timeFormat) {
		SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
		Calendar cal = Calendar.getInstance();
		return sdf.format(cal.getTime());
	}

	public String getCurrentDate(String dateFormat) {
		SimpleDateFormat formmater = new SimpleDateFormat(dateFormat);
		Date date = new Date();
		return formmater.format(date).trim();
	}
	
	public static String getRequiredDate(String dateFormat, int number, String type) {
		Date date = new Date();
		SimpleDateFormat formmater = new SimpleDateFormat(dateFormat);
		
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		if(type.equalsIgnoreCase("Date")) {
			c.add(Calendar.DATE, number);			
		} else if(type.equalsIgnoreCase("Month")){
			c.add(Calendar.MONTH, number);
		} else {
			c.add(Calendar.YEAR, number);
		}
		return formmater.format(c.getTime());
	}
}
