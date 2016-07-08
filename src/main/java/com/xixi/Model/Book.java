package com.xixi.Model;

import java.io.Serializable;

public class Book implements Serializable {
	
	private static final long serialVersionUID = 2642383188330728658L;
	String id;
	String name;
	String borrowTime;
	String payTime;
	int XuJieNum;
	String bookPlace;
	String mark;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getPayTime() {
		return payTime;
	}

	public void setPayTime(String payTime) {
		this.payTime = payTime;
	}

	public int getXuJieNum() {
		return XuJieNum;
	}

	public void setXuJieNum(int xuJieNum) {
		XuJieNum = xuJieNum;
	}

	public String getBookPlace() {
		return bookPlace;
	}

	public void setBookPlace(String bookPlace) {
		this.bookPlace = bookPlace;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", borrowTime=" + borrowTime + ", payTime=" + payTime + ", XuJieNum=" + XuJieNum + ", bookPlace=" + bookPlace + ", mark=" + mark + "]";
	}
	
}
