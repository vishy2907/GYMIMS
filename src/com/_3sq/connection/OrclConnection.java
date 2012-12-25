package com._3sq.connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * 
 * @author PRADIP K
 *use : Singleton class for oracleConnection
 */
public class OrclConnection {

	
	private static Connection m_cOrclConnection;

	private OrclConnection()
	{
		
	}
	
	/**
	 * 
	 * @return Connection Object
	 */
	private Connection CreateNewConnection() {
		try {
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);

			String serverName = "@localhost:1521";
			
			String url = "jdbc:oracle:thin://" + serverName;

			String username = "root";
			String password = "root";

			m_cOrclConnection = DriverManager.getConnection( url, username, password );
						
		} catch (Exception e) {
				System.err.println("OrclConnection.java : ");
			e.printStackTrace();
		}
		return m_cOrclConnection;
	}

	public static Connection getOrclConnection() {
		if (m_cOrclConnection == null)
			return new OrclConnection().CreateNewConnection();
		else
			return m_cOrclConnection;
	}
	
	
	public static void  main(String args[]){
		
		Connection conn = getOrclConnection();
		System.out.println(conn.toString());
	}
}
