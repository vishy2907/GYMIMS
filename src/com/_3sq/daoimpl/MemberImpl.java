/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MemberDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.datatransporter.ProFitness;
import com._3sq.domainobjects.Member;

/**
 * @author Vishal B
 *
 */
public class MemberImpl implements MemberDAO {

	
	private static MemberImpl m_miMemberImpl;

	private MemberImpl()	{
		
	}
	
	public static MemberImpl getmemberImpl() {
		if (m_miMemberImpl==null)
			return new MemberImpl();
		else
			return m_miMemberImpl;
	}
	
	private HashMap<Integer, LightWeightMember> m_hmAllMembers;
	
	/**
	 * @author Pradip K
	 */
	public boolean addLightMember(LightWeightMember member) {
		
		Connection oracleConn = OrclConnection.getOrclConnection();
		String sql = " insert into MEMBER (MEMBERID,NAME,DATEOFBIRTH,CONTACTNUMBER) values  (?,?,?,?) ";
		PreparedStatement preStatement;
		ResultSet rs ;
		try {
			
			
			preStatement = oracleConn.prepareStatement(sql);		
			
			preStatement.setInt(1,member.getMemberId());
     		preStatement.setString(2, member.getMemberName());
     		preStatement.setString(3, member.getDateOfBirth());
     		preStatement.setString(4, member.getMobileNumber());
    
     		 rs = preStatement.executeQuery();			
			
     		preStatement.close();
     		rs.close();
     		
     		if(rs!=null)
     			return true;
     		else
     			return false;
			
			
		} catch (Exception e) {
			System.out.println("MemberImple.java: AddMember() : ");
			e.printStackTrace();
		}

		
		return false;
	}
	
