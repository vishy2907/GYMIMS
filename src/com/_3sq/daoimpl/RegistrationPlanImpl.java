package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.RegistrationPlanDAO;
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
			String sql = " insert into REGISTRATIONINFO (MEMBERID,PLANID,RECEIPTNO,STARTDATE,ENDDATE,REASON,PAIDAMT,PAIDAMTDATE) values  (?,?,?,?,?,?,?,?) ";
			
			preStatement = oracleConn.prepareStatement(sql);
			
			 preStatement.setInt(1, memberId);
			 preStatement.setInt(2, rp.getPlanID());
			 preStatement.setInt(3, rp.getReceiptId());
			 preStatement.setDate(4, _3sqDate.utilDateToSqlDate(rp.getStartDate()));
			 preStatement.setDate(5, _3sqDate.utilDateToSqlDate(rp.getEndDate()));
			 preStatement.setString(6, reason);
			 preStatement.setInt(7, rp.getFees());
			 preStatement.setDate(8, _3sqDate.utilDateToSqlDate(rp.getPaindAmtDate()));
     		
     		preStatement.executeUpdate();			
			
     		preStatement.close();
					
		return true;
	}

	public Vector<java.util.Date> getAllDatesOfHistoryPayment(int currMemberId) {
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

		if(rs != null)	
			rs.close();
		if(preStatement != null)
			preStatement.close();
		
		if(paymentDate!=null)	{
			return paymentDate; 
		}
		
	} catch (Exception e) {
		System.out.println("RegistrationPlanImpl.java: getAllDatesOfHistoryPayment() : ");
		e.printStackTrace();
	}
	return null;
}
	
	public RegistrationPlan getPaymentDetails(int memberId, java.util.Date paymentDate)	{
		RegistrationPlan regPlan = new RegistrationPlan();
		try	{
			
			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " SELECT * from REGISTRATIONINFO WHERE MemberId = ? AND PAIDAMTDATE = ?";

			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			preStatement.setInt(1,memberId);
			preStatement.setDate(2,_3sqDate.utilDateToSqlDate(paymentDate));
			
			ResultSet rs = preStatement.executeQuery();			
			
			if(rs.next())	{
				regPlan.setReceiptId(rs.getInt("RECEIPTNO"));
				int planId = rs.getInt("PLANID");
				regPlan.setPlanID(planId);
				regPlan.setStartDate(_3sqDate.sqlDateToUtilDate(rs.getDate("STARTDATE")));
				regPlan.setEndDate(_3sqDate.sqlDateToUtilDate(rs.getDate("ENDDATE")));
				regPlan.setReason(rs.getString("REASON"));
				regPlan.setPaidAmount(rs.getInt("PAIDAMT"));
				regPlan.setPaindAmtDate(_3sqDate.sqlDateToUtilDate(rs.getDate("PAIDAMTDATE")));
			}
			if(rs!=null)	
				rs.close();
			if(preStatement!=null)
				preStatement.close();
		} catch (Exception e) {
			System.out.println("RegistrationPlanImpl.java: getPaymentDetail(int,date) : ");
			e.printStackTrace();
		}
		return regPlan;
	}
	
	
	//For serialization
	public Collection<RegistrationPlan>	retrieveAllRegInfoForSerialization()	{
		List<RegistrationPlan> allRegsHistoryInfo = new ArrayList<RegistrationPlan>();
		try	{
			
			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " SELECT * from REGISTRATIONINFO";

			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			ResultSet rs = preStatement.executeQuery();			
			
			while(rs.next())	{
				RegistrationPlan regPlan = new RegistrationPlan();
				
				regPlan.setMemberId(rs.getInt("MEMBERID"));
				regPlan.setReceiptId(rs.getInt("RECEIPTNO"));
				regPlan.setPlanID(rs.getInt("PLANID"));
				regPlan.setStartDate(_3sqDate.sqlDateToUtilDate(rs.getDate("STARTDATE")));
				regPlan.setEndDate(_3sqDate.sqlDateToUtilDate(rs.getDate("ENDDATE")));
				
				//regPlan.setReason(rs.getString("REASON"));
				//regPlan.setPaidAmount(rs.getInt("PAIDAMT"));
				//regPlan.setPaindAmtDate(_3sqDate.sqlDateToUtilDate(rs.getDate("PAIDAMTDATE")));
				
				allRegsHistoryInfo.add(regPlan);
			}
			if(rs!=null)	
				rs.close();
			if(preStatement!=null)
				preStatement.close();
		} catch (Exception e) {
			System.out.println("RegistrationPlanImpl.java: retrieveAllRegInfoForSerialization() : ");
			e.printStackTrace();
		}
		return allRegsHistoryInfo;
	}
	
	public void addAllDeserializedGymPlans(Collection<RegistrationPlan> regPlans){
		if(regPlans==null || regPlans.size()==0)
			return;
		
		PreparedStatement preStatement	= null;
		try	{
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into REGISTRATIONINFO (MEMBERID,PLANID,RECEIPTNO,STARTDATE,ENDDATE,REASON,PAIDAMT,PAIDAMTDATE) values  (?,?,?,?,?,?,?,?) ";
			
			preStatement = oracleConn.prepareStatement(sql);
			
			for(RegistrationPlan regPlan : regPlans)	{
				 preStatement.setInt(1, regPlan.getMemberId());
				 preStatement.setInt(2, regPlan.getPlanID());
				 preStatement.setInt(3, regPlan.getReceiptId());
				 preStatement.setDate(4, _3sqDate.utilDateToSqlDate(regPlan.getStartDate()));
				 preStatement.setDate(5, _3sqDate.utilDateToSqlDate(regPlan.getEndDate()));
				 preStatement.setString(6, regPlan.getReason());
				 preStatement.setInt(7, regPlan.getFees());
				 preStatement.setDate(8, _3sqDate.utilDateToSqlDate(regPlan.getPaindAmtDate()));
	
				 preStatement.addBatch();
			}
	 		preStatement.executeBatch();
	 		preStatement.close();
		}catch(Exception eq){
			System.out.println("Exception occured while addAllDeserializedGymPlans() ");
			eq.printStackTrace();
		}
	}

	public void toCode(){
		
		TreeMap<Integer,String> hm = new TreeMap<Integer,String>();
		hm.put(1593,"17-Dec-12");
		hm.put(1601,"18-Dec-12");
		hm.put(1607,"19-Dec-12");
		hm.put(1610,"20-Dec-12");
		hm.put(1619,"21-Dec-12");
		hm.put(1626,"22-Dec-12");
		hm.put(1627,"24-Dec-12");
		hm.put(1635,"26-Dec-12");
		hm.put(1642,"27-Dec-12");
		hm.put(1646,"28-Dec-12");
		hm.put(1650,"29-Dec-12");
		hm.put(1651,"31-Dec-12");
		hm.put(1654,"01-Jan-13");
		hm.put(1656,"02-Jan-13");
		hm.put(1676,"03-Jan-13");
		hm.put(1681,"04-Jan-13");
		hm.put(1689,"05-Jan-13");
		hm.put(1695,"07-Jan-13");
		hm.put(1706,"08-Jan-13");
		hm.put(1720,"19-Jan-13");
		hm.put(1730,"10-Jan-13");
		hm.put(1737,"11-Jan-13");
		hm.put(1741,"12-Jan-13");
		hm.put(1748,"14-Jan-13");
		hm.put(1754,"15-Jan-13");
		hm.put(1766,"16-Jan-13");
		hm.put(1771,"17-Jan-13");
		hm.put(1784,"18-Jan-13");
		hm.put(1789,"19-Jan-13");
		hm.put(1792,"21-Jan-13");
		hm.put(1808,"22-Jan-13");
		hm.put(1813,"23-Jan-13");
		hm.put(1816,"24-Jan-13");
		hm.put(1824,"25-Jan-13");
		hm.put(1833,"29-Jan-13");
		hm.put(1839,"30-Jan-13");
		hm.put(1846,"31-Jan-13");
		hm.put(1858,"01-Feb-13");
		hm.put(1866,"02-Feb-13");
		hm.put(1868,"04-Feb-13");
		hm.put(1880,"05-Feb-13");
		hm.put(1890,"06-Feb-13");
		hm.put(1900,"07-Feb-13");
		hm.put(1907,"08-Feb-13");
		hm.put(1918,"09-Feb-13");
		hm.put(1922,"11-Feb-13");
		hm.put(1938,"12-Feb-13");
		hm.put(1945,"13-Feb-13");
		hm.put(1951,"");

		
		Integer[] keys  = new Integer[49];
		keys = hm.keySet().toArray(keys);
		
		
		PreparedStatement preStatement	= null;
		try	{
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = "update REGISTRATIONINFO set PAIDAMTDATE = ? where receiptno between ? and ?";
			preStatement = oracleConn.prepareStatement(sql);
		
			for(int i=0;i<keys.length-1;i++)	{
				System.out.println(hm.get(keys[i])+" : "+keys[i]+" - "+(keys[i+1]-1));
				preStatement.setDate(1,_3sqDate.utilDateToSqlDate(new Date(hm.get(keys[i]))));
				preStatement.setInt(2, keys[i]);
				preStatement.setInt(3, keys[i+1]-1);
				
				preStatement.addBatch();
				
			}

			preStatement.executeBatch();
	 		preStatement.close();
		}catch(Exception eq){
			System.out.println("Exception occured while addAllDeserializedGymPlans() ");
			eq.printStackTrace();
		}
	}
	public static  void main(String[] args){
		new RegistrationPlanImpl().toCode();
	}

	public int getNextReceiptNumber()
	{
		Connection oracleConn = OrclConnection.getOrclConnection();
		Statement st=null;
		ResultSet rs=null;
		int temp=1;
		try {
			st=oracleConn.createStatement();
			String sql = " Select MAX(RECEIPTNO) FROM REGISTRATIONINFO";
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

	
}
