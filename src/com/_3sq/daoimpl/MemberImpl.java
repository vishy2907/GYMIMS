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
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MemberDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.Member;
import com._3sq.util._3sqDate;

/**
 * @author Vishal B
 *
 */
public class MemberImpl implements MemberDAO {


	private static MemberImpl m_miMemberImpl;

	private MemberImpl()	{
	}

	public static MemberImpl getmemberImpl() {
		if (m_miMemberImpl == null) {
			synchronized (MemberImpl.class) {
				if (m_miMemberImpl == null) {
					m_miMemberImpl = new  MemberImpl();
				}
			}
		}
		return m_miMemberImpl;
	}

	private TreeMap<Integer, LightWeightMember> m_hmAllMembers;

	/**
	 * @author Pradip K
	 */
	public boolean addLightMember(LightWeightMember member) throws SQLException {

		Connection oracleConn = OrclConnection.getOrclConnection();
		String sql = " insert into MEMBER (MEMBERID,NAME,DATEOFBIRTH,CONTACTNUMBER) values  (?,?,?,?) ";
		PreparedStatement preStatement	= null;
		ResultSet rs = null;
		try {
			preStatement = oracleConn.prepareStatement(sql);		

			preStatement.setInt(1,member.getMemberId());
			preStatement.setString(2, member.getMemberName());
			preStatement.setDate(3, _3sqDate.utilDateToSqlDate(member.getDateOfBirth()));
			preStatement.setString(4, member.getMobileNumber());

			rs = preStatement.executeQuery();			

			preStatement.close();
			rs.close();
			return true;

		} catch (Exception e) {
			System.out.println("MemberImple.java: AddMember() : ");
			e.printStackTrace();
		}
		finally	{
			if(preStatement!=null)
				preStatement.close();
			if(rs!=null)
				rs.close();
		}
		return false;
	}

