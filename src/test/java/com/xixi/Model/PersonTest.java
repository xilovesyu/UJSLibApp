package com.xixi.Model;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Iterator;

import org.junit.Test;

import com.xixi.utils.PropertiesUtils;

public class PersonTest {

	@Test
	public void test() {
		Person p=new Person();
		p.setStuNum("2211508055");
		p.setPasswd(PropertiesUtils.getPro("mylibCode"));
		
//		p.login();
		ArrayList<Book> books=p.getBorrowBooks();
		for (Iterator iterator = books.iterator(); iterator.hasNext();) {
			Book book = (Book) iterator.next();
			System.out.println(book);
		}
	}

}
