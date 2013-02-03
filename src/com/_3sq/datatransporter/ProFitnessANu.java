package com._3sq.datatransporter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import com._3sq.connection.OrclConnection;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.util._3sqDate;

public class ProFitnessANu {

	public HashMap<Integer,LightWeightMember> getAllMembers() {

		HashMap<Integer,LightWeightMember> allMembers = new HashMap<Integer,LightWeightMember>();
		try	{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:Excel1");
			Statement st = con.createStatement();
			System.out.println(con);
			ResultSet rs = st.executeQuery("Select * from [member$]");

			
			Connection oracleConn = OrclConnection.getOrclConnection();
			Statement delStmnt = oracleConn.createStatement();
			delStmnt.executeQuery("DELETE from MEMBER");
			delStmnt.close();
			
			
			int counter=1;
			int memberID = 1;
			String name = "";
			Date dob = null;
			long mobNo = 0l;
		
			
			while (rs.next() ) {
counter++;
					try	{
						name = rs.getString(1).trim();
								System.out.println("Name : "+name);
						dob = rs.getDate(2);
						
						mobNo = rs.getLong(3);
					}catch(Exception e){
						System.out.println("Prob........................." + e.getMessage());
						//break;
					}
					//System.out.println("\n"+memberID+ ": " +name +" : "+dob+" : "+mobNo );	

					LightWeightMember temp = new LightWeightMember(memberID, name, dob,true, ""+mobNo);
					allMembers.put(memberID, temp);
					memberID++;
			}
				System.out.println("Counter : "+(counter));
			st.close();
			con.close();

		} catch (Exception ex) {
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
		}
		return allMembers;
	}
		private static ProFitnessANu pObj;
	
		public static ProFitnessANu getObject() {
			if(pObj==null)
				return new ProFitnessANu();
			else
				return pObj;
		}

		/*
		 * Retrieve Member Data from MEMBER EXCEL SHEET
		 * Return Value : ResultSet rs
		 */
		public static ResultSet getMonthData() throws Exception {
			
			int counter = 1;
			ResultSet rs =  null;
			try	{
				
				/* Retrieve Member INFO */
				
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
				Connection con = DriverManager.getConnection("jdbc:odbc:Excel2");
				Statement st = con.createStatement();
				System.out.println(con);
				rs = st.executeQuery("Select * from [month$] where Rec IS NOT NULL");

				//ResultSetMetaData rsmd = rs.getMetaData();
	
/*				while (rs.next()) {
					counter++;
//					System.out.println(rs.getInt(1)+ " "+rs.getInt(2)+ " "+rs.getString(3));
				}
*/				
				System.out.println("Counter:"+counter);
			}catch (Exception ex) {
				System.err.print("Exception: ");
				System.err.println(ex.getMessage());
			}
			
			return rs;
			
		}
		
		
		
		public static void main(String[] args) throws Exception {

			/* Insert into MEMBER Table from EXCEL WORKBOOK*/
			System.out.println("HETE.........");
			HashMap<Integer,LightWeightMember> allMembers = getObject().getAllMembers();
			System.out.println("SIZE OF HASH MAP : "+allMembers.size());
			MemberImpl member = MemberImpl.getmemberImpl();
			int i = 0;
			
			
			for(LightWeightMember temp : allMembers.values()){
				//System.out.println(temp.getMemberId());
				try{
					member.addLightMember(temp);
				}catch(Exception e){
					System.out.println(temp.getMemberId());
				}

			}
		
	//		 Retrieve Month Data from EXCEL WORKBOOK 
			 
			ResultSet rs;
			rs = getMonthData();
	//	     Insert into REGINFO Database 
			if ( rs != null){

					addRegistrationInfo(rs);
			}else
			{
				System.out.println("Cannot retrieve data from MONTH");
			}
		
		
		}

		/*
		 * Insert MEMBER info REGINFO
		 * @param
		 * rs =  Resultset of MEMBER INFO
		 *  
		 */
		private static void addRegistrationInfo(ResultSet rs) {
			
			Connection oracleConn = OrclConnection.getOrclConnection();
			String sql = " insert into REGISTRATIONINFO (MEMBERID,PLANID,RECEIPTNO,STARTDATE,ENDDATE,REASON,PAIDAMT) values  (?,?,?,?,?,?,?) ";
			PreparedStatement preStatement	= null, stmnt = null;
		//	ResultSet rs1 = null;
			
			/* Attributes*/
			int cnt = 1;
			int rec = -1, mid = -1, planId = -1, amt=-1;
			String name = null;
			String reason = null;
			Date startDate = null, endDate = null;
			
			
			try {
			      /* DELETE PREV TABLE DATA*/
				 Statement delStmnt = oracleConn.createStatement();
				 delStmnt.executeQuery("DELETE from REGISTRATIONINFO");  
				 delStmnt.close();
				
				while(rs.next()){
						cnt++;
						rec = rs.getInt(1);
						mid = rs.getInt(2);
						name = rs.getString(3).trim();
						reason = rs.getString(4);
						amt = rs.getInt(5);
						startDate = rs.getDate(6);
						endDate = rs.getDate(7);
						planId = getPlanId(amt);
						
				 /* If MEMBERID  = 0 retreive from MEMBER TABLE */		
				 if( mid == 0){
					 try {
						 stmnt = oracleConn.prepareStatement("select MEMBERID from MEMBER where NAME=?");
						 stmnt.setString(1, name);
						 ResultSet rs1 = stmnt.executeQuery();
						 rs1.next();
						 mid = rs1.getInt(1);
						 rs1.close();
						 stmnt.close();
					} catch (Exception e) {
						System.out.println(e.getMessage());
						System.out.println("Name = "+name);
											
					}
				 }
					 /* INSERT INTO REGINFO TABLE*/
 					 preStatement = oracleConn.prepareStatement(sql);
					 preStatement.setInt(1, mid);
					 preStatement.setInt(2, planId);
					 preStatement.setInt(3, rec);
					 preStatement.setDate(4, _3sqDate.utilDateToSqlDate(startDate));
					 preStatement.setDate(5, _3sqDate.utilDateToSqlDate(endDate));
					 preStatement.setString(6, reason);
					 preStatement.setInt(7, amt);
					 
				if(mid !=0){	 
					 if(preStatement.executeUpdate()>0){
//						 System.out.println("Inserted into REGINGO successfully");
					 }else
					 {
//						 System.out.println("Failed to insert into REGINFO");
					 }
					 preStatement.close();
				}	 
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println(cnt);
			}
			
		}


		/*
		 * Decides PLAN ID from AMT PAID
		 */
		private static int getPlanId(int amt) {
			
			switch (amt) {
			case 500:
			case 550:
						return 1;
			case 700:
			case 750:
						return 2;
			case 400:
			case 450:
						return 3;
			case 600:
			case 650:
						return 4;
			case 1100:
			case 1300:
						return 5;
			case 1700:
			case 1900:
						return 6;
			case 1000:
			case 1200:
						return 7;
			case 1600:
			case 1800:
						return 8;

			default:
						return -1;
			}
			
		}
		
		
		

	}
