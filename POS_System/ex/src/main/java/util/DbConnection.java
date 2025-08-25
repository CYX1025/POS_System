package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

	public static void main(String[] args) {
		System.out.println(DbConnection.getDb());

	}
	
	public static Connection getDb() 
	{
		String url = "jdbc:mysql://localhost:3306/portfolio";
		String username = "root";
		String password = "1234";
		Connection conn = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(url, username, password);
			System.out.println("connect success");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("no driver");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

}
