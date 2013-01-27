/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.GymPlanDAO;
import com._3sq.domainobjects.GymPlan;

/**
 * @author Vishal B
 *
 */
public class GymPlanImpl implements GymPlanDAO {

	private static GymPlanImpl m_miGymPlanImpl;

	private GymPlanImpl()	{
	}
	
	public static GymPlanImpl getgymImpl() {
		if (m_miGymPlanImpl==null)
			return new GymPlanImpl();
		else
			return m_miGymPlanImpl;
	}
	

	
	
	/* (non-Javadoc)
	 * @see com._3sq.daos.GymPlanDAO#addGymPlan(com._3sq.domainobjects.GymPlan)
	 */
	@Override
		public boolean addGymPlan(GymPlan gymPlan) {
		// TODO Auto-generated method stub
		
		//physical insertion
		PreparedStatement preStatement	= null;
		ResultSet rs = null;
		try {
			
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into GYMPLAN(PLANID,PLANNAME,PLANFEES,DURATIONINMONTHS ) values  (?,?,?,?) ";
			
			preStatement = oracleConn.prepareStatement(sql);
			
			
			
			preStatement.setInt(1,gymPlan.getPlanID());
     		preStatement.setString(2, gymPlan.getPlanName());
     		preStatement.setInt(4, gymPlan.getDurationInMonths());
     		preStatement.setInt(3, gymPlan.getFees());
    
     		rs = preStatement.executeQuery();			
			
     		if(rs!=null)
     			return true;
     		else
     			return false;
					
		} catch (Exception e) {
			System.out.println("GymPlanImpl.java: AddGymPlan() : ");
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
		return true;
		
	
	}
	public int getNextPlanID()
	{
		Connection oracleConn = OrclConnection.getOrclConnection();
		Statement st=null;
		ResultSet rs=null;
		int temp=1;
		try {
			st=oracleConn.createStatement();
			String sql = " Select MAX(PLANID) FROM GYMPLAN ";
			rs=st.executeQuery(sql);
			
			if(rs.next())
			{
				temp = rs.getInt(1);
									
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try {
				st.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
				return temp+1;

	}

	@Override
	public boolean removeGymPlan(int planId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateGymPlan(int planId, GymPlan gymPlan) {
		// TODO Auto-generated method stub
		return false;
	}

}
