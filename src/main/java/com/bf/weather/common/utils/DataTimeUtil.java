package com.bf.weather.common.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * @Author:DingPengwei
 * @Email:www.dingpengwei@gmail.com
 * @DateCrate:Feb 23, 2014 9:32:08 AM
 * @DateUpdate:Feb 23, 2014 9:32:08 AM
 * @Des:提供日期时间的操作
 */
public class DataTimeUtil {

	/**
	 * 时间格式转换
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 9:40:02 AM
	 * @param yyyy年MM月dd日 HH时mm分ss秒
	 * @return yyyy-MM-dd HH-mm-ss
	 */
	public static String dateFormaterC2E(String date){
		try {
			DateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒",Locale.CHINA); 
			Date parse = format.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss",Locale.CHINA);
			String dateString = formatter.format(parse);
			return dateString;
		} catch (Exception e) {
		}
		return date;
	}
	
	/**
	 * 时间格式转换
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 9:41:25 AM
	 * @param yyyy-MM-dd HH-mm-ss
	 * @return yyyy年MM月dd日 HH时mm分ss秒
	 */
	public static String dateFormaterE2C(String date){
		try {
			DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss",Locale.CHINA); 
			Date parse = format.parse(date);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒",Locale.CHINA);
			String dateString = formatter.format(parse);
			return dateString;
		} catch (Exception e) {
		}
		return date;
	}
	
	/**
	 * 获取当前日期
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 9:35:06 AM
	 * @return
	 */
	public static String getDate(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.CHINA);
		Date date = new Date();
		String format = simpleDateFormat.format(date);
		return format;
	}
	
	/**
	 * 获取当前日期时间
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 9:38:10 AM
	 * @return
	 */
	public static String getDateTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss",Locale.CHINA);
		Date date = new Date();
		String format = simpleDateFormat.format(date);
		return format;
	}
	
	/**
	 * 获取当前时间
	 * @author:DingPengwei
	 * @date:Feb 23, 2014 9:37:17 AM
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss",Locale.CHINA);
		Date date = new Date();
		String format = simpleDateFormat.format(date);
		return format;
	}
}
