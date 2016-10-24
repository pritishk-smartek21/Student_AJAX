package com.example.controller;

import java.util.ArrayList;
import java.util.List;

import javax.xml.ws.WebServiceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

	/*	@RequestMapping(value="/stud", method = RequestMethod.POST, produces="application/JSON", consumes="application/JSON")
	public Student storeStudent(@RequestBody Student stud,Model map){
		System.out.println("student name: "+stud.getName()+": student rollNo: "+stud.getRollNo());
		try {
			service.storeStudent(stud);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return stud;
	}*/
	@RequestMapping(value="/stud",method=RequestMethod.POST, produces="application/json",consumes="application/json")
	public ResponseEntity<String> storeStudent(@RequestBody Student stud){
		System.out.println("inside store student");
		try{
			String result = service.storeStudent(stud);
			System.out.println("store student method output: "+result);
			if(result.equals("success")){
				return new ResponseEntity<String>("Student data successfully inserted", HttpStatus.OK);
			}else{
				return new ResponseEntity<String>("Error while inserting data", HttpStatus.ACCEPTED);
			}
		}catch(Exception e){
			throw new WebServiceException("Unable to register, wrong or unique rollNo error", e);
		}
	}



	/*	@RequestMapping(value="/stud/{rollNo}",method = RequestMethod.GET, produces="application/JSON")
	public Student getStudentById(@PathVariable int rollNo) throws SQLException{
		Student stud = service.getStudentById(rollNo);
		System.out.println("student name: "+stud.getName()+": student rollNo: "+stud.getRollNo());
		if(stud.equals(null)){
			return null;
		}
		return stud;
	}*/
	@RequestMapping(value="/stud/{rollNo}",method= RequestMethod.GET, produces="application/json")
	public ResponseEntity<?> getStudentById(@PathVariable int rollNo){
		System.out.println("inside get students by id");
		try{
			Student stud = service.getStudentById(rollNo);
			if(null == stud){
				return new ResponseEntity<String>("Wrong ID", HttpStatus.ACCEPTED);
			}else{
				return new ResponseEntity<Student>(stud, HttpStatus.OK);
			}
		}catch(Exception e){
			throw new WebServiceException("Student ID not available", e);
		}
	}

	/*@RequestMapping(value="/stud/{rollNo}",method = RequestMethod.DELETE, produces="application/JSON")
	public String deleteStudent(@PathVariable int rollNo) throws SQLException{
		System.out.println("delete from student controller");
		String result = service.deleteStudentById(rollNo);
		if(result == null){
			result = "No such Student ID available!";
			System.out.println(result+" inside delete controller");
			return result;
		}else{
			System.out.println(result+" inside else delete controller");
			return result;
		}
	}*/

	@RequestMapping(value="/stud/{rollNo}",method = RequestMethod.DELETE, produces="application/JSON")
	public ResponseEntity<String> deleteStudent(@PathVariable int rollNo) {
		System.out.println("delete from student controller");
		try{
			String result = service.deleteStudentById(rollNo);
			if(result != null){
				return new ResponseEntity<String>("Student ID:"+rollNo+" deleted successfully", HttpStatus.OK);
			}
			else{

				return new ResponseEntity<String>("Student ID not found Response Entity", HttpStatus.ACCEPTED);
			}

		}catch(Exception e){
			throw new WebServiceException("Student ID not available Exception", e);
		}
	}


	/*@RequestMapping(value="/stud", method = RequestMethod.GET, produces="application/JSON")
	public List<Student> getStudents() throws SQLException{
		System.out.println("list of all students");
		return service.getStudents();
	}*/
	@RequestMapping(value="/stud",method=RequestMethod.GET,produces="application/json")
	public ResponseEntity<?> getStudents(){
		System.out.println("inside get all students");
		try{
			List<Student> list = new ArrayList<>();
			list = service.getStudents();
			if(list.size() < 1){
				return new ResponseEntity<String>("No students registered", HttpStatus.ACCEPTED);
			}else{
				return new ResponseEntity<>(list, HttpStatus.OK);
			}
		}catch(Exception e){
			throw new WebServiceException("No Students registered yet", e);
		}
	}

	/*	@RequestMapping(value="/stud/{rollNo}", method = RequestMethod.PUT, produces="application/JSON", consumes="application/JSON")
	public Student updateStudent( @RequestBody Student stud) throws SQLException{
		System.out.println("inside update method of controller");
		return service.updateStudentById(stud);
	}*/
	
	@RequestMapping(value="/stud/{rollNo}",method=RequestMethod.PUT, produces="application/json",consumes="application/json")
	public ResponseEntity<?> updateStudent(@RequestBody Student stud){
		System.out.println("inside update");
		try{
			Student checkStudent = service.getStudentById(stud.getRollNo());
			if(null != checkStudent){
				Student st = service.updateStudentById(stud);
				return new ResponseEntity<Student>(st, HttpStatus.OK);
			}else{
				return new ResponseEntity<String>("Update Failed", HttpStatus.ACCEPTED);
			}
		}catch(Exception e){
			throw new WebServiceException("Unable to update", e);
		}
	}

}
