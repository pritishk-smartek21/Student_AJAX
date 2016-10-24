package com.example;

import static org.junit.Assert.assertEquals;

import java.net.URI;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.example.dao.StudentDao;
import com.example.dao.StudentDaoImpl;
import com.example.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdateStudentTest {

	@LocalServerPort
	private int port;
	private URI baseURL;
	private RestTemplate restTemplate;
	private StudentDao stud;
	private Student st;

	@Before
	public void StartUp() throws Exception{
		System.out.println("inside startup");
		st = new Student();
		stud = new StudentDaoImpl();
		st = stud.getStudentById(12);
		st.setAddress("Kerala");
		restTemplate = new RestTemplate();
		this.baseURL = new URI("http://localhost:"+port+"/stud/12");
	}

	@After
	public void tearDown(){
		System.out.println("inside tearDown");
		restTemplate = null;
		st = null;
	}

	@Test
	public void Update_student_success_test() throws Exception{
		System.out.println("inside update student success test");
		restTemplate.put(baseURL,st);
		ResponseEntity<Student> response = restTemplate.getForEntity(baseURL, Student.class);
		Student student = response.getBody();
		assertEquals(st.getAddress(), student.getAddress());
	}
}
