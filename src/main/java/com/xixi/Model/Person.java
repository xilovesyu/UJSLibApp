package com.xixi.Model;

public class Person {
	String name;
	String stuNum;
	String totalJiFen;
	String availableJiFen;
	String idNum;
	String address;
	String telephone;
	String Email;
	String sex;
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
		return "Person [name=" + name + ",Sex="+sex+", stuNum=" + stuNum + ", totalJiFen=" + totalJiFen + ", availableJiFen=" + availableJiFen + ", idNum=" + idNum + ", address=" + address + ", telephone="
				+ telephone + ",Email="+Email+"]";
	}
	
}
