package dao.impl;

import java.sql.*;
import java.util.Hashtable;

import dao.DaoHelper;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DaoHelperImpl implements DaoHelper{
	private static DaoHelperImpl daoHelper = new DaoHelperImpl();
	private Connection connection = null;
	private static InitialContext jndiContext = null;
	private static DataSource datasource = null;
	
	
	private DaoHelperImpl() {
		
	}

	@Override
	public Connection getConnection() {
		if (connection == null) {
			try {
				final Hashtable properties = new Hashtable();
				properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");

				try {
					jndiContext = new InitialContext(properties);
					datasource = (DataSource) jndiContext.lookup("java:/MySqlDS");
					if (datasource == null)
						System.out.println("datasource null");
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("got context");
				System.out.println("About to get ds---DaoHelper");
				connection = datasource.getConnection();
			} catch (SQLException e) {
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
