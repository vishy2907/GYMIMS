/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MemberDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.datatransporter.ProFitness;
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

	private HashMap<Integer, LightWeightMember> m_hmAllMembers;

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
					"EMERGENCYCONTACTNO,REGISTRATIONDATE,IMAGE,GENDER ) values  (?,?,?,?,?,?,?,?,?,?,?,?) ";

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
			rs = preStatement.executeQuery();			

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
		return false;
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
	 * 
	 * @return HashMap
	 */
	public HashMap<Integer, LightWeightMember> getAllMemberDetails()
	{
		return ProFitness.getObject().getAllMembers();
	}

	/**
	 * use : At the start of application this will return the All Light Weight Member Information
	 * @return 
	 */
	public HashMap<Integer, LightWeightMember> loadPartialMembers(String whereClause)
	{
		//This logic is to fill the all members map only once...
		PreparedStatement preStatement = null;
		ResultSet rs	= null;
		if(m_hmAllMembers == null)	
		{
			m_hmAllMembers = new HashMap<Integer, LightWeightMember>();
			Connection oracleConn = OrclConnection.getOrclConnection();

			StringBuffer sql = new StringBuffer(" Select MEMBERID,NAME,DATEOFBIRTH FROM MEMBER WHERE IS_ACTIVE IS NULL "); 
			if(whereClause!=null)
				sql.append(" AND ").append(whereClause);
			
			sql.append(" ORDER BY MEMBERID ");

			try {
				preStatement = oracleConn.prepareStatement(sql.toString());
				rs = preStatement.executeQuery();

				int i = 0;
				while (rs.next()) {
					int mId = rs.getInt("MEMBERID");
					String name = rs.getString("NAME");
					Date dob = _3sqDate.sqlDateToUtilDate(rs.getDate("DATEOFBIRTH"));
					boolean isActive = true;
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
			String sql = " Select COUNT(MEMBERID) FROM MEMBER ";
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