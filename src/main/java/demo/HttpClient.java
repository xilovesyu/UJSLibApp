package demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xixi.Model.Book;
import com.xixi.Model.Person;
import com.xixi.Recog.MyParser;
import com.xixi.utils.PropertiesUtils;

public class HttpClient {

	private CloseableHttpClient httpclient = null;
	BasicCookieStore cookieStore = null;
	String name = "2211508055";
	String pass =PropertiesUtils.getPro("mylibCode");
	String loginpage = "http://huiwen.ujs.edu.cn:8080/reader/login.php";
	String yanzheng = "http://huiwen.ujs.edu.cn:8080/reader/captcha.php";
	String url = "http://huiwen.ujs.edu.cn:8080/reader/redr_verify.php";
	String read_info = "http://huiwen.ujs.edu.cn:8080/reader/redr_info.php";
	String current_book = "http://huiwen.ujs.edu.cn:8080/reader/book_lst.php";
	String user_detail="http://huiwen.ujs.edu.cn:8080/reader/redr_info_rule.php";
	File cookiefile = new File("cookie"+File.separator+"cookie.txt");
	public void setName(String name){
		this.name=name;
	}
	public void setPass(String pass){
		this.pass=pass;
	}
	public HttpClient() {
		cookieStore = new BasicCookieStore();
		httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
	}
	public HttpClient(String name,String pass){
		this.name=name;
		this.pass=pass;
		cookiefile=new File("cookie"+File.separator+name+"Cookie.txt");
	}
	
