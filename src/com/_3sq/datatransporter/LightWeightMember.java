package com._3sq.datatransporter;

import java.util.Date;

public class LightWeightMember {
	int memberId;
	String memberName;
	Date dateOfBirth;
	String mobileNumber;
	boolean isActive;				// null means active member and -1 means membership active
	
	public boolean isMemberActive() {
		return isActive;
	}
	/**
	 * 
	 * @param isActive if true, membership is in iactive state, else inactive
	 */
	public void setMembershipStatus(boolean isActive) {
		this.isActive= isActive;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public LightWeightMember(int memberId, String memberName,
			Date dateOfBirth,boolean isActive, String mobileNumber) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.dateOfBirth = dateOfBirth;
		this.isActive = isActive;
		this.mobileNumber = mobileNumber;
	}
	public LightWeightMember()
	{
		
	}
	
	
}
