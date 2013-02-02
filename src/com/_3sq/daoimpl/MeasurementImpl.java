/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Date;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MeasurementDAO;
import com._3sq.domainobjects.MeasurementInfo;

/**
 * @author Vishal B
 *
 */
public class MeasurementImpl implements MeasurementDAO {
	
	

	private MeasurementImpl()
	{
		
	}
	
	  private static MeasurementImpl singleInstance;
	  
	  public static MeasurementImpl getMeasurementImpl() {
	    if (singleInstance == null) {
	      synchronized (MeasurementImpl.class) {
	        if (singleInstance == null) {
	          singleInstance = new  MeasurementImpl();
	        }
	      }
	    }
	    return singleInstance;
	  }
	
	
	
	
	
	/**
	 * @author PRADIP K
	 * Date- 29/12/2012
	 */
		public boolean addBodyMeasurement(int memberId, MeasurementInfo combinedMsrInfo) {
		// TODO Auto-generated method stub
	try {			
			
			Connection oracleConn = OrclConnection.getOrclConnection();
	
			String sql = " insert into MEASUREMENTDETAILLS (MEMBERID,MEASUREMENTTAKENDATE,HEIGHT,WEIGHT,CHEST,WAIST," +
					" THIGHS,CALFS,ARMS,FOREARMS,FATINPER,BODYAGE,BMI,RM,VISCERALFAT," +
					"WHOLEBODYSF,WHOLEBODYSM,TRUNKSF,TRUNKSM,LEGSF,LEGSM,ARMSF,ARMSM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " ;
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			preStatement.setInt(1, memberId);
			preStatement.setString(2, "" +  combinedMsrInfo.getMeasurementTakenDate().getTime());
			preStatement.setInt(3, combinedMsrInfo.getHeight());
			preStatement.setFloat(4, combinedMsrInfo.getWeight());
			preStatement.setFloat(5, combinedMsrInfo.getChest());
			preStatement.setFloat(6, combinedMsrInfo.getWaist());
			preStatement.setFloat(7, combinedMsrInfo.getThig());
			preStatement.setFloat(8, combinedMsrInfo.getCalf());
			preStatement.setFloat(9, combinedMsrInfo.getArms());
			preStatement.setFloat(10, combinedMsrInfo.getForeamrs());
			preStatement.setInt(11, combinedMsrInfo.getFatInPer());
			preStatement.setInt(12, combinedMsrInfo.getBodyAge());
			preStatement.setInt(13, combinedMsrInfo.getBMI());
			preStatement.setInt(14, combinedMsrInfo.getRM());
			preStatement.setFloat(15, combinedMsrInfo.getVisceralFat());	
			preStatement.setFloat(16, combinedMsrInfo.get_WholeBodySF());
			preStatement.setFloat(17, combinedMsrInfo.get_WholeBodySM());
			preStatement.setFloat(18, combinedMsrInfo.getTrunkSF());
			preStatement.setFloat(19, combinedMsrInfo.getTrunkSM());
			preStatement.setFloat(20, combinedMsrInfo.getLegSF());
			preStatement.setFloat(21, combinedMsrInfo.getLegSM());
			preStatement.setFloat(22, combinedMsrInfo.getArmSF());
			preStatement.setFloat(23, combinedMsrInfo.getArmSM());
     		
     		
     		ResultSet rs = preStatement.executeQuery();			
			
     		if(rs!=null)
     			return true;
     		else
     			return false;

			
		} catch (Exception e) {
			System.out.println("MeasurementImpl.java: addBodyMeasurement() : ");
			e.printStackTrace();
		}
     		
     		
	return false;
	}

	
		
