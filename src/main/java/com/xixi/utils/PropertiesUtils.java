package com.xixi.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesUtils {
	public static String getPro(String key) {
		Properties prop = new Properties();// 属性集合对象
		FileInputStream fis=null;
		try {
			fis = new FileInputStream("value.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 属性文件流
		String value;
		try {
			prop.load(fis);
			fis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 将属性文件流装载到Properties对象中
		
		return prop.getProperty(key);
	}
}
