package com._3sq.daos;

import java.util.HashMap;

import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.Member;
/**
 * 
 * @author Vishalb
 * DAO class for Member Information
 */

public interface MemberDAO {
	public boolean addMember(Member member) throws Exception;
	public boolean removeMember(Member member) throws Exception;
	public boolean updateMember(Member member) throws Exception;
	public Member getMember(int memberId) throws Exception;
	public int getNextMemberID() throws Exception;
	
	public HashMap<Integer, LightWeightMember> loadPartialMembers();
	
}
