package dao;

import java.sql.*;

public interface DaoHelper {
	public Connection getConnection();

	public void closeConnection(Connection con);
	
	public void closePreparedStatement(PreparedStatement stmt);
		
	public void closeResult(ResultSet result);
}
