/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.GymPlanDAO;
import com._3sq.domainobjects.GymPlan;

/**
 * @author Vishal B
 *
 */
public class GymPlanImpl implements GymPlanDAO {

	
	
	  private static GymPlanImpl singleInstance;
	  
	  public static GymPlanImpl  getGymPlanImpl() {
	    if (singleInstance == null) {
	      synchronized (GymPlanImpl.class) {
	        if (singleInstance == null) {
	          singleInstance = new  GymPlanImpl();
	        }
	      }
	    }
	    return singleInstance;
	  }
	
	  private GymPlanImpl()	{
		  
	  }
	
	
	/* (non-Javadoc)
	 * @see com._3sq.daos.GymPlanDAO#addGymPlan(com._3sq.domainobjects.GymPlan)
	 */
	@Override
		public boolean addGymPlan(GymPlan gymPlan)  throws Exception{
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

	public HashMap<String,GymPlan> getAllGymPlans()	{
		HashMap<String,GymPlan> allPlans = new HashMap<String,GymPlan>(9);

		Connection oracleConn = OrclConnection.getOrclConnection();
		Statement st=null;
		ResultSet rs=null;
		try {
			st=oracleConn.createStatement();
			String sql = " Select PLANID,PLANNAME,PLANFEES,DURATIONINMONTHS FROM GYMPLAN ORDER BY PLANID";
			rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				int pId = rs.getInt(1);
				if(pId==-1)
					continue;
				
				String pName = rs.getString(2);
				int fees = rs.getInt(3);
				int dur = rs.getInt(4);

				GymPlan gym = new  GymPlan();
				
				gym.setPlanID(pId);
				gym.setPlanName(pName);
				gym.setFees(fees);
				gym.setDurationInMonths(dur);
				
				allPlans.put(pName,gym);
			}
			st.close();
			rs.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
		}
		
		return allPlans;
	}
	
	public static void main(String[] ars)	{
		
	}
}
