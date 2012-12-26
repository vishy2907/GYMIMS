/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.HashMap;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MemberDAO;
import com._3sq.domainobjects.Member;

/**
 * @author Vishal B
 *
 */
public class MemberImpl implements MemberDAO {

	/**
	 *
	 * @author Pradip K
	 */
	public boolean addMember(Member member) {
		// TODO Auto-generated method stub
		
	
		try {
			
	
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into MEMBER (MEMBERID,MEMBERNAME,MEMBERADDRESS,CONTACTNUMBER,DATEOFBIRTH,BLOODGROUP,OCCUPATION,MEDICALHISTORY,GENDER,EMERGENCYCONTACTNO ) values  (?,?,?,?,?,?,?,?,?,?) ";
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			
			preStatement.setInt(1,member.getMemberID());
     		preStatement.setString(2, member.getMemberName());
     		preStatement.setString(3, member.getMemberAddress());
     		preStatement.setLong(4, member.getContactNumber());
     		preStatement.setString(5,  member.getDateOfBirth());
     		preStatement.setString(6, member.getBloodGroup());
     		preStatement.setString(7, member.getOccupation());
     		preStatement.setString(8, member.getMedicalHistory());
     		preStatement.setString(9, member.getGender());
     		preStatement.setLong(10, member.getEmergencyContactNo());
     		
     		
			preStatement.executeQuery();			
			
					
		} catch (Exception e) {
			System.out.println("MemberDAOinDBImple.java: AddMember() : ");
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
			
			
			preStatement.setInt(1,1);
     		     		
			preStatement.executeQuery();			
			
					
		} catch (Exception e) {
			System.out.println("MemberDAOinDBImple.java: removeMember() : ");
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
			String sql = " update MEMBER set MEMBERNAME = ? ,MEMBERADDRESS = ?, CONTACTNUMBER = ? ,DATEOFBIRTH = ? ,BLOODGROUP = ? ,OCCUPATION = ? ,MEDICALHISTORY = ? ,GENDER = ? ,EMERGENCYCONTACTNO = ? where MEMBERID = ? ";
			
			PreparedStatement preStatement = oracleConn.prepareStatement(sql);
			
			
			preStatement.setString(1, member.getMemberName());
     		preStatement.setString(2, member.getMemberAddress());
     		preStatement.setLong(3, member.getContactNumber());
     		preStatement.setString(4,  member.getDateOfBirth());
     		preStatement.setString(5, member.getBloodGroup());
     		preStatement.setString(6, member.getOccupation());
     		preStatement.setString(7, member.getMedicalHistory());
     		preStatement.setString(8, member.getGender());
     		preStatement.setLong(9, member.getEmergencyContactNo());
     		preStatement.setInt(10, 5);   // Update recordNo = 5
      		
     		
     		
			preStatement.executeQuery();			
			
					
		} catch (Exception e) {
			System.out.println("MemberDAOinDBImple.java: updateMember() : ");
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

	
	public static void main(String args[]){
		
		Member obj = new Member();
		
		obj.setM_MemberID(1);
		obj.setMemberName("Pradip");
		obj.setMemberAddress("Sr No 31/2/8");
	    obj.setContactNumber(9850303441L);
	    obj.setDateOfBirth("10-9-10");
	    obj.setBloodGroup("b+ve");
	    obj.setOccupation("engg");
	    obj.setMedicalHistory("no");
	    obj.setGender("male");
	    obj.setEmergencyContactNo(9923059380L);

		
		
		
		MemberImpl memberDBImpl = new MemberImpl();													
		memberDBImpl.addMember(obj);
		
		
	}
	
	
	
}
