package com.smile.demo.apihandler.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 * 日期时间工具类
 * 
 * @author smile
 *
 */
public final class DateUtils {

	private static DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	
	/**
	 * 此类不需要实例化
	 */
	private DateUtils(){
		
	}
	
	public static String getNowTime() {
		LocalDateTime now = LocalDateTime.now();
		return df.format(now);
	}

	public static String format(String date){
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
		LocalDateTime time = LocalDateTime.parse(date, df);
		return dateTimeFormatter.format(time);
	}


	/**
	 * 判断当前时间是周几
	 * @return 
	 */
	public static Integer getDayOfWeek(){
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK);
	}
	
	/**
	 * 判断当前时间是否是周末
	 * @return true：周末
	 */
	public static boolean isWeekend(){
		Calendar cal = Calendar.getInstance();
		int day = cal.get(Calendar.DAY_OF_WEEK);
		if(day == Calendar.SATURDAY || day == Calendar.SUNDAY){  
		    return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 获取本周的起止日期，从周一开始
	 * @return
	 */
	public static  Map<String,String> getDate(){
		Map<String,String> map = new HashMap<>(8);
		int day = getDayOfWeek();
		map.put("start_date", getDateBeforeNDays(day - 2));
		map.put("end_date", getDateBeforeNDays(day - 8));
		return map;
	}
	
	/**
	 * 获取当前时间n天之前的日期
	 * @param n
	 * @return
	 */
	public static String getDateBeforeNDays(Integer n) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - n);
		String datetime = df.format(cal.getTime());

		return datetime;
	}
	
	/**
	 * 获取当前时间n小时之前的时间
	 * @param n
	 * @return
	 */
	public static String getDateTimeBeforeNHours(Integer n) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY) - n);
		String datetime = df.format(cal.getTime());

		return datetime;
	}
	
	/**
	 * 获取当前时间n个月之前的月份,格式 yyyy-MM
	 * @param n
	 * @return
	 */
	public static String getLastNMonth(Integer n) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - n);
		String datetime = df.format(cal.getTime());

		return datetime;
	}

	/**
	 * 字符串类型的时间转换为时间戳
	 * @param pattern
	 * @param timeString
	 * @return
	 */
	public static Long convertTimeStringToTimestamp(String timeString, String pattern) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(pattern);
		LocalDateTime parse = LocalDateTime.parse(timeString, timeFormatter);
		return LocalDateTime.from(parse).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
	}

	/**
	 * 字符串类型的时间转换为另一格式的时间
	 * @param timeString 待转的字符串
	 * @param sourcePattern 源时间字符串的格式
	 * @param targetPattern 目标字符串的格式
	 * @return
	 */
	public static String formatFromTimeString(String timeString, String sourcePattern, String targetPattern) {
		return convertTimeToString(convertTimeStringToTimestamp(timeString, sourcePattern), targetPattern);
	}

	/**
	 * 将时间戳转换成String类型的时间格式
	 * @param pattern 目标的时间格式
	 * @param timestamp
	 */
	public static String convertTimeToString(long timestamp, String pattern){
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(pattern);
		return timeFormatter.format(ZonedDateTime.ofInstant(Instant.ofEpochMilli(timestamp),ZoneId.systemDefault()));
	}

	public static void main(String[] args) {
		Map<String,String> map = new HashMap<>(8);
		int day = DateUtils.getDayOfWeek();
		map.put("start_date", DateUtils.getDateBeforeNDays(day - 2));
		map.put("end_date", DateUtils.getDateBeforeNDays(day - 8));
		System.out.println(map);
		System.out.println("2018-04-24".substring(0, 7));
		System.out.println(format(getNowTime()));
	}

}
