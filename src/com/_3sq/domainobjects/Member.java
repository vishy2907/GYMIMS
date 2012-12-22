 /** 
 * 
 */
package com._3sq.domainobjects;

import java.util.Date;

/**
 * @author Vishal b.
 * Date : 22nd Dec 2012
 * @version 1.0
 * Use : Domain Object Class For Members of the gym
 * Naming Convention : m_ represents Class member  
 */
public class Member {
	private int 			m_MemberID;
	private String 			m_MemberName;
	private String 			m_MemberAddress;
	private long 			m_ContactNumber;
	private Date 			m_DateOfBirth;
	private String 			m_BloodGroup;	//can be later converted into the enum - bloodgroup
	private String 			m_Occupation;
	private String 			m_MedicalHistory;
	private String 			m_Gender;		//can be later converted into the enum - bloodgroup
	/**
	 * @return MemberID for selected member
	 */
	public int getMemberID() {
		return m_MemberID;
	}
	/**
	 * Set the Member If for Selected Member  
	 * @param MemberID
	 */
	public void setM_MemberID(int MemberID) {
		this.m_MemberID = MemberID;
	}
	
	/**
	 * 
	 * @return Member Name for Selected Member
	 */
	public String getMemberName() {
		return m_MemberName;
	}
	
	/**
	 * Set Member Name for Selected Member
	 * @param MemberName
	 */
	public void setMemberName(String MemberName) {
		this.m_MemberName = MemberName;
	}

	/**
	 * 	@return member address for Selected Member
	 */
	public String getMemberAddress() {
		return m_MemberAddress;
	}
	
	/**
	 * Set member address for Selected Member
	 * @param m_MemberAddress
	 */
	public void setMemberAddress(String MemberAddress) {
		this.m_MemberAddress = MemberAddress;
	}
	
	/**
	 * 
	 * @return contact number for Selected Member
	 */
	public long getContactNumber() {
		return m_ContactNumber;
	}
	
	/**
	 * Set contact number for Selected Member
	 * @param ContactNumber
	 */
	public void setContactNumber(long ContactNumber) {
		this.m_ContactNumber = ContactNumber;
	}
	
	/**
	 * 
	 * @return DateOfBirth for Selected Member
	 */
	public Date getDateOfBirth() {
		return m_DateOfBirth;
	}
	
	/**
	 * Set DateOfBirth for Selected Member
	 * @param DateOfBirth
	 */
	public void setDateOfBirth(Date DateOfBirth) {
		this.m_DateOfBirth = DateOfBirth;
	}
	
	/**
	 * 
	 * @return blood group for Selected Member
	 */
	public String getBloodGroup() {
		return m_BloodGroup;
	}
	
	/**
	 * Set the bloof group for Selected Member 
	 * @param BloodGroup
	 */
	public void setBloodGroup(String m_BloodGroup) {
		this.m_BloodGroup = m_BloodGroup;
	}
	
	/**
	 * 
	 * @return Occupation for Selected Member
	 */
	public String getOccupation() {
		return m_Occupation;
	}
	
	/**
	 * Set the occupation for the member
	 * @param Occupation
	 */
	public void setOccupation(String Occupation) {
		this.m_Occupation = Occupation;
	}
	
	/**
	 * 
	 * @return the medical history of member, which is specified at the time of joining gym
	 */
	public String getMedicalHistory() {
		return m_MedicalHistory;
	}
	
	/**
	 * 
	 * @return gender for selected member
	 */
	public String getGender() {
		return m_Gender;
	}
	
	/**
	 * Sets the Gender for member
	 * @param m_Gender
	 */
	public void setGender(String Gender) {
		this.m_Gender = Gender;
	}
	/**
	 * set the Medical History for Member
	 * @param m_MedicalHistory
	 */
	
	public void setMedicalHistory(String m_MedicalHistory) {
		this.m_MedicalHistory = m_MedicalHistory;
	}
}
