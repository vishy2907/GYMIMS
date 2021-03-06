package com._3sq.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com._3sq.GymImsImpl;

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

			GymImsImpl gym = GymImsImpl.getGymImsImpl();
			
			String serverName = "@"+gym.getDbmachinename()+":"+gym.getDbport()+":xe";
			
			String url = "jdbc:oracle:thin://" + serverName;

			String username = "root";
			String password = "root";

			m_cOrclConnection = DriverManager.getConnection( url, username, password );
			System.out.println("Connection Established...");
						
		} catch (Exception e) {
				System.err.println("OrclConnection.java : "+e.toString());
			e.printStackTrace();
		}
		return m_cOrclConnection;
	}

	

	public static Connection getOrclConnection() {
		try {
			if (m_cOrclConnection == null || m_cOrclConnection.isClosed())	{   
				synchronized (OrclConnection.class) {
					if (m_cOrclConnection == null) {
						System.out.println("Returning new oracle connection");
						m_cOrclConnection = new OrclConnection().CreateNewConnection();
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return m_cOrclConnection;
	}

	
	public static void  main(String args[]){
		Connection conn = getOrclConnection();
		System.out.println(conn.toString());
	}
}
