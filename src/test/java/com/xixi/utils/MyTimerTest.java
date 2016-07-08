package com.xixi.utils;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Test;

public class MyTimerTest {

	@Test
	public void test() {
		System.out.println(MyTimer.createTime("2015-08-13").toString());;
	}
	@Test
	public void testBetween(){
		LocalDate date1=MyTimer.createTime("2011-02-11");
		LocalDate date2=MyTimer.createTime("2011-04-13");
		System.out.println(MyTimer.getTwoDaysInterVal(date1, date2));
	}

}
