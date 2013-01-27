package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.RegistrationPlanDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.RegistrationPlan;

public class RegistrationPlanImpl implements RegistrationPlanDAO{

	@Override
	public boolean addRegistrationInfo(int memberid,
			RegistrationPlan registrationplan) {
		// TODO Auto-generated method stub
		
		//physical insertion
		PreparedStatement preStatement	= null;
		ResultSet rs = null;
		try {
			
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into REGISTRATIONINFO (MEMBERID,PLANID,STARTDATE,ENDDATE) values  (?,?,?,?) ";
			
			preStatement = oracleConn.prepareStatement(sql);
			
			
			
			preStatement.setInt(1,memberid);
     		preStatement.setInt(2, registrationplan.getPlanID());
     		preStatement.setDate(3, (Date) registrationplan.getStartDate());
     		preStatement.setDate(4, (Date) registrationplan.getEndDate());
    
     		
     		rs = preStatement.executeQuery();			
			
     		if(rs!=null)
     			return true;
     		else
     			return false;
					
		} catch (Exception e) {
			System.out.println("RegistrationPlanImpl.java: AddRegistrationInfo() : ");
			e.printStackTrace();
		}
		finally	{
			try	{
				if(preStatement!=null)
					preStatement.close();
				if(rs!=null)	
					rs.close();
			}catch(SQLException e)	{
				e.printStackTrace();
			}
		}
		return false;
		
		
		
	}
	public static void main(String args[])
	{
		Connection oracleConn = OrclConnection.getOrclConnection();	
		String sql = " insert into REGISTRATIONINFO(MEMBERID,PLANID,STARTDATE,ENDDATE) values  (?,?,?,?) ";
		
		preStatement = oracleConn.prepareStatement(sql);
		
		
		
		preStatement.setInt(1,gymPlan.getPlanID());
 		preStatement.setString(2, gymPlan.getPlanName());
 		preStatement.setInt(4, gymPlan.getDurationInMonths());
 		preStatement.setInt(3, gymPlan.getFees());

 		rs = preStatement.executeQuery();	
		
		
	}
	
	
	

}
