package com.cruds.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private static final SimpleDateFormat dateFormatter= new SimpleDateFormat("dd-MM-yyyy");
	
	public static Date getCurrentDate()
	{
		Date date= new Date();
		return date;
	}
	
	public static Date addToCurrentDate(int noOfDays)
	{
		Date today= new Date();
		Calendar cal=Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, noOfDays);
		return cal.getTime();
	}
	
	public static Date getUtilDateFromString(String strDate)
	{
		Date date=null;
		
		try
		{
			date=dateFormatter.parse(strDate);
		}
		catch(ParseException e)
		{
			e.printStackTrace();
		}
		
		return date;
	}
	
	public static java.sql.Date getSQLDate(java.util.Date utilDate)
	{
		return new java.sql.Date(utilDate.getTime());
	}
}
