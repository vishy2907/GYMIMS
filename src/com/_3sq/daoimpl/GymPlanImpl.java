/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.GymPlanDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.GymPlan;

/**
 * @author Vishal B
 *
 */
public class GymPlanImpl implements GymPlanDAO {

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
