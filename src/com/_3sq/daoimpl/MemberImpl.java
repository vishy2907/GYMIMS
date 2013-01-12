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

	private MemberImpl()
	{
		
	}
	
	public static MemberImpl getmemberImpl() {
		if (m_miMemberImpl==null)
			return new MemberImpl();
		else
			return m_miMemberImpl;
	}
	
	
	/**
	 *
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
		// TODO Auto-generated method stub
			
		
		try {
			
			
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " DELETE FROM MEMBER WHERE MEMBERID = ?";
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			
			preStatement.setInt(1,1); //remove record no = 1 
     		     		
			preStatement.executeQuery();			
			
					
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

	
	
	
	
	
	
	/* (non-Javadoc)
	 * @see com._3sq.daos.MemberDAO#getAllMemberNamesWithIDs()
	 */
	@Override
	public HashMap<Integer, String> getAllMemberNamesWithIDs() {
		// TODO Auto-generated method stub
		return null;
	}

	
	@SuppressWarnings("deprecation")
	public static void main(String args[]){
		
		
		
		//ALL BELOW THINGS ARE FOR TEMPORATILY
		
		
		
		
		MemberImpl memberImpl = MemberImpl.getmemberImpl();
		HashMap<Integer, LightWeightMember>allMembers= memberImpl.getAllMemberDetails();
		
		for(Integer memberId : allMembers.keySet())
		{
			System.out.println("Member : "+memberId);
			memberImpl.addLightMember(allMembers.get(memberId));
		}
		
		//memberDBImpl.removeMember(obj);
		//memberDBImpl.updateMember(obj);
		
	}
	
	public HashMap<Integer, LightWeightMember> getAllMemberDetails()
	{
		return ProFitness.getObject().getAllMembers();
	}
	
	
	
}
