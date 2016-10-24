package com.example.custom_exception;

public class StudentNotFoundException extends Exception{

	public StudentNotFoundException(String message){
		super(message);
	}

}
