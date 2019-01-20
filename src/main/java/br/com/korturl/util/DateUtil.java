/*
 * 
 */
package br.com.korturl.util;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

import br.com.korturl.exception.UtilConstructException;

/**
 * The Class DateUtil.
 */
public class DateUtil {
	
	/**
	 * Instantiates a new date util.
	 */
	private DateUtil(){
		throw new UtilConstructException("Classe não pode ser instanciada");
	}
	
	/**
	 * Data to local date time.
	 *
	 * @param date the date
	 * @return the local date time
	 */
	public static LocalDateTime dataToLocalDateTime(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		ZonedDateTime zdt = cal.toZonedDateTime();
		return zdt.toLocalDateTime();
	}

	/**
	 * Local date time to date.
	 *
	 * @param ldt the ldt
	 * @return the date
	 */
	public static Date localDateTimeToDate(LocalDateTime ldt) {
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.systemDefault());
		GregorianCalendar cal = GregorianCalendar.from(zdt);
		return cal.getTime();
	}
}
