package com.xixi.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.message.BasicNameValuePair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.xixi.Recog.MyParser;
import com.xixi.utils.HttpUtils;

public class Person {
	private String name;
	private String stuNum;
	private String passwd;


	private String totalJiFen;
	private String availableJiFen;
	private String idNum;
	private String address;
	private String telephone;
	private String Email;
	private String sex;
	HttpUtils utils=new HttpUtils();
	public Person() {
	}

	public Person( String stuNum, String passwd) {
		this.stuNum = stuNum;
		this.passwd = passwd;
	}

	
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStuNum() {
		return stuNum;
	}

	public void setStuNum(String stuNum) {
		this.stuNum = stuNum;
	}

	public String getTotalJiFen() {
		return totalJiFen;
	}

	public void setTotalJiFen(String totalJiFen) {
		this.totalJiFen = totalJiFen;
	}

	public String getAvailableJiFen() {
		return availableJiFen;
	}

	public void setAvailableJiFen(String availableJiFen) {
		this.availableJiFen = availableJiFen;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ",Sex="+sex+", stuNum=" + stuNum +",Password="+passwd+ ", totalJiFen=" + totalJiFen + ", availableJiFen=" + availableJiFen + ", idNum=" + idNum + ", address=" + address + ", telephone="
				+ telephone + ",Email="+Email+"]";
	}
	
	private File getYZM() {
		InputStream yzm = utils.Get_InputStream(Library.getYanzhengPage());
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
		return yzmFile;
	}

	/**
	 * @return 是否登录成功
	 *  @author xijiaxiang
	 */
	public boolean login() {
		File yzm = getYZM();
		String yzmbak = MyParser.recogYZM(yzm);
		List<NameValuePair> lists = new ArrayList<NameValuePair>();
		lists.add(new BasicNameValuePair("number", this.getStuNum()));
		lists.add(new BasicNameValuePair("passwd", this.getPasswd()));
		lists.add(new BasicNameValuePair("captcha", yzmbak));
		lists.add(new BasicNameValuePair("select", "cert_no"));
		lists.add(new BasicNameValuePair("returnUrl", ""));
		// 验证，后面做cookie持久化
		InputStream response = utils.Post_InputStream(Library.getReaderVertifyPage(), lists);
		String text = null;
		try {
			text = IOUtils.toString(response);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (text.contains("对不起，密码错误，请查实！") || text.contains("验证码错误(wrong check code)")) {
			System.out.println("登录失败");
			return false;
		} else {
			// store cookie
			// Cookie cookie = cookieStore.getCookies().get(0);
			// storeCookie(cookie);
		}
		return true;
	}
	public boolean isLogin(){
		boolean flag=true;
		CloseableHttpResponse borrowResponse = utils.Get(Library.getBookListPage());
		CloseableHttpResponse loginResponse = utils.Get(Library.getLoginPage());
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
		
	public ArrayList<Book> getBorrowBooks(){
		ArrayList<Book> lists=new ArrayList<Book>();
		String pageBorrow = null;
		if(!isLogin()){login();}
		CloseableHttpResponse borrowResponse = utils.Get(Library.getBookListPage());
		borrowResponse =utils.Get(Library.getBookListPage());
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
	
	public Person getProfile(){
		CloseableHttpResponse indexResponse = utils.Get(Library.getReaderInfoPage());
		CloseableHttpResponse userDetailResponse =utils.Get(Library.getUserDetailPage());
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
		
		setName(indexdoc.getElementsByClass("profile-name").get(0).text());
		setTotalJiFen(indexdoc.getElementsByClass("bigger-170").get(3).text());
		setAvailableJiFen(indexdoc.getElementsByClass("bigger-170").get(4).text());
		//setStuNum(tds.get(2).ownText().trim());
		setEmail(tds.get(16).ownText().trim());
		setIdNum(tds.get(17).ownText().trim());
		setSex(tds.get(21).ownText().trim());
		setAddress(tds.get(22).ownText().trim());
		setTelephone(tds.get(25).ownText().trim());
		return this;
	}
}
