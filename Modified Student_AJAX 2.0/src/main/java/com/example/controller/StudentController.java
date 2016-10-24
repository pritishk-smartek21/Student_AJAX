package com.example.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.custom_exception.StudentNotFoundException;
import com.example.model.Student;
import com.example.service.StudentService;

@RestController
public class StudentController {

	private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService service;



	@RequestMapping(value="/stud",method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public String registerStudent(@Valid @RequestBody Student student){
		logger.info("entered registerStudent controller");
		return service.registerStudent(student);
	}

	@RequestMapping(value="/stud/{rollNo}",method= RequestMethod.GET, produces="application/json")
	public Student getStudentById(@PathVariable int rollNo) throws StudentNotFoundException{
		logger.info("entered getStudentById controller");
		return service.getStudentById(rollNo);
	}

	@RequestMapping(value="/stud/{rollNo}",method = RequestMethod.DELETE, produces="application/JSON")
	public String deleteStudentById(@PathVariable int rollNo) throws StudentNotFoundException{
		logger.info("entered deleteStudentById controller");
		return service.deleteStudentById(rollNo);
	}



	@RequestMapping(value="/stud",method=RequestMethod.GET,produces="application/json")
	public List<Student> getAllStudents(){
		logger.info("entered getAllStudent controller");
		return service.getAllStudents();
	}


	@RequestMapping(value="/stud/{rollNo}",method=RequestMethod.PUT, produces="application/json",consumes="application/json")
	public Student updateStudentById(@RequestBody Student student) throws StudentNotFoundException{
		logger.info("entered updateStudentById controller");
		return service.updateStudentById(student);
	}
}
