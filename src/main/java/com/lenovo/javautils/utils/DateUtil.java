package com.lenovo.javautils.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	/**
	 * date格式化为:2017-11-12
	 * @author dcx
	 * @date 2017年10月31日13:53:12
	 * @param date
	 * @return
	 */
	public static String getDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		return str;
	}
	
	/**
	 * 获取n天前的时间
	 * @author daicx1
	 * @date 2018年2月28日 上午10:22:34
	 * @param date
	 * @param n
	 * @return
	 */
	public static Date getDayAgoDate(Date date,Integer n) {
		Calendar now =Calendar.getInstance();  
		now.setTime(date);  
		now.set(Calendar.DATE,now.get(Calendar.DATE)-n);
		return now.getTime();
	}
}
