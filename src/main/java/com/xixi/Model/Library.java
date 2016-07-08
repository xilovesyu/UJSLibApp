package com.xixi.Model;

import java.io.File;

public class Library {
	private static  String loginPage = "http://huiwen.ujs.edu.cn:8080/reader/login.php";
	private  static String yanzhengPage = "http://huiwen.ujs.edu.cn:8080/reader/captcha.php";
	private static  String readerVertifyPage = "http://huiwen.ujs.edu.cn:8080/reader/redr_verify.php";
	private static  String readerInfoPage = "http://huiwen.ujs.edu.cn:8080/reader/redr_info.php";
	private  static String bookListPage = "http://huiwen.ujs.edu.cn:8080/reader/book_lst.php";
	private  static String userDetailPage = "http://huiwen.ujs.edu.cn:8080/reader/redr_info_rule.php";
	private  static File cookiefile = new File("cookie" + File.separator + "cookie.txt");
	public static String getLoginPage() {
		return loginPage;
	}
	public static String getYanzhengPage() {
		return yanzhengPage;
	}
	public static  String getReaderVertifyPage() {
		return readerVertifyPage;
	}
	public static  String getReaderInfoPage() {
		return readerInfoPage;
	}
	public  static String getBookListPage() {
		return bookListPage;
	}
	public static  String getUserDetailPage() {
		return userDetailPage;
	}
	public  static File getCookiefile() {
		return cookiefile;
	}

	
}
