package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.custom_exception.StudentNotFoundException;
import com.example.dao.StudentDao;
import com.example.model.Student;

@Service
public class StudentServiceImpl implements StudentService{

	@Autowired
	private StudentDao dao;

	@Override
	public String registerStudent(Student student){
		return dao.registerStudent(student);
	}

	@Override
	public List<Student> getAllStudents(){
		return dao.getAllStudents();
	}

	@Override
	public Student getStudentById(int rollNo) throws StudentNotFoundException{
		return dao.getStudentById(rollNo);
	}


	@Override
	public String deleteStudentById(int rollNo) throws StudentNotFoundException{
		return dao.deleteStudentById(rollNo);
	}

	@Override
	public Student updateStudentById(Student stud) throws StudentNotFoundException {
		return dao.updateStudentById(stud);

	}
}






































