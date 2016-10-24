package com.example.model;

import java.sql.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

public class Student {
	@Min(value = 3,message="The minimum value allowed for 'AGE' is 3") 
	@Max(value = 101, message = "The maximum value allowed for 'AGE' field value is 101")
	@NotNull(message="The 'AGE' field should not be null")
	private int age;

	@NotNull @Min(0)
	private int rollNo;

	@NotNull(message="The 'NAME' should not be Null")
	@Length(min= 2,max=20,message="Please enter correct 'NAME', maximum = 20 words")
	@Pattern(regexp = "[a-z-A-Z]*",message="The Name should contain only alphabets")
	private String name;

	@Length(max=20,message="Maximum length of 'ADDRESS' should be 20 words")
	@NotNull(message="The 'ADDRESS' field should not be null")
	@NotEmpty(message= "The 'ADDRESS' field should not be empty")
	private String address;

	@Past(message="Doing advanced booking?? Please enter correct 'DOB'!")
	private Date dob;

	@Size(min=1,max=1,message="'GENDER' value should be either M, F or O")
	@Pattern(regexp="(M|F|O|m|f|o)")
	private String gender;

	@Length(max=20,message="Maximum length of 'COURSE' allowed is 20 words")
	private String course;

	@Size(min=10,max=10,message="'MOBILE NUMBER' should be 10 digit")
	@Pattern(regexp="(^$||[0-9]{10})")
	private String mobileNo;


	public Student(int age, int rollNo, String name, String address, Date dob, String gender,
			String course, String mobileNo) {
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


	public String getMobileNo() {
		return mobileNo;
	}


	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	@Override
	public String toString() {
		return "Student [age=" + age + ", rollNo=" + rollNo + ", name=" + name + ", address="
				+ address + ", dob=" + dob + ", gender=" + gender + ", course=" + course + ", mobileNo=" + mobileNo
				+ "]";
	}
}
