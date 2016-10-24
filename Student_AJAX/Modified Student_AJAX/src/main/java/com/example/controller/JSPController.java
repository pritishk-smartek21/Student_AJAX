package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class JSPController {

	//	@Autowired
	//	private StudentService service;

	@RequestMapping("/")
	public ModelAndView index(){
		System.out.println("indside index");
		return new ModelAndView("index");
	}


	/*	@RequestMapping("downloadNextPage")
	public ModelAndView indexD(){
		System.out.println("indside downloadNextPage");
		return new ModelAndView("downloadIndex");
	}*/
	/*
	@RequestMapping("showInsert")
	public ModelAndView showInsert(){
		System.out.println("inside insert");
		return new ModelAndView("studentRegistration");
	}

	@RequestMapping("showSingle")
	public ModelAndView showSingleStudent(){
		System.out.println("inside show Single by ID");
		return new ModelAndView("showStudentById");
	}

	@RequestMapping("delete")
	public ModelAndView delete(){
		System.out.println("indside delete jsp controller");
		return new ModelAndView("deleteStud");
	}*/
}
/*	@RequestMapping(value="processInsert", method = RequestMethod.POST,/* produces="application/JSON", consumes="application/JSON")
	@RequestMapping(value="processInsert",method={RequestMethod.POST})
	public ModelAndView storeStudent(Student stud,Model map){
		System.out.println("indide processInsert");
		System.out.println("stud name: "+stud.getName()+": stud rollNo: "+stud.getRollNo());
		try {
			service.storeStudent(stud);
			map.addAttribute("msg", stud.getName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return new ModelAndView("success");
	}


	@RequestMapping(value="/api/stud", method = RequestMethod.GET, produces="application/JSON")
	public List<Student> getStudents() throws SQLException{
		return service.getStudents();
	}

	@RequestMapping(value="/api/stud/{rollNo}", method = RequestMethod.PUT, produces="application/JSON", consumes="application/JSON")
	public Student updateStudent(@PathVariable int rollNo, @RequestBody Student stud) throws SQLException{
		return service.updateStudentById(rollNo,stud);
	}

	@RequestMapping(value="/api/stud/{rollNo}",method = RequestMethod.DELETE)
	public String deleteStudent(@PathVariable int rollNo) throws SQLException{
		return service.deleteStudentById(rollNo);
	}

	@RequestMapping(value="/api/stud/{rollNo}",method = RequestMethod.GET, produces="application/JSON")
	public Student getStudentById(@PathVariable int rollNo) throws SQLException{
		return service.getStudentById(rollNo);
	}
}
 */