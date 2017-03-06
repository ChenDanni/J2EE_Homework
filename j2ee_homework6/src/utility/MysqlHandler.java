package utility;

import java.sql.*;

import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MysqlHandler {
	
	public static Connection connection = null;
	private static InitialContext jndiContext = null;
	private static DataSource datasource = null;

	public static Connection getConnection(){
		if (connection == null){
//			try {
//				final Hashtable properties = new Hashtable();
//				properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
//
//				try {
//					jndiContext = new InitialContext(properties);
//					datasource = (DataSource) jndiContext.lookup("java:/MySqlDS");
//					if (datasource==null)
//						System.out.println("datasource null");
//				} catch (NamingException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("got context");
//				System.out.println("About to get ds---DaoHelper");
//				connection = datasource.getConnection();
//			}catch(SQLException e){
//				e.printStackTrace();
//			}


//			try {
//				Class.forName("com.mysql.jdbc.Driver");
//			} catch (ClassNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			String url = "jdbc:mysql://localhost:8889/homework2";
//			String username = "user";
//			String password = "123321";
//
//
//			try {
//				connection = DriverManager.getConnection(url,username,password);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		
		return connection;
	}
	
	private MysqlHandler(){
		
	}
}
