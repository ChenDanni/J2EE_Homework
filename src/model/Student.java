package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import utility.MysqlHandler;
import utility.TestState;

public class Student {
	String id;
	String username;
	public static Connection connection = MysqlHandler.getConnection();
	
	public Student(String id, String username){
		this.id = id;
		this.username = username;
	}
	
	public static String getIdByName(String username){
		String id = "";
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM student WHERE username='" + username + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
				id = resultSet.getString("id");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return id;
	}
	

}
