package com.jou.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	// 将Util.Date转换为字符串类型
	public static String formatDate(Date date, String format){
		String result="";
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		if(date!=null){
			result=sdf.format(date);
		}
		return result;
	}
		//将字符串转换为Util.Date;
	public static Date formatString(String str,String format) throws Exception{
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		return sdf.parse(str);
	}
	
	/**
	 * 计算两个日期之差
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static Long[] getDateBetweenHour(Date endDate, Date nowDate) {	
	    long nd = 1000 * 24 * 60 * 60; //1天多少毫秒
	    long nh = 1000 * 60 * 60;      //1小时多少毫秒
	    long nm = 1000 * 60;           //1分钟多少毫秒
	    // long ns = 1000;
	    // 获得两个时间的毫秒时间差?
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少天
	    long day = diff / nd;
	    // 计算差多少小时?
	    long hour = diff % nd / nh;
	    // 计算差多少分钟?
	    long min = diff % nd % nh / nm;
	    // 计算差多少秒//输出结果
	    //long sec = diff % nd % nh % nm / ns;
	    Long[] res = new Long[3];
	    res[0] = day;
	    res[1] = hour;
	    res[2] = min;
	    return res;
	}

}