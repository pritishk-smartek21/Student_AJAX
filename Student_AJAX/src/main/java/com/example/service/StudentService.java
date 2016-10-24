package com.example.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.model.Student;
import com.example.uitls.JDBCUtility;

@Component
public class StudentService{

	private String insert="insert into student(rollNo,age,name,address,dob,gender,course,mobileNo) values(?,?,?,?,?,?,?,?)";
	private String delete="delete from student where rollNo=?";
	private String show="select age,rollNo,name,address,dob,gender,course,mobileNo from student where rollNo=?";
	private String showAll = "select age,rollNo,name,address,dob,gender,course,mobileNo from student";
	private String update = "update student set age=?,name=?,address=?,dob=?,gender=?,course=?,mobileNo=? where rollNo=?";
	
	
	private Connection con;
	private PreparedStatement pst1,pst2,pst3,pst4;
	private Statement st;
	private ResultSet rst;
	
	public void storeStudent(Student student) throws SQLException{
//		SimpleDateFormat format=null;
		try{
//			format = new SimpleDateFormat("dd-mm-yy");
//			Date dob = format.parse(student.getDob());
//			
//			java.sql.Date sqlDob = new java.sql.Date(dob.getTime());
//			
			con = JDBCUtility.getConnection();
			System.out.println("the connection: "+con);
			pst1 = con.prepareStatement(insert);
			pst1.setInt(1, student.getRollNo());
			pst1.setInt(2, student.getAge());
			pst1.setString(3, student.getName());
			pst1.setString(4, student.getAddress());
			pst1.setDate(5, student.getDob());
			pst1.setString(6, student.getGender());
			pst1.setString(7, student.getCourse());
			pst1.setLong(8, student.getMobileNo());
			int i = pst1.executeUpdate();
			System.out.println("successfully inserted "+i+" record");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			if(pst1!=null){
				pst1.close();
				JDBCUtility.close();
			}
		}
	}
	
	
	public List<Student> getStudents() throws SQLException {
		List<Student> list = new ArrayList<>();
		try{
		con = JDBCUtility.getConnection();
		System.out.println("the connection: "+con);
		st = con.createStatement();
		rst = st.executeQuery(showAll);
		while(rst.next()){
			list.add(new Student(rst.getInt(1), rst.getInt(2), rst.getString(3), rst.getString(4), rst.getDate(5), rst.getString(6), rst.getString(7), rst.getLong(8)));
		}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			System.out.println("in finally of getStudents");
			if(st!=null){
				st.close();
			if(con!=null)
				con.close();
			System.out.println("completed finally of getStudents");
			}
		}
		return list	;
	}
	
//	private String show="select rollNo,age,name,address,dob,gender,course,mobileNo from student where rollNo=?";
	public Student getStudentById(int rollNo) throws SQLException{
		con = JDBCUtility.getConnection();
		Student stud=null;
		try{
			pst4 = con.prepareStatement(show);
			pst4.setInt(1, rollNo);
			rst = pst4.executeQuery();
			while(rst.next()){
				stud=new Student(rst.getInt(1), rollNo, rst.getString(3), rst.getString(4), rst.getDate(5), rst.getString(6), rst.getString(7), rst.getLong(8));
			}
			return stud;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(con!=null)
				con.close();
			if(pst4!=null)
				pst4.close();
		}
		return null;
	}
	
	
//	private String delete="delete * from student where rollNo=?";
	public String deleteStudentById(int rollNo) throws SQLException{
		con = JDBCUtility.getConnection();
		System.out.println("the connection: "+con);
		int i = 0 ;
		String result;
		pst3 = con.prepareStatement(delete);
		pst3.setInt(1, rollNo);
		System.out.println("in deleteStudentByID");
		i = pst3.executeUpdate();
		System.out.println("after deleteStudentByID");
		if(i == 0){
			result="no such id available!";
		}else
		{
			result= rollNo +" successfully deleted";
		}
		return result;
	}
	
	public Student updateStudentById(Student stud) throws SQLException {
		con = JDBCUtility.getConnection();
//		SimpleDateFormat format=null;
		try{
//			format = new SimpleDateFormat("dd-mm-yy");
//			Date dob = format.parse(stud.getDob());
//			
//			java.sql.Date sqlDob = new java.sql.Date(dob.getTime());
			System.out.println("inside update " );
			pst2 = con.prepareStatement(update);
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
			if(con!=null)
				con.close();
			if(pst2!=null)
				pst2.close();
		}
		return stud;
	}
	
}






































