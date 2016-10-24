package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.custom_exception.GlobalExceptionHandler;
import com.example.custom_exception.StudentNotFoundException;
import com.example.model.Student;

@Component
public class StudentDaoImpl implements StudentDao {

	private static final Logger logger = Logger.getLogger(GlobalExceptionHandler.class);
	@Autowired
	DataSource dataSource;

	@Override
	public Student getStudentById(int rollNo) throws StudentNotFoundException {
		Student student = null;
		try (Connection conn = dataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(
						"select age,rollNo,name,address,dob,gender,course,mobileNo from student where rollNo=?");) {

			preparedStatement.setInt(1, rollNo);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (!resultSet.next()) {
				throw new StudentNotFoundException("No student with ID: " + rollNo + " in the database");
			} else {
				student = new Student(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8));
				return student;
			}
		} catch (Exception e) {
			logger.error("No Student with rollNo:" + rollNo + " available in databsae.", e);

		}
		return student;
	}

	public String registerStudent(Student student) {
		String result = null;
		Student studentAvailable;

		try {
			studentAvailable = getStudentById(student.getRollNo());

			if (studentAvailable != null) {
				result = "Student with ID:" + student.getRollNo() + " already available";

			} else {
				try (Connection conn = dataSource.getConnection();
						PreparedStatement preparedStatement = conn.prepareStatement(
								"insert into student(rollNo,age,name,address,dob,gender,course,mobileNo) values(?,?,?,?,?,?,?,?)");) {
					preparedStatement.setInt(1, student.getRollNo());
					preparedStatement.setInt(2, student.getAge());
					preparedStatement.setString(3, student.getName());
					preparedStatement.setString(4, student.getAddress());
					preparedStatement.setDate(5, student.getDob());
					preparedStatement.setString(6, student.getGender());
					preparedStatement.setString(7, student.getCourse());
					preparedStatement.setString(8, student.getMobileNo());
					int i = preparedStatement.executeUpdate();
					if (i != 1) {
						return result;
					} else {
						result = "Student Successfully registered with ID:" + student.getRollNo();
						logger.info("successfully registered " + i + " student");
					}
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
				return result;
			}
		} catch (StudentNotFoundException e1) {
			logger.error(e1.getMessage(), e1);
		}
		return result;
	}

	@Override
	public List<Student> getAllStudents() {
		Student student;
		List<Student> allStudents = new ArrayList<>();
		try (Connection conn = dataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(
						"select age,rollNo,name,address,dob,gender,course,mobileNo from student order by rollNo");
				ResultSet resultSet = preparedStatement.executeQuery()) {
			logger.info("getting all students via " + conn);
			while (resultSet.next()) {
				student = new Student(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3),
						resultSet.getString(4), resultSet.getDate(5), resultSet.getString(6), resultSet.getString(7),
						resultSet.getString(8));
				allStudents.add(student);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return allStudents;
	}

	@Override
	public String deleteStudentById(int rollNo) throws StudentNotFoundException {
		String delete = "Student ID: " + rollNo + " not available.";
		try (Connection conn = dataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement("delete from student where rollNo=?");) {
			preparedStatement.setInt(1, rollNo);
			int count = preparedStatement.executeUpdate();
			if (count < 1) {
				throw new StudentNotFoundException("No student with ID: " + rollNo + " in the database");
			} else {
				delete = "Student ID: " + rollNo + ", successfully deleted";
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return delete;
	}

	public Student updateStudentById(Student student) throws StudentNotFoundException {
		try (Connection conn = dataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(
						"update student set age=?,name=?,address=?,dob=?,gender=?,course=?,mobileNo=? where rollNo=?");) {
			logger.info("updating student by id via " + conn);
			preparedStatement.setInt(1, student.getAge());
			preparedStatement.setString(2, student.getName());
			preparedStatement.setString(3, student.getAddress());
			preparedStatement.setDate(4, student.getDob());
			preparedStatement.setString(5, student.getGender());
			preparedStatement.setString(6, student.getCourse());
			preparedStatement.setString(7, student.getMobileNo());
			preparedStatement.setInt(8, student.getRollNo());
			int i = preparedStatement.executeUpdate();
			if (i < 1) {
				throw new StudentNotFoundException("Error while Updating database");
			} else {
				return student;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return student;
	}
}
