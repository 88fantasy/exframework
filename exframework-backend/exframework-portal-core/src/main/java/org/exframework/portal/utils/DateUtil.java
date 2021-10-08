package org.exframework.portal.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2016
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author not attributable
 * @version 1.0
 */

public class DateUtil {
	static private Log log = LogFactory.getLog(DateUtil.class);

//	private static DateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd");

//	private static DateFormat dateTimeFormat = new SimpleDateFormat(
//			"yyyy-MM-dd HH:mm:ss");

	//支持多线程 clq 20130722
	
	private static final FastDateFormat simpleFormat = FastDateFormat.getInstance("yyyy-MM-dd");
	
	private static final FastDateFormat dateTimeFormat = FastDateFormat.getInstance("yyyy-MM-dd HH:mm:ss");
	
	private static String[] dateTimePatterns = {"yyyy-MM-dd HH:mm:ss"};
	private static String[] datePatterns = {"yyyy-MM-dd"};

	public DateUtil() {
	}
	
	

	public static Calendar getCalendar() {
		return GregorianCalendar.getInstance();
	}

	/**
	 * 取得指定日期加减一定年份后的日期
	 * 
	 * @param date
	 *            指定日期
	 * @param years
	 *            年数
	 * @return 指定日期加减年份后得到的日期
	 */
	public static Date addYears(Date date, int years) {
		// 指定日期的年份加一定年份
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.YEAR, years);
		return gc.getTime();
		// return new Date(gc.get(Calendar.YEAR) - 1900, gc.get(Calendar.MONTH),
		// gc.get(Calendar.DATE));
	}

	/**
	 * 取得指定日期加减一定月份后的日期
	 * 
	 * @param date
	 *            指定日期
	 * @param months
	 *            月数
	 * @return 指定日期加减月份后得到的日期
	 */
	public static Date addMonths(Date date, int months) {
		// 指定日期的月份加一定月份
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.MONTH, months);
		return gc.getTime();
		// return new Date(gc.get(Calendar.YEAR) - 1900, gc.get(Calendar.MONTH),
		// gc.get(Calendar.DATE));
	}

	/**
	 * 取得指定日期加减一定天数后的日期
	 * 
	 * @param date
	 *            指定日期
	 * @param days
	 *            天数
	 * @return 指定日期加减天数后得到的日期
	 */
	public static Date addDays(Date date, int days) {
		// 指定日期加一定天数
		GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
		gc.setTime(date);
		gc.add(Calendar.DATE, days);
		return gc.getTime();
		// return new Date(gc.get(Calendar.YEAR) - 1900, gc.get(Calendar.MONTH),
		// gc.get(Calendar.DATE));
	}

	/**
	 * 获取字符串形式的当前日期
	 * 
	 * @return yyyy-MM-dd格式的日期
	 */
	public static String getStringNow() {
		return simpleFormat.format(new Date());
	}

	/**
	 * 格式化日期,日期格式yyyy-MM-dd
	 * 
	 * @param date
	 *            日期
	 * @return 日期字符串
	 */
	public static String format(Date date) {
		return simpleFormat.format(date);
	}

	public static String formatDTime(Date date) {
		return dateTimeFormat.format(date);
	}

	/**
	 * 解析日期
	 * 
	 * @param stringDate
	 *            日期字符串
	 * @return 日期,如无法解析则返回null
	 */
	public static Date parse(String stringDate) {
		try {
			if(stringDate.contains("/")){
				stringDate = stringDate.replace("/", "-");
			}
			if (stringDate.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
				return DateUtils.parseDate(stringDate, datePatterns);
						
			} else if (stringDate
					.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}")) {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(stringDate);
						
			} else if (stringDate
					.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}")) {
				return DateUtils.parseDate(stringDate, dateTimePatterns);
						
			} else if (stringDate
					.matches("\\d{4}-\\d{1,2}-\\d{1,2}\\s\\d{1,2}:\\d{1,2}:\\d{1,2}.0")) {
				String newDate = stringDate.substring(0, stringDate.length()-2);
				return DateUtils.parseDate(newDate, dateTimePatterns);
						
			} else if (stringDate
					.matches("\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\+\\d{4}")) {
				return new SimpleDateFormat(
						org.apache.commons.lang3.time.DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT
								.getPattern()).parse(stringDate);
			} else if (stringDate
					.matches("\\d{4}-\\d{1,2}-\\d{1,2}T\\d{1,2}:\\d{1,2}:\\d{1,2}\\+\\d{1,2}:\\d{1,2}")) {
				String newDate = stringDate.substring(0, 22)
						+ stringDate.substring(23);
				return new SimpleDateFormat(
						org.apache.commons.lang3.time.DateFormatUtils.ISO_DATETIME_TIME_ZONE_FORMAT
								.getPattern()).parse(newDate);
			} else {
				return DateUtils.parseDate(stringDate, datePatterns);
			}
		} catch (ParseException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 时间类型转化
	 * 
	 * @param date
	 *            格式化前的日期
	 * @return 格式化后的日期
	 */
	public static Date changeDate(Date date) {
		SimpleDateFormat dateformat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		Date formatdate = null;
		try {
			formatdate = dateformat.parse(dateformat.format(date));
		} catch (ParseException ex) {
			log.error(ex.getMessage(), ex);
			throw new RuntimeException(ex);
		}
		return formatdate;
	}

	public static Date calcMonthFirstDay() {
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
		return new Date(cpcalendar.getTimeInMillis());
	}

	public static Date calcMonthEndDay() {
		Calendar calendar = Calendar.getInstance();
		Calendar cpcalendar = (Calendar) calendar.clone();
		cpcalendar.set(Calendar.DAY_OF_MONTH, 1);
		cpcalendar.add(Calendar.MONTH, 1);
		cpcalendar.add(Calendar.DATE, -1);
		return new Date(cpcalendar.getTimeInMillis());
	}
	
}
