package com.example.dao;

import java.util.List;

import com.example.custom_exception.StudentNotFoundException;
import com.example.model.Student;

public interface StudentDao {

	String registerStudent(Student student);

	Student updateStudentById(Student stud) throws StudentNotFoundException;

	String deleteStudentById(int rollNo) throws StudentNotFoundException;

	Student getStudentById(int rollNo) throws StudentNotFoundException ;

	List<Student> getAllStudents() ;
}