	public CloseableHttpResponse Get(String url) {
		CloseableHttpResponse response = null;
		HttpGet httpGet = new HttpGet(url);
		try {
			response = httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public CloseableHttpResponse Get(String url, Cookie cookie) {
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.setHeader("Accept-Encoding", "gzip, deflate, sdch");
		httpGet.setHeader("Accept-Language", "zh-CN,zh;q=0.8,zh-TW;q=0.6,en;q=0.4,ja;q=0.2");
		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84 Safari/537.36");
		httpGet.setHeader("Cookie", cookie.getName() + "=" + cookie.getValue());
		CloseableHttpResponse response = null;
		try {
			response = new HttpClient().httpclient.execute(httpGet);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
	public InputStream Get_InputStream(String uri) {
		HttpGet httpGet = new HttpGet(uri);
		InputStream input = null;
		try {
			CloseableHttpResponse response = httpclient.execute(httpGet);
			HttpEntity entity = response.getEntity();
			input = entity.getContent();

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return input;
	}

	public InputStream Get_InputStream(String url, Cookie cookie) {
		// Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8
		// Accept-Encoding:gzip, deflate, sdch
		// Accept-Language:zh-CN,zh;q=0.8,zh-TW;q=0.6,en;q=0.4,ja;q=0.2
		// Cache-Control:max-age=0
		// Connection:keep-alive
		// Cookie:safedog-flow-item=9CCBECB0A069DC3E03346A732D48B2BD;
		// PHPSESSID=5gitft7nh6ft71ijp7at7r25t1
		// Host:huiwen.ujs.edu.cn:8080
		// Referer:http://huiwen.ujs.edu.cn:8080/reader/redr_info.php
		// Upgrade-Insecure-Requests:1
		// User-Agent:Mozilla/5.0 (Windows NT 6.1; Win64; x64)
		// AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.84
		// Safari/537.36
		try {
			return Get(url,cookie).getEntity().getContent();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public CloseableHttpResponse Post(String uri, List<NameValuePair> list){
		HttpPost httpPost = new HttpPost(uri);
		CloseableHttpResponse response2 =null;
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(list));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			response2 = httpclient.execute(httpPost);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response2;
	}
	
	public InputStream Post_InputStream(String uri, List<NameValuePair> list) {
		try {
			return Post(uri, list).getEntity().getContent();
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void getYZM() {
		InputStream yzm = Get_InputStream(yanzheng);
		File yzmFile = new File("yzm.png");
		FileOutputStream fout = null;
		try {
			fout = new FileOutputStream(yzmFile);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			FileUtils.copyInputStreamToFile(yzm, yzmFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			yzm.close();
			fout.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String recogYZM(File f) {
		return MyParser.recogYZM(f);
	}

	public boolean login() {
		getYZM();
		String yzmbak = recogYZM(new File("yzm.png"));
		List<NameValuePair> lists = new ArrayList<NameValuePair>();
		lists.add(new BasicNameValuePair("number", name));
		lists.add(new BasicNameValuePair("passwd", pass));
		lists.add(new BasicNameValuePair("captcha", yzmbak));
		lists.add(new BasicNameValuePair("select", "cert_no"));
		lists.add(new BasicNameValuePair("returnUrl", ""));
		// 验证，后面做cookie持久化
		InputStream response = Post_InputStream(url, lists);
		String text=null;
		try {
			text = IOUtils.toString(response);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if(text.contains("对不起，密码错误，请查实！")||text.contains("验证码错误(wrong check code)")){
			System.out.println("登录失败");
			return false;
		}
		else{
			Cookie cookie = cookieStore.getCookies().get(0);
//			storeCookie(cookie);
		}
		return true;
	}
	public boolean isLogin(){
		boolean flag=true;
		CloseableHttpResponse borrowResponse = Get(current_book);
		CloseableHttpResponse loginResponse = Get(loginpage);
		String pageBorrow;
		String pageLogin;
		try {
			pageBorrow = IOUtils.toString(borrowResponse.getEntity().getContent());
			pageLogin = IOUtils.toString(loginResponse.getEntity().getContent());
			if (pageBorrow.equals(pageLogin)) {
				flag=false;
			}
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	public String getName(){
		CloseableHttpResponse borrowResponse = Get(read_info);
		Document doc = null;
		try {
			doc = Jsoup.parse(IOUtils.toString(borrowResponse.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc.getElementsByClass("profile-name").get(0).text();
	}
	public Person getProfile(){
		CloseableHttpResponse indexResponse = Get(read_info);
		CloseableHttpResponse userDetailResponse = Get(user_detail);
		Document indexdoc = null;
		Document userdetaildoc = null;
		try {
			indexdoc = Jsoup.parse(IOUtils.toString(indexResponse.getEntity().getContent()));
			userdetaildoc = Jsoup.parse(IOUtils.toString(userDetailResponse.getEntity().getContent()));
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element div=userdetaildoc.getElementById("mylib_info");
		Elements tds=div.select("td");//
		Person p=new Person();
		p.setName(indexdoc.getElementsByClass("profile-name").get(0).text());
		p.setTotalJiFen(indexdoc.getElementsByClass("bigger-170").get(3).text());
		p.setAvailableJiFen(indexdoc.getElementsByClass("bigger-170").get(4).text());
		p.setStuNum(tds.get(2).ownText().trim());
		p.setEmail(tds.get(16).ownText().trim());
		p.setIdNum(tds.get(17).ownText().trim());
		p.setSex(tds.get(21).ownText().trim());
		p.setAddress(tds.get(22).ownText().trim());
		p.setTelephone(tds.get(25).ownText().trim());
		return p;
	}
	public void getBorrowBooks(Cookie cookie) {
		CloseableHttpResponse borrowResponse = Get(current_book, cookie);
		CloseableHttpResponse loginResponse = Get(loginpage);
		String pageBorrow = null;
		String pageLogin = null;
		try {
			pageBorrow = IOUtils.toString(borrowResponse.getEntity().getContent());
			pageLogin = IOUtils.toString(loginResponse.getEntity().getContent());
			if (pageBorrow.equals(pageLogin)) {
				login();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		borrowResponse = Get(current_book, getCookie());
		try {
			pageBorrow = IOUtils.toString(borrowResponse.getEntity().getContent());
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		doc = Jsoup.parse(pageBorrow);
		Elements table = doc.getElementsByClass("table_line");
		Elements trs = table.select("tr");
		for (int i = 1; i < trs.size(); i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
			for (int j = 0; j < tds.size(); j++) {
				String text = tds.get(j).text();
				System.out.print(text + "\t");
			}
			System.out.println();
		}
	}
	public ArrayList<Book> getBorrowBooks(){
		ArrayList<Book> lists=new ArrayList<Book>();
		CloseableHttpResponse borrowResponse = Get(current_book);
		CloseableHttpResponse loginResponse = Get(loginpage);
		String pageBorrow = null;
		String pageLogin = null;
		try {
			pageBorrow = IOUtils.toString(borrowResponse.getEntity().getContent());
			pageLogin = IOUtils.toString(loginResponse.getEntity().getContent());
			if (pageBorrow.equals(pageLogin)) {
				login();
			}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		borrowResponse = Get(current_book);
		try {
			pageBorrow = IOUtils.toString(borrowResponse.getEntity().getContent());
		} catch (UnsupportedOperationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Document doc = null;
		doc = Jsoup.parse(pageBorrow);
		Elements table = doc.getElementsByClass("table_line");
		Elements trs = table.select("tr");
		for (int i = 1; i < trs.size(); i++) {
			Element tr = trs.get(i);
			Elements tds = tr.select("td");
//			for (int j = 0; j < tds.size(); j++) {
//				String text = tds.get(j).text();
//				System.out.print(text + "\t");
//			}
			Book temp=new Book();
			temp.setId(tds.get(0).text());
			temp.setName(tds.get(1).text());
			temp.setBorrowTime(tds.get(2).text());
			temp.setPayTime(tds.get(3).text());
			temp.setXuJieNum(Integer.parseInt(tds.get(4).text()));
			temp.setBookPlace(tds.get(5).text());
			temp.setMark(tds.get(6).text());
//			System.out.println(temp);
			lists.add(temp);
		}
		return lists;
	}
	public Cookie getCookie() {
		BufferedReader bfr;
		Cookie cookie = null;
		try {
			bfr = new BufferedReader(new InputStreamReader(new FileInputStream(cookiefile)));
			String name = bfr.readLine();
			String value = bfr.readLine();
			cookie = new BasicClientCookie(name, value);
			bfr.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cookie;
	}
	public void storeCookie(Cookie cookie){
		String name = cookie.getName();
		String value = cookie.getValue();
		if (!cookiefile.exists()) {
			try {
				cookiefile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		try {
			BufferedWriter bfr = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(cookiefile)));
			bfr.write(name);
			bfr.newLine();
			bfr.write(value);
			bfr.flush();
			bfr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws Exception {
		// //
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				HttpClient client = new HttpClient();
				//Cookie cookie = client.getCookie();
				client.login();
				System.out.println(client.getProfile());
				ArrayList<Book> booklist=client.getBorrowBooks();
				for (int i = 0; i < booklist.size(); i++) {
					System.out.println(booklist.get(i).toString());
				}
			}
		};
		new Timer(false).schedule(task, 0, 21600000);

	}

}
