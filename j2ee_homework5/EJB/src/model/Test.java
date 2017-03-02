package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import utility.*;

public class Test {
	public TestState state;
	public String name;
	public int score;
	public String student_id;
	public String lesson;
	public static Connection connection = MysqlHandler.getConnection();
	
	public Test(TestState state,String name,String student_id,int score,String lesson){
		this.state = state;
		this.name = name;
		this.student_id = student_id;
		this.score = score;
		this.lesson = lesson;
		
	}
	public static TestState getTestState(String state){
		switch (state) {
		case "wait":
			return TestState.WAIT;
		case "finish":
			return TestState.FINISH;
		default:
			return TestState.ERR;
		}
	}
	
	public static ArrayList<Test> getTestInfo(String student_id){
		ArrayList<Test> ret = new ArrayList<>();
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM test WHERE student_id=" + student_id;
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
				String name = resultSet.getString("test_name");
				String state = resultSet.getString("state");
				int score = resultSet.getInt("score");
				String lesson = resultSet.getString("lesson");
				
				TestState s = getTestState(state);
				Test t = new Test(s, name, student_id, score,lesson);
				ret.add(t);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	

}
