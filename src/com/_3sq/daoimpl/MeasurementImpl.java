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
import java.util.Vector;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MeasurementDAO;
import com._3sq.domainobjects.MeasurementInfo;
import com._3sq.util._3sqDate;
import com.lowagie.text.pdf.PRStream;

/**
 * @author Vishal B
 *
 */
public class MeasurementImpl implements MeasurementDAO {
	
	

	private MeasurementImpl()	{
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
			preStatement.close();
			
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

			ResultSetMetaData rsmd = rs.getMetaData();

			int columnsNumber = rsmd.getColumnCount();
			System.out.println(columnsNumber);
	
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
		
	
				
	//	boolean bresult = measurementDBImpl.addBodyMeasurement(2, obj_MeasurementInfo);
		//System.out.println("Insertion of Measurement " + bresult);


	}


	public java.util.Date[] getAllMeasurementDates(int currMemberId) {
			Date[] msrmntDates=null;
		try	{
			Vector<Date> measurementDates = null;
			
			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " SELECT MEASUREMENTTAKENDATE from MEASUREMENTDETAILLS WHERE MemberId = ? ORDER BY MEASUREMENTTAKENDATE DESC";

			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			preStatement.setInt(1,currMemberId);  
			
			ResultSet rs = preStatement.executeQuery();			

			while(rs.next())	{	
				if(measurementDates==null)
					measurementDates = new Vector<Date>(5);
				measurementDates.add(_3sqDate.sqlDateToUtilDate(rs.getDate("MEASUREMENTTAKENDATE")));
			}

			if(rs!=null)	
				rs.close();
			if(preStatement!=null)
				preStatement.close();

		
			if(measurementDates!=null)	{
				msrmntDates = new Date[measurementDates.size()];
				
				return measurementDates.toArray(msrmntDates);
				
			}
			

		} catch (Exception e) {
			System.out.println("MeasurementImpl.java: getAllMeasurementDates() : ");
			e.printStackTrace();
		}
		return null;
	}		
	
	public MeasurementInfo getMeasurement(int memberId, Date msrmntDate)	{
		MeasurementInfo currMsr = new MeasurementInfo();
		
		try	{
			
			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " SELECT *  from MEASUREMENTDETAILLS WHERE MemberId = ? AND MEASUREMENTTAKENDATE = ?";

			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			preStatement.setInt(1,memberId);
			preStatement.setDate(2,_3sqDate.utilDateToSqlDate(msrmntDate));
			
			ResultSet rs = preStatement.executeQuery();			
			
			if(rs.next())	{	
				currMsr.setHeight(rs.getInt("HEIGHT"));
				currMsr.setWeight(rs.getFloat("WEIGHT"));
				currMsr.setChest(rs.getFloat("CHEST" ));
				currMsr.setWaist(rs.getFloat("WAIST"	));
				currMsr.setThig(rs.getFloat("THIGHS"));
				currMsr.setCalf(rs.getFloat("CALFS"));
				currMsr.setArms(rs.getFloat("ARMS"));
				currMsr.setForeamrs(rs.getFloat("FOREARMS"));
				currMsr.setFatInPer(rs.getInt("FATINPER"));
				currMsr.setBodyAge(rs.getInt("BODYAGE"));
				currMsr.setBMI(rs.getInt("BMI"));
				currMsr.setRM(rs.getInt("RM"));
				currMsr.setVisceralFat(rs.getFloat("VISCERALFAT" ));	
				currMsr.setWholeBodySF(rs.getFloat("WHOLEBODYSF" ));
				currMsr.setWholeBodySM(rs.getFloat("WHOLEBODYSM"));
				currMsr.setTrunkSF(rs.getFloat("TRUNKSF"));
				currMsr.setTrunkSM(rs.getFloat("TRUNKSM"));
				currMsr.setLegSF(rs.getFloat("LEGSF" ));
				currMsr.setLegSM(rs.getFloat("LEGSM" ));
				currMsr.setArmSF(rs.getFloat("ARMSF" ));
				currMsr.setArmSM(rs.getFloat("ARMSM"));
			}
			if(rs!=null)	
				rs.close();
			if(preStatement!=null)
				preStatement.close();
		} catch (Exception e) {
			System.out.println("MeasurementImpl.java: getMsrmnt() : ");
			e.printStackTrace();
		}
		return currMsr;
	}
}