	public boolean addMember(Member member) {
		//logical insertion : 
		LightWeightMember mMember = new  LightWeightMember();
		mMember.setMemberId(member.getMemberID());
		mMember.setMobileNumber(""+member.getContactNumber());
		mMember.setMemberName(member.getMemberName());
		mMember.setDateOfBirth(member.getDateOfBirth());

		//physical insertion
		PreparedStatement preStatement	= null;
		ResultSet rs = null;
		try {

			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into MEMBER (MEMBERID,NAME,ADDRESS,CONTACTNUMBER,DATEOFBIRTH,BLOODGROUP,OCCUPATION,MEDICALHISTORY," +
					"EMERGENCYCONTACTNO,REGISTRATIONDATE,IMAGE,GENDER,IS_ACTIVE ) values  (?,?,?,?,?,?,?,?,?,?,?,?,?) ";

			preStatement = oracleConn.prepareStatement(sql);



			preStatement.setInt(1,member.getMemberID());
			preStatement.setString(2, member.getMemberName());
			preStatement.setString(3, member.getMemberAddress());
			preStatement.setLong(4, member.getContactNumber());

			preStatement.setDate(5,_3sqDate.utilDateToSqlDate(member.getDateOfBirth()));

			preStatement.setString(6, member.getBloodGroup());
			preStatement.setString(7, member.getOccupation());
			preStatement.setString(8, member.getMedicalHistory());
			preStatement.setLong(9, member.getEmergencyContactNo());

			preStatement.setDate(10, _3sqDate.utilDateToSqlDate(member.getRegistrationDate()));     		

			preStatement.setObject(11, member.getImage());
			preStatement.setString(12, member.getGender());
			preStatement.setInt(13, 0);
			
			rs = preStatement.executeQuery();			
			System.out.println("New Member Added...");

			if(rs!=null)
				return true;
			else
				return false;

			
		} catch (Exception e) {
			System.out.println("MemberImpl.java: AddMember() : ");
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

	/**
	 * @author PRADIP K
	 */
	public boolean removeMember(Member member) {
		PreparedStatement preStatement ;
		//physical deletion

		try {
			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " DELETE FROM MEMBER WHERE MEMBERID = ?";

			preStatement = oracleConn.prepareStatement(sql);

			preStatement.setInt(1,member.getMemberID()); //remove record no = 1 

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
			preStatement.setDate(4,  _3sqDate.utilDateToSqlDate(member.getDateOfBirth()));
			preStatement.setString(5, member.getBloodGroup());
			preStatement.setString(6, member.getOccupation());
			preStatement.setString(7, member.getMedicalHistory());
			preStatement.setLong(8, member.getEmergencyContactNo());
			preStatement.setDate(9,  _3sqDate.utilDateToSqlDate(member.getRegistrationDate()));
			//preStatement.setObject(10, member.getImage());
			preStatement.setString(11, member.getGender());

			preStatement.setInt(12, member.getMemberID());   // Update recordNo = 3
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
		//memberImpl.loadPartialMembers();
		int id=memberImpl.getNextMemberID();
		System.out.print("Member Return ID"+id);
	}

	/**
	 * use : At the start of application this will return the All Light Weight Member Information
	 * @return 
	 */
	public TreeMap<Integer, LightWeightMember> loadPartialMembers(String nameToBeSearched)
	{
		//This logic is to fill the all members map only once...
		PreparedStatement preStatement = null;
		System.out.println("Where clause : "+nameToBeSearched);
		ResultSet rs	= null;
		if(m_hmAllMembers == null || nameToBeSearched!=null)	
		{
			m_hmAllMembers = new TreeMap<Integer, LightWeightMember>();
			Connection oracleConn = OrclConnection.getOrclConnection();

			StringBuffer sql = new StringBuffer();
			
			 
			if(nameToBeSearched!=null)	{
				sql.append("Select MEMBERID,NAME,DATEOFBIRTH,IS_ACTIVE FROM MEMBER WHERE IS_ACTIVE = -1");
				sql.append(" AND LOWER(NAME) LIKE LOWER(\'%"+nameToBeSearched+"%\') ");
			}
			else
			{
				sql.append("Select MEMBERID,NAME,DATEOFBIRTH,IS_ACTIVE FROM MEMBER WHERE IS_ACTIVE = 0");
			}
			sql.append(" ORDER BY MEMBERID");
//			
			try {
				preStatement = oracleConn.prepareStatement(sql.toString());
//				if(nameToBeSearched!=null)
//					preStatement.setString(1,nameToBeSearched);
				rs = preStatement.executeQuery();

				while (rs.next()) {
					int mId = rs.getInt("MEMBERID");
					String name = rs.getString("NAME");
					Date dob = _3sqDate.sqlDateToUtilDate(rs.getDate("DATEOFBIRTH"));
					boolean isActive = rs.getInt("IS_ACTIVE") != -1? true : false;
					LightWeightMember temp = new LightWeightMember(mId, name,dob,isActive, "");
					m_hmAllMembers.put(mId, temp);
				}
				preStatement.close();
				rs.close();
			} catch (Exception e) {
				System.out.println("MemberImple.java: loadPartialMember() : ");
				e.printStackTrace();
			}
		}
		return m_hmAllMembers;
	}

	@Override
	public Member getMember(int memberId) {
		//This logic is to fill the all members map only once...
		PreparedStatement preStatement = null;
		ResultSet rs	= null;
		Connection oracleConn = OrclConnection.getOrclConnection();

		Member member = null;
		String sql = " Select * FROM MEMBER WHERE MEMBERID = "+memberId;

		try {
			preStatement = oracleConn.prepareStatement(sql);
			rs = preStatement.executeQuery();

			int i = 0;
			while (rs.next()) {
				member = new Member();

				int memId = rs.getInt("MEMBERID");
				member.setMemberID(memId);

				String name = rs.getString("NAME");
				member.setMemberName(name);

				String address = rs.getString("ADDRESS");
				if(address!=null)
					member.setMemberAddress(address);
				else
					member.setMemberAddress("");

				String conNumber = rs.getString("CONTACTNUMBER");
				if(conNumber != null)
					member.setContactNumber(Long.parseLong(conNumber));
				else
					member.setContactNumber(0l);

				Date dob = _3sqDate.sqlDateToUtilDate(rs.getDate("DATEOFBIRTH"));
				if(dob!=null)
					member.setDateOfBirth(dob);
				else
					member.setDateOfBirth(null);

				String bg = rs.getString("BLOODGROUP");
				if(bg!=null)
					member.setBloodGroup(bg);
				else
					member.setBloodGroup("");

				String occupation = rs.getString("OCCUPATION");
				if(occupation!=null)
					member.setOccupation(occupation);
				else
					member.setOccupation("");

				String medHist = rs.getString("MEDICALHISTORY");
				if(medHist!=null)
					member.setMedicalHistory(medHist);
				else
					member.setMedicalHistory("");

				String emergContact = rs.getString("EMERGENCYCONTACTNO");
				if(emergContact!=null)
					member.setEmergencyContactNo(Long.parseLong(emergContact));
				else
					member.setEmergencyContactNo(0l);

				Date regDate = _3sqDate.sqlDateToUtilDate(rs.getDate("REGISTRATIONDATE"));
				if(regDate!=null)
					member.setRegistrationDate(regDate);
				else
					member.setRegistrationDate(null);

				Object image = rs.getBlob("IMAGE" );
				if(image!=null)
					member.setImage(image);
				else
					member.setImage(null);

				String gen = rs.getString("GENDER");
				if(gen!=null)
					member.setGender(gen);
				else
					member.setGender("Male");
			}
			preStatement.close();
			rs.close();

		} catch (Exception e) {
			System.out.println("MemberImple.java: getMember() : ");
			e.printStackTrace();
		}

		return member;
	}
	public int getNextMemberID()
	{
		Connection oracleConn = OrclConnection.getOrclConnection();
		Statement st=null;
		ResultSet rs=null;
		int temp=1;
		try {
			st=oracleConn.createStatement();
			String sql = " Select MAX(MEMBERID) FROM MEMBER ";
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
	public boolean activateMembership(int memberId) {
		Connection oracleConn = OrclConnection.getOrclConnection();
		PreparedStatement preStatement	= null;
		boolean isUpdated = false;	
		try {
			String sql = " UPDATE MEMBER SET IS_ACTIVE = 0 where MEMBERID = "+memberId;
			preStatement = oracleConn.prepareStatement(sql);
			preStatement.executeUpdate(sql);
			isUpdated = true;	
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally
		{
			try {
				preStatement.close();
			} catch (SQLException e) {
			}

		}
		return isUpdated;
	}
	
	public Collection<Member> retrieveAllMembersForSerialization	()	{
		Collection<Member> allMembers = new ArrayList<Member>();
		
		PreparedStatement preStatement = null;
		ResultSet rs	= null;
		Connection oracleConn = OrclConnection.getOrclConnection();

		Member member = null;
		String sql = " Select * FROM MEMBER ORDER BY MEMBERID";

		try {
			preStatement = oracleConn.prepareStatement(sql);
			rs = preStatement.executeQuery();

			while (rs.next()) {
				member = new Member();

				int memId = rs.getInt("MEMBERID");
				member.setMemberID(memId);

				String name = rs.getString("NAME");
				member.setMemberName(name);

				String address = rs.getString("ADDRESS");
				if(address!=null)
					member.setMemberAddress(address);
				else
					member.setMemberAddress("");

				String conNumber = rs.getString("CONTACTNUMBER");
				if(conNumber != null)
					member.setContactNumber(Long.parseLong(conNumber));
				else
					member.setContactNumber(0l);

				Date dob = _3sqDate.sqlDateToUtilDate(rs.getDate("DATEOFBIRTH"));
				if(dob!=null)
					member.setDateOfBirth(dob);
				else
					member.setDateOfBirth(null);

				String bg = rs.getString("BLOODGROUP");
				if(bg!=null)
					member.setBloodGroup(bg);
				else
					member.setBloodGroup("");

				String occupation = rs.getString("OCCUPATION");
				if(occupation!=null)
					member.setOccupation(occupation);
				else
					member.setOccupation("");

				String medHist = rs.getString("MEDICALHISTORY");
				if(medHist!=null)
					member.setMedicalHistory(medHist);
				else
					member.setMedicalHistory("");

				String emergContact = rs.getString("EMERGENCYCONTACTNO");
				if(emergContact!=null)
					member.setEmergencyContactNo(Long.parseLong(emergContact));
				else
					member.setEmergencyContactNo(0l);

				Date regDate = _3sqDate.sqlDateToUtilDate(rs.getDate("REGISTRATIONDATE"));
				if(regDate!=null)
					member.setRegistrationDate(regDate);
				else
					member.setRegistrationDate(null);

				Object image = rs.getBlob("IMAGE" );
				if(image!=null)
					member.setImage(image);
				else
					member.setImage(null);

				String gen = rs.getString("GENDER");
				if(gen!=null)
					member.setGender(gen);
				else
					member.setGender("Male");
				

				int isActiveStatus = rs.getInt("IS_ACTIVE");
				member.setActiveFlag(isActiveStatus);
				
				int weight = rs.getInt("WEIGHT");
				member.setWeight(weight);
				
				
				//Add member to the LIst...
				allMembers.add(member);
			}
			preStatement.close();
			rs.close();

		} catch (Exception e) {
			System.out.println("MemberImple.java: retrieveAllMembersForSerialization() : ");
			e.printStackTrace();
		}
		return allMembers;
	}
	
	public void addDeserializedMembers(Collection<Member> allMembers)	{
		if(allMembers == null || allMembers.size() == 0)
			return;
		
		PreparedStatement preStatement	= null;
		try {

			Connection oracleConn = OrclConnection.getOrclConnection();	
			String sql = " insert into MEMBER (MEMBERID,NAME,ADDRESS,CONTACTNUMBER,DATEOFBIRTH,BLOODGROUP,OCCUPATION,MEDICALHISTORY," +
					"EMERGENCYCONTACTNO,REGISTRATIONDATE,IMAGE,GENDER,IS_ACTIVE,WEIGHT ) values  (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";

			preStatement = oracleConn.prepareStatement(sql);
			
			
			for(Member member : allMembers){

				preStatement.setInt(1,member.getMemberID());
				preStatement.setString(2, member.getMemberName());
				preStatement.setString(3, member.getMemberAddress());
				preStatement.setLong(4, member.getContactNumber());

				preStatement.setDate(5,_3sqDate.utilDateToSqlDate(member.getDateOfBirth()));

				preStatement.setString(6, member.getBloodGroup());
				preStatement.setString(7, member.getOccupation());
				preStatement.setString(8, member.getMedicalHistory());
				preStatement.setLong(9, member.getEmergencyContactNo());

				preStatement.setDate(10, _3sqDate.utilDateToSqlDate(member.getRegistrationDate()));     		

				preStatement.setObject(11, member.getImage());
				preStatement.setString(12, member.getGender());
				
				preStatement.setInt(13, member.getActiveFlag());
				preStatement.setInt(14,member.getWeight());
				
				//Add the job to batch... :)
				preStatement.addBatch();
			}

			preStatement.executeBatch();		//Execute whole batch at a time...
			
		} catch (Exception e) {
			System.out.println("MemberImpl.java: addDeserializedMember() : ");
			e.printStackTrace();
		}
		finally	{
			try	{
				if(preStatement!=null)
					preStatement.close();
			}catch(SQLException e)	{
				e.printStackTrace();
			}
		}

	}
}