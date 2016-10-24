package com.example.service;

import java.sql.SQLException;
import java.util.List;

import com.example.model.Student;

public interface StudentService {

	Student updateStudentById(Student stud) throws SQLException;

	String deleteStudentById(int rollNo) throws SQLException;

	Student getStudentById(int rollNo) throws SQLException;

	List<Student> getStudents() throws SQLException;

	String storeStudent(Student student) throws SQLException;

}
