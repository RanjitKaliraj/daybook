package com.daybook.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FormattedDate {
	private static String date;
	
	public static String getDate(){
		Date raw_Date = new Date();
		SimpleDateFormat d_Format = new SimpleDateFormat("yyyy-mm-dd");
		date = d_Format.format(raw_Date).toString();
		return date;
	}
}
