package com.example.controller;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.Student;
import com.example.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService service;


	@RequestMapping(value="/stud", method = RequestMethod.POST, produces="application/JSON", consumes="application/JSON")
	public Student storeStudent(@RequestBody Student stud,Model map){
		System.out.println("student name: "+stud.getName()+": student rollNo: "+stud.getRollNo());
		try {
			service.storeStudent(stud);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return stud;
	}

	@RequestMapping(value="/stud/{rollNo}",method = RequestMethod.GET, produces="application/JSON")
	public Student getStudentById(@PathVariable int rollNo) throws SQLException{
		Student stud = service.getStudentById(rollNo);
		System.out.println("student name: "+stud.getName()+": student rollNo: "+stud.getRollNo());
		return stud;
	}

	@RequestMapping(value="/stud/{rollNo}",method = RequestMethod.DELETE, produces="application/JSON")
	public String deleteStudent(@PathVariable int rollNo) throws SQLException{
		System.out.println("successfully deleted from student controller");
		return service.deleteStudentById(rollNo);
	}
	@RequestMapping(value="/stud", method = RequestMethod.GET, produces="application/JSON")
	public List<Student> getStudents() throws SQLException{
		System.out.println("list of all students");
		return service.getStudents();
	}

	@RequestMapping(value="/stud/{rollNo}", method = RequestMethod.PUT, produces="application/JSON", consumes="application/JSON")
	public Student updateStudent( @RequestBody Student stud) throws SQLException{
		System.out.println("inside upate method of controller");
		return service.updateStudentById(stud);
	}

}