	/**
	 * @author PRADIP K
	 * @Date 29/12/2012
	 */
	public MeasurementInfo getLatestBodyMeasurement(int memberId) {
		
		
		try {
			
		Connection oracleConn = OrclConnection.getOrclConnection();	
		
		String sql = "select max(MEASUREMENTTAKENDATE) from MEASUREMENTDETAILLS where MEMBERID = ? ";
		PreparedStatement preStatement = oracleConn.prepareStatement(sql);
		preStatement.setInt(1,memberId);  
		ResultSet rs = preStatement.executeQuery();
		rs.next();
		
		/*String sql1 = "select * from MEASUREMENTDETAILLS where MEMBERID = ? and MEASUREMENTTAKENDATE = '?' ";
		PreparedStatement preStatement1 = oracleConn.prepareStatement(sql1);
		String temp = rs.getString(1);
		//long temp_date = Long.parseLong(temp);
		preStatement1.setInt(1,memberId);  
	    preStatement1.setString(2,temp);  
		
		ResultSet rs1 = preStatement.executeQuery();
		rs1.next();
		System.out.println("Asooooooooo"+rs1.getRow());  //prints how many rows in result
		*/
		ResultSetMetaData rsmd = rs.getMetaData();

		int columnsNumber = rsmd.getColumnCount();
		System.out.println(columnsNumber);
		
		
		//System.out.println("Asooo"+rs1.getInt(1));
		
	    
		
/*		  long id = rs1.getLong(1);
		System.out.println(id);
			
	*/	
		
		
		
	/*	while (rs1.next()) {
             // Read values using column name
            // int id = rs1.getInt("MEMBERID");
			 int id = rs1.getInt(1);
			 
             //String mesuredate = rs1.getString("MEASUREMENTTAKENDATE");
			String mesuredate = rs1.getString(2);
             
			 
			 //int height = rs1.getInt("HEIGHT");
			int height = rs1.getInt(3);
              
              
             System.out.printf("%d %s %d \n" + id, mesuredate, height);
         }*/
	
		}
		
		
		catch (Exception e) {
		
			System.out.println(" MeasurementImpl.java  : getLatestBodyMeasurement ");
			e.printStackTrace();
		}
		
		return null;
	}

	
	
	
	
	
	/* (non-Javadoc)
	 * @see com._3sq.daos.MeasurementDAO#getBodyMeasurementHistory(int)
	 */
	@Override
	public ArrayList<MeasurementInfo> getBodyMeasurementHistory(int memberId) {
		// TODO Auto-generated method stub
		return null;
	}



	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		
		
		
		//ALL BELOW THINGS ARE FOR TEMPORATILY
		MeasurementInfo obj_MeasurementInfo = new MeasurementInfo();
		MeasurementImpl measurementDBImpl = MeasurementImpl.getMeasurementImpl();
	
				
		obj_MeasurementInfo.setMeasurementTakenDate(new Date("07/30/1991"));
		obj_MeasurementInfo.setHeight(1);
		obj_MeasurementInfo.setWeight(1);
		obj_MeasurementInfo.setChest(1);
		obj_MeasurementInfo.setWaist(1);
		obj_MeasurementInfo.setThig(1);
		obj_MeasurementInfo.setCalf(1);
		obj_MeasurementInfo.setArms(1);
		obj_MeasurementInfo.setForeamrs(1);
		obj_MeasurementInfo.setFatInPer(1);
		obj_MeasurementInfo.setBodyAge(1);
		obj_MeasurementInfo.setBMI(1);
		obj_MeasurementInfo.setRM(1);
		obj_MeasurementInfo.setVisceralFat(1);
		obj_MeasurementInfo.setWholeBodySF(1);
		obj_MeasurementInfo.setWholeBodySM(1);
		obj_MeasurementInfo.setTrunkSF(1);
		obj_MeasurementInfo.setTrunkSM(1);
		obj_MeasurementInfo.setLegSF(1);
		obj_MeasurementInfo.setLegSM(1);
		obj_MeasurementInfo.setArmSF(1);
		obj_MeasurementInfo.setArmSM(1);
		
				
	//	boolean bresult = measurementDBImpl.addBodyMeasurement(2, obj_MeasurementInfo);
		//System.out.println("Insertion of Measurement " + bresult);
			
	    measurementDBImpl.getLatestBodyMeasurement(2);

	}		

}
