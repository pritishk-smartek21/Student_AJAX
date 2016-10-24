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
public class DeleteStudentTest {

	@LocalServerPort
	private int port;
	private URI baseURL;
	private RestTemplate restTemplate;
	StudentDao stud;
	Student stu;

	@Before
	public void setUp() throws Exception{
		stud = new StudentDaoImpl();
		stu = new Student();
		stu = stud.getStudentById(12);
		restTemplate = new RestTemplate();
		this.baseURL = new URI("http://localhost:" + port + "/stud" + "/12");
		System.out.println("done setup");
		
	}

	@Test
	public void delete_Student_Success_Test(){
		ResponseEntity<Student> response = restTemplate.getForEntity(baseURL, Student.class);
		Student stu = response.getBody();
		assertEquals(12, stu.getRollNo());
		restTemplate.delete(baseURL);
		System.out.println("in delete student by id");
	}
	@After
	public void tearDown(){
		restTemplate = null;
		stu = null;
		System.out.println("in tear down");
	}

}
