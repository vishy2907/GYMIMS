package com._3sq.connection;

import java.sql.Connection;
import java.sql.DriverManager;

//import com.mysql.jdbc.Connection;

/**
 * 
 * @author Vishal B Handles All MySQL Connections - Singleton Class
 */
public class SqlConnection {

	private static Connection m_cSqlConnection;

	private SqlConnection()
	{
		
	}
	private Connection CreateNewConnection() {
		try {
			String driverName = "com.mysql.jdbc.Driver";
			Class.forName(driverName);

			String serverName = "localhost:3306";
			String mydatabase = "gym db";

			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;

			String username = "root";
			String password = "gracious1!";

			m_cSqlConnection = DriverManager.getConnection( url, username, password );
						
		} catch (Exception e) {
				System.err.println("SqlConnection.java : ");
			e.printStackTrace();
		}
		return m_cSqlConnection;
	}

	public static Connection getSqlConnection() {
		if (m_cSqlConnection == null)
			return new SqlConnection().CreateNewConnection();
		else
			return m_cSqlConnection;
	}
}
