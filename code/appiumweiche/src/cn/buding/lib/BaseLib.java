package cn.buding.lib;

import java.util.Calendar;

public class BaseLib {
	public static String getCurrentTime(){
		try{
			Calendar ca = Calendar.getInstance();
			System.out.println(ca);
			int year = ca.get(Calendar.YEAR);
			int month = ca.get(Calendar.MONTH);
			int day = ca.get(Calendar.DATE);
			int hour = ca.get(Calendar.HOUR);
			int minute = ca.get(Calendar.MINUTE);
			int second = ca.get(Calendar.SECOND);
			String currentTime = (String.valueOf(year) + "-" + String.valueOf(month) 
					+ "-" + String.valueOf(day) + "-" + String.valueOf(hour) + "-" + String.valueOf(minute)
					+ "-" + String.valueOf(second) );
			System.out.println(currentTime);
			return currentTime;
		}catch(Exception e){
			return "00000000";
		}
		
	}

	
	
}
