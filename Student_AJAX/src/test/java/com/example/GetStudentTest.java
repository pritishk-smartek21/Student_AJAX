package com.example;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.model.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetStudentTest {
	
	@LocalServerPort
	private int port;
	private URL baseURL;
	RestTemplate restTemplate;
	

	@Before
	public void setUp() throws MalformedURLException {
		System.out.println("done setup");
		restTemplate = new RestTemplate();
		this.baseURL = new URL("http://localhost:" + port + "/stud" + "/2");
	}

	@Test
	public void test_Get_Student_ByID_Success() throws Exception {
		ResponseEntity<Student> response = restTemplate.getForEntity(baseURL.toURI(), Student.class);
		Student employee = response.getBody();
		System.out.println("in get student by id");
		assertEquals(2, employee.getRollNo());
		assertEquals(10, employee.getAge());
		assertEquals("shyam", employee.getName());
		assertEquals("Pune", employee.getAddress());
		assertEquals("M", employee.getGender());
		assertEquals("DC", employee.getCourse());
		assertEquals("2016-01-12", employee.getDob().toString());
		assertEquals(9996, employee.getMobileNo());
	}

	@Test
	public void test_Get_All_Student_Success() throws MalformedURLException, RestClientException, URISyntaxException {
		this.baseURL = new URL("http://localhost:" + port + "/stud");
		
		ResponseEntity<Student[]> response = restTemplate.getForEntity(baseURL.toURI(), Student[].class);
		System.out.println("in get all students");
		Student[] empList = response.getBody();
		for (Student emp : empList) {
			System.out.println(emp.getName() +" "+ emp.getRollNo());
		}

	}


	@After
	public void tearDown() {
		System.out.println("after test");
		restTemplate = null;

	}
}
