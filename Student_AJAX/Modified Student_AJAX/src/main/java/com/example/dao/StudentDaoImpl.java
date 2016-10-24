package com.example.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.model.Student;

@Component
public class StudentDaoImpl implements StudentDao{

	@Autowired
	DataSource dataSource;

	private String insert="insert into student(rollNo,age,name,address,dob,gender,course,mobileNo) values(?,?,?,?,?,?,?,?)";
	private String delete="delete from student where rollNo=?";
	private String show="select age,rollNo,name,address,dob,gender,course,mobileNo from student where rollNo=?";
	private String showAll = "select age,rollNo,name,address,dob,gender,course,mobileNo from student order by rollNo";
	private String update = "update student set age=?,name=?,address=?,dob=?,gender=?,course=?,mobileNo=? where rollNo=?";


	private Connection connection;
	private PreparedStatement pst1,pst2,pst3,pst4;
	private Statement statement;
	private ResultSet rst;

	@Override
	public String storeStudent(Student student) throws SQLException{

		String result;
		connection = dataSource.getConnection();
		System.out.println("the connection: "+connection);
		pst1 = connection.prepareStatement(insert);
		pst1.setInt(1, student.getRollNo());
		pst1.setInt(2, student.getAge());
		pst1.setString(3, student.getName());
		pst1.setString(4, student.getAddress());
		pst1.setDate(5, student.getDob());
		pst1.setString(6, student.getGender());
		pst1.setString(7, student.getCourse());
		pst1.setLong(8, student.getMobileNo());
		int i = pst1.executeUpdate();
		if(i == 1){
			result = "success";
			System.out.println("successfully inserted "+i+" record");
		}else{
			result = null;
		}	
		return result;
	}

	@Override
	public List<Student> getStudents() throws SQLException {
		List<Student> list = new ArrayList<>();
		try{
			connection = dataSource.getConnection();
			System.out.println("the connection: "+connection);
			statement = connection.createStatement();
			rst = statement.executeQuery(showAll);
			while(rst.next()){
				list.add(new Student(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getString(4), rst.getDate(5), rst.getString(6), rst.getString(7), rst.getLong(8)));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("in finally of getStudents");
			if(statement!=null){
				statement.close();
				System.out.println("completed finally of getStudents");
			}
		}
		return list	;
	}

	@Override
	public Student getStudentById(int rollNo) throws SQLException{
		connection = dataSource.getConnection();
		Student stud=null;
		try{
			pst4 = connection.prepareStatement(show);
			pst4.setInt(1, rollNo);
			rst = pst4.executeQuery();
			while(rst.next()){
				stud=new Student(rst.getInt(1), rollNo, rst.getString(3), rst.getString(4), rst.getDate(5), rst.getString(6), rst.getString(7), rst.getLong(8));
			}
			return stud;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pst4!=null)
				pst4.close();
		}
		return null;
	}


	@Override
	public String deleteStudentById(int rollNo) throws SQLException{
		connection = dataSource.getConnection();
		System.out.println("the connection: "+connection);
		int i = 0 ;
		String result;
		pst3 = connection.prepareStatement(delete);
		pst3.setInt(1, rollNo);
		System.out.println("in deleteStudentByID");
		i = pst3.executeUpdate();
		System.out.println("after deleteStudentByID");
		if(i < 1){
			result = null;
		}else
		{
			result= "Student ID: "+rollNo +" successfully deleted from database";
		}
		System.out.println(result +" returning");
		return result;
	}

	@Override
	public Student updateStudentById(Student stud) throws SQLException {
		connection = dataSource.getConnection();
		try{
			System.out.println("inside update " );
			pst2 = connection.prepareStatement(update);
			pst2.setInt(1, stud.getAge());
			pst2.setString(2, stud.getName());
			pst2.setString(3, stud.getAddress());
			pst2.setDate(4, stud.getDob());
			pst2.setString(5, stud.getGender());
			pst2.setString(6, stud.getCourse());
			pst2.setLong(7, stud.getMobileNo());
			pst2.setInt(8, stud.getRollNo());
			int i = pst2.executeUpdate();
			System.out.println(i+ "student update successfully ");

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(pst2!=null)
				pst2.close();
		}
		return stud;
	}

}
