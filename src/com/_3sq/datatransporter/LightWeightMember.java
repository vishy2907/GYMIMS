package com._3sq.datatransporter;

public class LightWeightMember {
	int memberId;
	String memberName;
	String dateOfBirth;
	String mobileNumber;
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
	public String getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	
	public LightWeightMember(int memberId, String memberName,
			String dateOfBirth, String mobileNumber) {
		super();
		this.memberId = memberId;
		this.memberName = memberName;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
	}
	public LightWeightMember()
	{
		
	}
	
	
}
