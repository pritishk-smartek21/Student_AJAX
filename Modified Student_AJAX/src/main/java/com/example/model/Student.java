package com.example.model;

import java.sql.Date;

public class Student {
	private int age;
	private int rollNo;
	private String name;
	private String address;
	private Date dob;
	private String gender;
	private String course;
	private long mobileNo;
	
	
	public Student(int age, int rollNo, String name, String address, Date dob, String gender,
			String course, long mobileNo) {
		super();
		this.age = age;
		this.rollNo = rollNo;
		this.name = name;
		this.address = address;
		this.dob = dob;
		this.gender = gender;
		this.course = course;
		this.mobileNo = mobileNo;
	}


	public Student() {
	}


	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}


	public int getRollNo() {
		return rollNo;
	}


	public void setRollNo(int rollNo) {
		this.rollNo = rollNo;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public String getCourse() {
		return course;
	}


	public void setCourse(String course) {
		this.course = course;
	}


	public long getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(long mobileNo) {
		this.mobileNo = mobileNo;
	}


	@Override
	public String toString() {
		return "Student [age=" + age + ", rollNo=" + rollNo + ", name=" + name + ", address="
				+ address + ", dob=" + dob + ", gender=" + gender + ", course=" + course + ", mobileNo=" + mobileNo
				+ "]";
	}
	
	
	
}
