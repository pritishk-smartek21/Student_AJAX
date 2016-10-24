package com.example.uitls;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtility {

	private static Connection connection=null;
	static{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("drivers loaded");
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		try {
			connection =  DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "oracle");
			System.out.println("sucessfully connected");
			return connection;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("connection failed");
		}
		return null;
	}
	
	public static void close(){
		try {
			connection.close();
			System.out.println("connection closed");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
}
