package com.xixi.LibApp;

import java.io.File;

public class Library {
	private String loginPage = "http://huiwen.ujs.edu.cn:8080/reader/login.php";
	private String yanzhengpage = "http://huiwen.ujs.edu.cn:8080/reader/captcha.php";
	private String readerVertifyPage = "http://huiwen.ujs.edu.cn:8080/reader/redr_verify.php";
	private String readerInfoPage = "http://huiwen.ujs.edu.cn:8080/reader/redr_info.php";
	private String bookListPage = "http://huiwen.ujs.edu.cn:8080/reader/book_lst.php";
	private String userDetailPage="http://huiwen.ujs.edu.cn:8080/reader/redr_info_rule.php";
	private File cookiefile = new File("cookie"+File.separator+"cookie.txt");
	
}
