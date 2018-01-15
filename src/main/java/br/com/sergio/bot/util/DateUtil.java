package br.com.sergio.bot.util;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

	public static final Date minDateWithoutTime() {
		return dateWithoutTime(new Date());
	}

	public static final Date dateBeforeWithoutTime() {
		return Date.from(LocalDate.now().minus(1, ChronoUnit.DAYS).atTime(0, 0, 0).toInstant(ZoneOffset.UTC));
	}

	public static final Date dateCurrentWithoutTime() {
		return Date.from(LocalDate.now().atTime(0, 0, 0).toInstant(ZoneOffset.UTC));
	}

	// public static final Date dateCurrentWithoutTime(Long timestamp) {
	// return Date.from(LocalDate.ofEpochDay(timestamp).atTime(0, 0, 0));
	//
	// }

	public static void main(String[] args) {
		System.out.println(dateCurrentWithoutTime());
	}

	public static final Date dateWithoutTime(Date data) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(data);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.HOUR_OF_DAY, 0);

		return gc.getTime();
	}

	public static final Date dateWithoutTime(Long timestamp) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTimeInMillis(timestamp * 1000);
		gc.set(Calendar.SECOND, 0);
		gc.set(Calendar.MINUTE, 0);
		gc.set(Calendar.HOUR_OF_DAY, 0);

		return gc.getTime();
	}
}
