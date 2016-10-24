package com.example;

import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.sql.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RegisterStudentTest {

	@LocalServerPort
	private int port;
	private URI baseURL;
	private RestTemplate restTemplate;
	Student stud;
	
	@Before
	public void setUp() throws Exception{
		System.out.println("inside setup");
		stud = new Student();
		stud.setAddress("Pune");
		stud.setAge(23);
		stud.setRollNo(12);
		stud.setCourse("DAC");
		stud.setGender("F");
		stud.setMobileNo("90090909");
		stud.setName("Aparna");
		stud.setDob(new Date(1992-5-10));
		restTemplate = new RestTemplate();
		this.baseURL = new URI("http://localhost:"+port+"/stud");
		
	}

	@Test
	public void test_Student_registration() throws Exception{
		System.out.println("inside test student registration");
		ResponseEntity<Student> response = restTemplate.postForEntity(baseURL, stud, Student.class);
		Student testStudent = response.getBody();
		assertEquals(stud.getAge(), testStudent.getAge());
		assertEquals(stud.getRollNo(), testStudent.getRollNo());
		assertEquals(stud.getMobileNo(), testStudent.getMobileNo());
		assertEquals(stud.getAddress(), testStudent.getAddress());
		assertEquals(stud.getGender(), testStudent.getGender());
		assertEquals(stud.getName(), testStudent.getName());
		assertEquals(stud.getDob().toString(),testStudent.getDob().toString());
		assertEquals(stud.getCourse(), testStudent.getCourse());
	}
	
	@After
	public void tearDown(){
		System.out.println("inside tear down");
		restTemplate = null;
		stud = null;
	}
}
