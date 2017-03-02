package dao.impl;

import java.sql.*;

import dao.DaoHelper;

public class DaoHelperImpl implements DaoHelper{
	private static DaoHelperImpl daoHelper = new DaoHelperImpl();
	private Connection connection = null;
	
	
	private DaoHelperImpl() {
		
	}

	@Override
	public Connection getConnection() {
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

	public static DaoHelperImpl getDaoHelper() {
		return daoHelper;
	}

	public static void setDaoHelper(DaoHelperImpl daoHelper) {
		DaoHelperImpl.daoHelper = daoHelper;
	}

	@Override
	public void closeConnection(Connection con) {
		if(con!=null)
		{
			try 
			{
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closePreparedStatement(PreparedStatement stmt) {
		if(stmt!=null)
		{
			try
			{
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void closeResult(ResultSet result) {
		if(result!=null)
		{
			try
			{
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
