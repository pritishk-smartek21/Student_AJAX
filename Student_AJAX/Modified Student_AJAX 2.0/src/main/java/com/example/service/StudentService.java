package com.example.service;

import java.util.List;

import com.example.custom_exception.StudentNotFoundException;
import com.example.model.Student;

public interface StudentService {

	Student updateStudentById(Student stud) throws StudentNotFoundException;

	String deleteStudentById(int rollNo) throws StudentNotFoundException;

	Student getStudentById(int rollNo) throws StudentNotFoundException;
	
	List<Student> getAllStudents();

	String registerStudent(Student student) ;
}
