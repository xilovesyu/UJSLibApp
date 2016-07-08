package com.xixi.utils;

import java.time.LocalDate;
import java.time.Period;

public class MyTimer {
	public static LocalDate getCurrentTime() {
		// 使用jdk1.8的时间
		LocalDate today = LocalDate.now();
		return today;
	}

	public static LocalDate createTime(String text) {
		LocalDate date = null;
		try {
			date = LocalDate.parse(text);
		} catch (Exception e) {
			System.out.println("日期转换异常");
		}
		return date;
	}

	/**
	 * 暂时只能返回几个月集日
	 * */
	public static int[]  getTwoDaysInterVal(LocalDate first,LocalDate second) {
//		String text="";
		Period period=Period.between(first, second);
		int year=period.getYears();
		int month=period.getMonths();
		int days=period.getDays();
//		if(year!=0){text+=year+"年,";};
//		if(month!=0){text+=month+"月,";};
//		if(days!=0){text+=days+"日";};
//	
		return new int[]{year,month,days};
	}
}
