/**
 * 
 */
package com._3sq.daoimpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import com._3sq.connection.OrclConnection;
import com._3sq.daos.MemberDAO;
import com._3sq.domainobjects.Member;

/**
 * @author Vishal B
 *
 */
public class MemberImpl implements MemberDAO {

	/* (non-Javadoc)
	 * @see com._3sq.daos.MemberDAO#AddMember(com._3sq.domainobjects.Member)
	 */
	@Override
	public boolean addMember(Member member) {
		// TODO Auto-generated method stub
		
	
		try {
			Connection oracleConn = OrclConnection.getOrclConnection();
	
			String query = "Insert into asdasdasd";
			
					
					
		} catch (Exception e) {
			System.out.println("MemberDAOinDBImple.java: AddMember() : ");
			e.printStackTrace();
		}

		
		
		
		
		
		return false;
	}

	
	
	
	
	/* (non-Javadoc)
	 * @see com._3sq.daos.MemberDAO#RemoveMember(com._3sq.domainobjects.Member)
	 */
	@Override
	public boolean removeMember(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	
	
	
	
	
	/* (non-Javadoc)
	 * @see com._3sq.daos.MemberDAO#updateMember(com._3sq.domainobjects.Member)
	 */
	@Override
	public boolean updateMember(Member member) {
		// TODO Auto-generated method stub
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
		obj.setMemberName("Pradip");
		MemberImpl memberDBImpl = new MemberImpl();
		memberDBImpl.addMember(obj);
		
	}
	
	
	
}
