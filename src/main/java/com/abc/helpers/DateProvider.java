package com.abc.helpers;

import java.util.Calendar;
import java.util.Date;

public class DateProvider {

	private static DateProvider instance = null;

	public static DateProvider getInstance() {
		if (DateProvider.instance == null) {
			DateProvider.instance = new DateProvider();
		}
		return DateProvider.instance;
	}

	public Date now() {
		return Calendar.getInstance().getTime();
	}

	public Date justDateTomorrow() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		cal.add(Calendar.DATE, 1);
		return cal.getTime();
	}

	public Date addDaysToDate(Date date, int daysToAdd) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, daysToAdd);
		return cal.getTime();
	}

	public int getDaysInThisYear() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		return cal.getActualMaximum(Calendar.DAY_OF_YEAR);
	}
}
