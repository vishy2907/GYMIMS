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
	public boolean addMember(Member member);
	public boolean removeMember(Member member);
	public boolean updateMember(Member member);
	public Member getMember(int memberId);
	
	
	public HashMap<Integer, LightWeightMember> loadPartialMembers();

	
}