	public boolean addMember(Member member) {
		//logical insertion : 
		
		LightWeightMember mMember = new  LightWeightMember();
		mMember.setMemberId(member.getMemberID());
		mMember.setMobileNumber(""+member.getContactNumber());
		mMember.setMemberName(member.getMemberName());
		mMember.setDateOfBirth(member.getDateOfBirth().toLocaleString().substring(0, 10));
		m_hmAllMembers.put(member.getMemberID(), mMember);
		
		//physical insertion
		
		try {
			
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into MEMBER (MEMBERID,NAME,ADDRESS,CONTACTNUMBER,DATEOFBIRTH,BLOODGROUP,OCCUPATION,MEDICALHISTORY," +
					"EMERGENCYCONTACTNO,REGISTRATIONDATE,IMAGE,GENDER ) values  (?,?,?,?,?,?,?,?,?,?,?,?) ";
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			
			
			preStatement.setInt(1,member.getMemberID());
     		preStatement.setString(2, member.getMemberName());
     		preStatement.setString(3, member.getMemberAddress());
     		preStatement.setLong(4, member.getContactNumber());
    
     		//Conversion Logic For Date
     		//Java Default Dae Format : MM/DD/YYYY
     		//So, store it first with date.getTime() : which is of long datatype
     		     		preStatement.setString(5,""+  member.getDateOfBirth().getTime());
     		
     		preStatement.setString(6, member.getBloodGroup());
     		preStatement.setString(7, member.getOccupation());
     		preStatement.setString(8, member.getMedicalHistory());
     		preStatement.setLong(9, member.getEmergencyContactNo());
     		
     		preStatement.setString(10, ""+member.getRegistrationDate().getTime());     		
     		
     		preStatement.setObject(11, member.getImage());
     		preStatement.setString(12, member.getGender());
     		ResultSet rs = preStatement.executeQuery();			
			
     		if(rs!=null)
     			return true;
     		else
     			return false;
					
		} catch (Exception e) {
			System.out.println("MemberImpl.java: AddMember() : ");
			e.printStackTrace();
		}

		
		return false;
	}

	
	
	
	
	/**
	 * @author PRADIP K
	 */
	public boolean removeMember(Member member) {
		//logical deletion
		m_hmAllMembers.remove(member.getMemberID());
		
		//physical deletion
		
		try {
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " DELETE FROM MEMBER WHERE MEMBERID = ?";
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			preStatement.setInt(1,1); //remove record no = 1 
     		     		
			preStatement.executeQuery();			
		
			preStatement.close();	
					
		} catch (Exception e) {
			System.out.println("MemberImpl.java: removeMember() : ");
			e.printStackTrace();
		}
		
		
		return false;
	}

	
	
	
	/**
	 * @author PRADIP K
	 */
	
	public boolean updateMember(Member member) {
			// TODO Auto-generated method stub
		//logical update
 
		LightWeightMember mMember = m_hmAllMembers.get(member.getMemberID());
		mMember.setMobileNumber(""+member.getContactNumber());
		mMember.setMemberName(member.getMemberName());
		mMember.setDateOfBirth(member.getDateOfBirth().toLocaleString().substring(0, 10));
		
		//physical update
		try {
			
	
			Connection oracleConn = OrclConnection.getOrclConnection();	

			
			String sql = " update MEMBER set NAME = ? ,ADDRESS = ?, CONTACTNUMBER = ? ,DATEOFBIRTH = ? ," +
					"BLOODGROUP = ? ,OCCUPATION = ? ,MEDICALHISTORY = ? ,EMERGENCYCONTACTNO = ?, REGISTRATIONDATE = ?," +
					"IMAGE=? , GENDER = ?  where MEMBERID = ? ";
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			
			preStatement.setString(1, member.getMemberName());
     		preStatement.setString(2, member.getMemberAddress());
     		preStatement.setLong(3, member.getContactNumber());
     		preStatement.setString(4,  ""+member.getDateOfBirth().getTime());
     		preStatement.setString(5, member.getBloodGroup());
     		preStatement.setString(6, member.getOccupation());
     		preStatement.setString(7, member.getMedicalHistory());
     		preStatement.setLong(8, member.getEmergencyContactNo());
     		preStatement.setString(9,  ""+member.getRegistrationDate().getTime());
     		preStatement.setObject(10, member.getImage());
     		preStatement.setString(11, member.getGender());
     		preStatement.setInt(12, 3);   // Update recordNo = 3
      		
     		
     		
			preStatement.executeQuery();			
			
					
		} catch (Exception e) {
			System.out.println("MemberImpl.java: updateMember() : ");
			e.printStackTrace();
		}

		
		return false;
	}

	

	public static void main(String args[]){
	
		//ALL BELOW THINGS ARE FOR TEMPORATILY
		
		MemberImpl memberImpl = MemberImpl.getmemberImpl();
		memberImpl.loadPartialMembers();
		
	}
	
	/**
	 * 
	 * @return HashMap
	 */
	public HashMap<Integer, LightWeightMember> getAllMemberDetails()
	{
		return ProFitness.getObject().getAllMembers();
	}
	
	/**
	 * use : At the start of appliocation this will return the All Light Weight Member Information
	 * @return
	 */
	public HashMap<Integer, LightWeightMember> loadPartialMembers()
	{
		//This logic is to fill the all members map only once...
		if(m_hmAllMembers == null)	
		{
			m_hmAllMembers = new HashMap<Integer, LightWeightMember>();
			Connection oracleConn = OrclConnection.getOrclConnection();
			// This is going to be the important query.
			// have to be optimized...

			String sql = " Select MEMBERID,NAME,DATEOFBIRTH FROM MEMBER";
			PreparedStatement preStatement;
			ResultSet rs;
			try {

				preStatement = oracleConn.prepareStatement(sql);
				rs = preStatement.executeQuery();

				int i = 0;
				while (rs.next()) {
					int mId = rs.getInt("MEMBERID");
					String name = rs.getString("NAME");
					String dob = rs.getString("DATEOFBIRTH");
					LightWeightMember temp = new LightWeightMember(mId, name,
							dob, "");
					System.out.println(i++);
					m_hmAllMembers.put(mId, temp);
				}

				preStatement.close();
				rs.close();

			} catch (Exception e) {
				System.out.println("MemberImple.java: AddMember() : ");
				e.printStackTrace();
			}
		}

		return m_hmAllMembers;
	}

}
