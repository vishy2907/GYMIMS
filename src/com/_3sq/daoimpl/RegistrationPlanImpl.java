package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.RegistrationPlanDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.RegistrationPlan;
import com._3sq.util._3sqDate;

public class RegistrationPlanImpl implements RegistrationPlanDAO{


	private static RegistrationPlanImpl m_miRegistrationPlanImpl;

	private RegistrationPlanImpl()	{
	}
	public static RegistrationPlanImpl getRegistrationPlanImpl() {
		if (m_miRegistrationPlanImpl == null) {
			synchronized (RegistrationPlanImpl.class) {
				if (m_miRegistrationPlanImpl == null) {
					m_miRegistrationPlanImpl = new  RegistrationPlanImpl();
				}
			}
		}
		return m_miRegistrationPlanImpl;
	}

	
	@Override
	public boolean addRegistrationInfo(int memberId,RegistrationPlan rp,String reason) throws Exception{
		// TODO Auto-generated method stub
		
		//physical insertion
		PreparedStatement preStatement	= null;
			
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into REGISTRATIONINFO (MEMBERID,PLANID,RECEIPTNO,STARTDATE,ENDDATE,REASON,PAIDAMT) values  (?,?,?,?,?,?,?) ";
			
			preStatement = oracleConn.prepareStatement(sql);
			
			 preStatement.setInt(1, memberId);
			 preStatement.setInt(2, rp.getPlanID());
			 preStatement.setInt(3, rp.getReceiptId());
			 preStatement.setDate(4, _3sqDate.utilDateToSqlDate(rp.getStartDate()));
			 preStatement.setDate(5, _3sqDate.utilDateToSqlDate(rp.getEndDate()));
			 preStatement.setString(6, reason);
			 preStatement.setInt(7, rp.getFees());
     		
     		preStatement.executeUpdate();			
			
     		preStatement.close();
					
		return true;
	}
	public static void main(String args[])
	{
		
		
	}
	
	
	public java.util.Date[] getAllDatesOfHistoryPayment(int currMemberId) {
		java.util.Date[] paymentDates=null;
	try	{
		Vector<java.util.Date> paymentDate = null;
		
		Connection oracleConn = OrclConnection.getOrclConnection();
		String sql = " SELECT PAIDAMTDATE from REGISTRATIONINFO WHERE MemberId = ? ORDER BY PAIDAMTDATE DESC";

		PreparedStatement preStatement = oracleConn.prepareStatement(sql);
		preStatement.setInt(1,currMemberId);  
		
		ResultSet rs = preStatement.executeQuery();			

		while(rs.next())	{	
			if(paymentDate==null)
				paymentDate = new Vector<java.util.Date>(5);
			paymentDate.add(_3sqDate.sqlDateToUtilDate(rs.getDate("PAIDAMTDATE")));
		}

		if(rs!=null)	
			rs.close();
		if(preStatement!=null)
			preStatement.close();

	
		if(paymentDate!=null)	{
			paymentDates = new Date[paymentDate.size()];
			return paymentDate.toArray(paymentDates);
		}
		

	} catch (Exception e) {
		System.out.println("RegistrationPlanImpl.java: getAllDatesOfHistoryPayment() : ");
		e.printStackTrace();
	}
	return null;
}		
}
