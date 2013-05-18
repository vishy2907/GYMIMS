/**
 * 
 */
package com._3sq.util;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;

import com._3sq.GymImsImpl;
import com._3sq.connection.OrclConnection;

/**
 * This class will handle the all backup related operation.. 
 * @author VishalB
 *
 */
public class _3sqBackupHandler {

	public void takeBackup()	{
		System.out.println("Backup generation script started...");
		try	{
			_3sqSerializer serzr = new _3sqSerializer();
			serzr.serializeData();
			System.out.println("Backup generation script Completed...");
		}catch(Exception e)	{
			System.out.println("Problem occured while running backup generation script...");
			e.printStackTrace();
		}
	}
	
	
	/**
	 * will try to restore the backup based on
	 * @param fileName file name excluding file path
	 */
	public void restoreBackupFile(String fileName)	{
		restoreBackupFile(new File(GymImsImpl.getGymImsImpl().getBackupDirectory()+fileName));
	}

	/**
	 * This method will try to perform the cleanup of existing state of db. 
	 */
	private boolean prepareForRestoringBackup() throws Exception{
		//Truncate all the tables...

		Connection oracleConn = OrclConnection.getOrclConnection();

		Statement preStatement=null;
		try {
			preStatement = oracleConn.createStatement();
			System.out.println("Cleanup process started...");
			//Delete registration info
			String sql = " DELETE FROM REGISTRATIONINFO";
			preStatement.executeUpdate(sql);

			sql = " DELETE FROM MEASUREMENTDETAILS";
			preStatement.executeUpdate(sql);

			sql = " DELETE FROM GYMPLAN";
			preStatement.executeUpdate(sql);

			sql = " DELETE FROM MEMBER";
			preStatement.executeUpdate(sql);
			System.out.println("Cleanup process completed Successfully...");
		}
		finally{
			preStatement.close();
		}
		return true;
	}
	
	private void restoreBackupFile(File file) {	
		boolean bNoException = true;
		Connection oracleConn = OrclConnection.getOrclConnection();
		Savepoint s1 = null;
		try	{
			System.out.println("Backup Restoring Process started....");
			//Set the auto commit false, so changes can be reverted later...
			oracleConn.setAutoCommit(false);
			s1 = oracleConn.setSavepoint("beforePrepareForRestore");
			
			//Delete all the data..
			prepareForRestoringBackup();
			
			//Restore the backup
			_3sqSerializer restoringProcess = new _3sqSerializer();
			restoringProcess.setBackupFile(file);
			restoringProcess.deserializeData();
			
			System.out.println("Backup Restoring Process Completed Successfully....");
		}catch(Exception e){
			bNoException = false;
			e.printStackTrace();
		}finally	{
			try {
				if(bNoException == true){
					oracleConn.commit();
				}
				else{
					oracleConn.rollback(s1);
				}
				oracleConn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[]args){
		//_3sqBackupHandler backup = new _3sqBackupHandler();
		//backup.restoreBackupFile("Backup - 16-Feb-2013 10-33.backup");
	}
}
