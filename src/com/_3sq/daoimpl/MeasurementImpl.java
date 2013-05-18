/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MeasurementDAO;
import com._3sq.domainobjects.MeasurementInfo;
import com._3sq.util._3sqDate;

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
		public boolean addBodyMeasurement(int memberId, MeasurementInfo combinedMsrInfo) throws Exception{
		// TODO Auto-generated method stub
	try {			
			
			Connection oracleConn = OrclConnection.getOrclConnection();
	
			String sql = " insert into MEASUREMENTDETAILS (MEMBERID,MEASUREMENTTAKENDATE,HEIGHT,WEIGHT,CHEST,WAIST," +
					" THIGHS,CALFS,ARMS,FOREARMS,FATINPER,BODYAGE,BMI,RM,VISCERALFAT," +
					"WHOLEBODYSF,WHOLEBODYSM,TRUNKSF,TRUNKSM,LEGSF,LEGSM,ARMSF,ARMSM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " ;
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			preStatement.setInt(1, memberId);
			preStatement.setDate(2, _3sqDate.utilDateToSqlDate(combinedMsrInfo.getMeasurementTakenDate()));
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
     		
		} finally {
			return true;
		}
     		
	}

	
		
	/**
	 * @author PRADIP K
	 * @Date 29/12/2012
	 */
	public MeasurementInfo getLatestBodyMeasurement(int memberId) {
		
		
		try {
			
			Connection oracleConn = OrclConnection.getOrclConnection();	

			String sql = "select max(MEASUREMENTTAKENDATE) from MEASUREMENTDETAILS where MEMBERID = ? ";
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


	public Vector<Date> getAllMeasurementDates(int currMemberId) {
		try	{
			Vector<Date> measurementDates = null;
			
			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " SELECT MEASUREMENTTAKENDATE from MEASUREMENTDETAILS WHERE MemberId = ? ORDER BY MEASUREMENTTAKENDATE DESC";

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
				return measurementDates;
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
			String sql = " SELECT *  from MEASUREMENTDETAILS WHERE MemberId = ? AND MEASUREMENTTAKENDATE = ?";

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


	public Collection<MeasurementInfo>	retrieveAllMsrmntsForSerialization()	{
		Collection<MeasurementInfo> msrInfos = new ArrayList<MeasurementInfo>();
		
		try	{
			
			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " SELECT *  from MEASUREMENTDETAILS ";

			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			ResultSet rs = preStatement.executeQuery();			
			
			while(rs.next())	{
				MeasurementInfo currMsr = new MeasurementInfo();
				
				currMsr.setMemberId(rs.getInt("MEMBERID"));
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
				
				msrInfos.add(currMsr);
			}
			if(rs!=null)	
				rs.close();
			if(preStatement!=null)
				preStatement.close();
		} catch (Exception e) {
			System.out.println("MeasurementImpl.java: retrieveAllMsrmntsForSerialization() : ");
			e.printStackTrace();
		}
		
		return msrInfos;
	}
	
	public void addAllDeserializedMeasurements(Collection<MeasurementInfo> allMsrmnts){
		if(allMsrmnts == null || allMsrmnts.size()==0)
			return;
		
		try {			

			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " insert into MEASUREMENTDETAILS (MEMBERID,MEASUREMENTTAKENDATE,HEIGHT,WEIGHT,CHEST,WAIST," +
					" THIGHS,CALFS,ARMS,FOREARMS,FATINPER,BODYAGE,BMI,RM,VISCERALFAT," +
					"WHOLEBODYSF,WHOLEBODYSM,TRUNKSF,TRUNKSM,LEGSF,LEGSM,ARMSF,ARMSM) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) " ;

			PreparedStatement preStatement = oracleConn.prepareStatement(sql);

			for(MeasurementInfo info : allMsrmnts){

				preStatement.setInt(1, info.getMemberId());
				preStatement.setDate(2, _3sqDate.utilDateToSqlDate(info.getMeasurementTakenDate()));
				preStatement.setInt(3, info.getHeight());
				preStatement.setFloat(4, info.getWeight());
				preStatement.setFloat(5, info.getChest());
				preStatement.setFloat(6, info.getWaist());
				preStatement.setFloat(7, info.getThig());
				preStatement.setFloat(8, info.getCalf());
				preStatement.setFloat(9, info.getArms());
				preStatement.setFloat(10, info.getForeamrs());
				preStatement.setInt(11, info.getFatInPer());
				preStatement.setInt(12, info.getBodyAge());
				preStatement.setInt(13, info.getBMI());
				preStatement.setInt(14, info.getRM());
				preStatement.setFloat(15, info.getVisceralFat());	
				preStatement.setFloat(16, info.get_WholeBodySF());
				preStatement.setFloat(17, info.get_WholeBodySM());
				preStatement.setFloat(18, info.getTrunkSF());
				preStatement.setFloat(19, info.getTrunkSM());
				preStatement.setFloat(20, info.getLegSF());
				preStatement.setFloat(21, info.getLegSM());
				preStatement.setFloat(22, info.getArmSF());
				preStatement.setFloat(23, info.getArmSM());

				preStatement.addBatch();

			}
			preStatement.executeBatch();			
			preStatement.close();

		} catch (Exception e) {
			System.out.println("MeasurementImpl.java: addAllDeserializedMeasurements() : ");
			e.printStackTrace();
		}
	}
}
