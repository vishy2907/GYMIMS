package com._3sq.util;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import com._3sq.connection.OrclConnection;




public class _3sqInitialDBGenerator {

	/**
	 * @param args
	 */
	
	private Connection m_cOrclConnection = null;	
	

	/*
	 * Retrieve a database connection for default 'SYSTEM' user
	 */

	static String baseUname = "";
	static String basePwd = "";
	static String dbMachineName = "";
	static String dbport = "";
	
	private Connection getConnection(String username, String password) {
		
		try
		{
			
			String driverName = "oracle.jdbc.driver.OracleDriver";
			Class.forName(driverName);
			System.out.println("DB MACHINE : "+dbMachineName);
			String serverName = "@"+dbMachineName+":"+dbport+":xe";
			System.out.println("Server Name : "+serverName);
			String url = "jdbc:oracle:thin://" + serverName;
			//String username = "SYSTEM";
			//String password = "oracle$1";

			m_cOrclConnection = DriverManager.getConnection( url, username, password );
			System.out.println("Connection Established...");
					
		} catch (Exception e) {
			System.err.println("OrclConnection.java : "+e.toString());
			e.printStackTrace();
		}
		return m_cOrclConnection;
	}
	
	/*
	 * Check for existance of 'ROOT' user
	 */
	private boolean isUserExist(String username){
		
		/* Get DB Connection*/
		
		boolean flag = false;
		if(m_cOrclConnection == null)	{
			m_cOrclConnection = getConnection(baseUname,basePwd);
		}
		try
		{
		 String sql = "SELECT COUNT (*) FROM dba_users WHERE username = UPPER ('root')";
		 //PreparedStatement stmnt = m_cOrclConnection.prepareStatement(sql);
		 //stmnt.setString(1, username);
		 Statement stmnt = m_cOrclConnection.createStatement();
		 ResultSet rs = stmnt.executeQuery("SELECT COUNT (*) FROM dba_users WHERE username = UPPER ('root')");
		 rs.next();
		 short cnt = (short)rs.getInt(1);
		 System.out.println("Count:"+cnt);
		 
		 if(cnt!=0){
			 flag = true;
			 System.out.print("\n User already exists");
		 }
		}
		catch(Exception e){
			System.err.println("OrclConnection.java : "+e.toString());
			e.printStackTrace();
		}
		return flag;
	}
	
	
	/*
	 *  Create a new 'ROOT user 
	 */
	private boolean createUser(){
			
		int qcnt = 0;
		try {
			/*Create "root" user*/
			Statement stmnt = m_cOrclConnection.createStatement();
			stmnt.executeUpdate("CREATE User root IDENTIFIED BY root"); 
			boolean flag = true;
			if( flag == false)
				{
					System.out.println("FAILED TO CREATE USER");
					m_cOrclConnection.close();
					return false;
				}
			flag = stmnt.execute("GRANT dba to root"); 
			if(flag == false){
				System.out.println("Failed to GRANT dba previlage ");
			}
			flag = stmnt.execute("GRANT ALL PRIVILEGES to root"); 
			if(flag == false){
				System.out.println("Failed to GRANT dba previlage ");
			}
		
			
			/*Get new connection*/
			if((m_cOrclConnection = getConnection("root", "root")) == null)
			{
				System.out.println("Failed to get root user connection");
				return false;
			}
			
			
					
			/* Read Script*/
			String ipFileName = "C:/Backup/SQL Script.txt";
			
			//File f = new File();
			FileReader fr = new FileReader(ipFileName);
			BufferedReader br = new BufferedReader(fr);
			
			String contents = "", temp="", sql="";
			while((temp=br.readLine())!= null)
					contents+=temp;
			String [] queries = contents.split("/");
			for(int i=0; i< queries.length; i++){
				queries[i].trim();
				System.out.println(" "+i+" "+queries[i]); //Printing
				
			}
			
			/* Fire Queries */
			
			while(qcnt < queries.length)
				{
					sql = queries[qcnt];
					PreparedStatement query = m_cOrclConnection.prepareStatement(sql);
					query.executeQuery();
					/*if(query.execute()==false){
						System.out.println(qcnt);
						throw new SQLException();
					}
						*/
					qcnt++;
				}
			
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(qcnt);
			resetDatabase();
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(qcnt);
			resetDatabase();
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(qcnt);
			resetDatabase();
			e.printStackTrace();
		}
		
		
		return true;
	}
	
	
	private void resetDatabase() {
		Statement stmnt;
		try {
			
			m_cOrclConnection.close();
			m_cOrclConnection = getConnection(baseUname, basePwd);
			stmnt = m_cOrclConnection.createStatement();
			if(stmnt.execute("DROP USER root CASCADE")){
				System.out.println("USER ROOT dropped successfully..Database RESET");
				m_cOrclConnection.close();
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				m_cOrclConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
		
	}

	private void performInitialSetup()	{
		Properties prop = new Properties();
		String propFileName = "gym.properties";
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);

		if (inputStream == null)
		{
		}
		else	{
			try {
				prop.load(inputStream);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		baseUname =	prop.getProperty("baseusername");
		basePwd = prop.getProperty("basepassword");
		dbMachineName = prop.getProperty("dbmachinename");
		dbport = prop.getProperty("dbport");
		
		System.out.println("DB MACHINE NAME : "+dbMachineName);
		System.out.println("Base U Name : "+baseUname);
	}
	
	public void checkUserAndCreateIfNotExists()	{
		performInitialSetup();
		if(isUserExist("root") == false )
		{
			System.out.println("User Creation Script Started...");

			createUser();
			try {
				m_cOrclConnection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("User Creation Script Completed...");
		}
		
	}
}
