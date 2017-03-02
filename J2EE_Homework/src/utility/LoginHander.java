package utility;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginHander {
	public static Connection connection = MysqlHandler.getConnection();
	
	public static LoginState getLoginRes(String username,String pwd){
		
		if (connection == null)
			return null;
		try {
			Statement statement = connection.createStatement();
			String sql = "SELECT * FROM student WHERE username='"+ username + "'";
			ResultSet resultSet = statement.executeQuery(sql);
			
			int i = 0;
			
			while (resultSet.next()){
				i++;
				if (i > 1){
					return LoginState.ERR;
				}
				int stu = resultSet.getInt("id");
				System.out.println(stu);
				String pwdd = resultSet.getString("password");
				if (!pwd.equals(pwdd)){
					return LoginState.WRONGPWD;
				}
			}
			if (i < 1){
				return LoginState.USERNOTEXIST;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return LoginState.SUCCESS;
	}
}
