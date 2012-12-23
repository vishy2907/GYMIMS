package com._3sq.daos;

import java.util.HashMap;

import com._3sq.domainobjects.Member;
/**
 * 
 * @author Vishalb
 * DAO class for Member Information
 */

public interface MemberDAO {
	public boolean AddMember(Member member);
	public boolean RemoveMember(Member member);
	public boolean updateMember(Member member);
	
	public HashMap<Integer,String> getAllMemberNamesWithIDs();
	
}