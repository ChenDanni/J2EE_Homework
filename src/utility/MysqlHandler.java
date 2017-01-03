package utility;

import java.sql.*;
import java.util.ArrayList;

import apple.laf.JRSUIConstants.State;
import model.Test;
import utility.*;

public class MysqlHandler {
	
	public static Connection connection = null;
	
	public static Connection getConnection(){
		if (connection == null){
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String url = "jdbc:mysql://localhost:8889/homework2";
			String username = "user";
			String password = "123321";
			
			
			try {
				connection = DriverManager.getConnection(url,username,password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return connection;
	}
	
	private MysqlHandler(){
		
	}
}
