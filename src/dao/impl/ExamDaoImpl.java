package dao.impl;


import java.util.ArrayList;
import java.util.List;
import java.sql.*;
import dao.*;
import model.Exam;
import utility.TestState;

public class ExamDaoImpl implements ExamDao{
	
	private static ExamDaoImpl examDao = new ExamDaoImpl();
	private static DaoHelper daoHelper = DaoHelperImpl.getDaoHelper();

	private ExamDaoImpl() {}
	
	public static ExamDaoImpl getInstance(){
		return examDao;
	}
	
	@Override
	public void save(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List find(String colum, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List find(String name) {
		Connection connection = daoHelper.getConnection();
		Statement statement = null;
		ResultSet resultSet = null;
		ArrayList list = new ArrayList<>();
		String sql = "SELECT * FROM student JOIN test ON student.id = test.student_id WHERE username = '" + name + "'";
		
		try{
			statement = connection.createStatement();
			resultSet = statement.executeQuery(sql);			
			
			while (resultSet.next()){
				String test_name = resultSet.getString("test_name");
				String state = resultSet.getString("state");
				int score = resultSet.getInt("score");
				String lesson = resultSet.getString("lesson");
				String s = getTestState(state);
				
				Exam examInfo = new Exam();
				examInfo.setState(s);
				examInfo.setName(test_name);
				examInfo.setScore(score);
				examInfo.setStudent_name(name);
				examInfo.setLesson(lesson);		
				list.add(examInfo);
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return list;
	}

	@Override
	public void updateById(Exam exam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List find() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static String getTestState(String state){
		switch (state) {
		case "wait":
			return "未完成";
		case "finish":
			return "已结束";
		default:
			return "error";
		}
	}

}
