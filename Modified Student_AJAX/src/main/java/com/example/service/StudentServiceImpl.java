package com.example.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.StudentDao;
import com.example.model.Student;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao dao;

	@Override
	public String storeStudent(Student student) throws SQLException{
		return dao.storeStudent(student);
	}

	@Override
	public List<Student> getStudents() throws SQLException {
		return dao.getStudents();
	}

	@Override
	public Student getStudentById(int rollNo) throws SQLException{
		return dao.getStudentById(rollNo);
	}


	@Override
	public String deleteStudentById(int rollNo) throws SQLException{
		return dao.deleteStudentById(rollNo);
	}

	@Override
	public Student updateStudentById(Student stud) throws SQLException {
		return dao.updateStudentById(stud);

	}
}






































