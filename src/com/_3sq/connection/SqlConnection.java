package com._3sq.connection;

/**
 * 
 * @author Vishal B
 * Handles All MySQL Connections - Singleton Class
 */
public class SqlConnection {
	
	private static SqlConnection 				m_cSqlConnection;
	
	
	
	private SqlConnection()
	{
		
	}
	
	public static SqlConnection getSqlConnection()
	{
		if(m_cSqlConnection == null)
			return new SqlConnection();
		else
			return m_cSqlConnection;
	}
	
	
}
