 /** 
 * 
 */
package com._3sq.domainobjects;

//import java.util.Date;

/**
 * @author Vishal b.
 * Date : 22nd Dec 2012
 * @version 1.0
 * Use : Domain Object Class For Members of the gym
 * Naming Convention : m_ represents Class member  
 * and characters after _ represents the data type 
 */
public class Member {
	private int 			m_iMemberID;
	private String 			m_sMemberName;
	private String 			m_sMemberAddress;
	private long 			m_lContactNumber;
	private String 			m_dDateOfBirth;  //temporarily changed the data type
	private String 			m_sBloodGroup;	//can be later converted into the enum - bloodgroup
	private String 			m_sOccupation;
	private String 			m_sMedicalHistory;
	private String 			m_sGender;		//can be later converted into the enum - Gender
	private long			m_lEmergencyContactNo;

	
	
	public Member(		String 			MemberName, 
						String 			MemberAddress,
						long 			ContactNumber, 
						String 			DateOfBirth, 
						String 			BloodGroup,
						String 			Occupation, 
						String 			MedicalHistory, 
						String 			Gender,
						long 			EmergencyContactNo) 				{
		super();
		this.m_sMemberName = MemberName;
		this.m_sMemberAddress = MemberAddress;
		this.m_lContactNumber = ContactNumber;
		this.m_dDateOfBirth = DateOfBirth;
		this.m_sBloodGroup = BloodGroup;
		this.m_sOccupation = Occupation;
		this.m_sMedicalHistory = MedicalHistory;
		this.m_sGender = Gender;
		this.m_lEmergencyContactNo = EmergencyContactNo;
	}
	
	public Member(){
		
	}




	
	/**
	 * @return MemberID for selected member
	 */
	public int getMemberID() {
		return m_iMemberID;
	}
	
	
	
	/**
	 * Set the Member If for Selected Member  
	 * @param MemberID
	 */
	public void setM_MemberID(int MemberID) {
		this.m_iMemberID = MemberID;
	}
	
	
	
	
	/**
	 * 
	 * @return Member Name for Selected Member
	 */
	public String getMemberName() {
		return m_sMemberName;
	}
	
	
	
	
	/**
	 * Set Member Name for Selected Member
	 * @param MemberName
	 */
	public void setMemberName(String MemberName) {
		this.m_sMemberName = MemberName;
	}

	
	
	
	/**
	 *  	@return member address for Selected Member
	 */
	public String getMemberAddress() {
		return m_sMemberAddress;
	}
	
	
	
	
	/**
	 * Set member address for Selected Member
	 * @param MemberAddress
	 */
	public void setMemberAddress(String MemberAddress) {
		this.m_sMemberAddress = MemberAddress;
	}
	
	
	
	
	/**
	 * 
	 * @return contact number for Selected Member
	 */
	public long getContactNumber() {
		return m_lContactNumber;
	}
	
	
	
	
	/**
	 * Set contact number for Selected Member
	 * @param ContactNumber
	 */
	public void setContactNumber(long ContactNumber) {
		this.m_lContactNumber = ContactNumber;
	}
	
	
	
	
	/**
	 * 
	 * @return DateOfBirth for Selected Member
	 */
	public String getDateOfBirth() {
		return m_dDateOfBirth;
	}
	
	
	
	/**
	 * Set DateOfBirth for Selected Member
	 * @param DateOfBirth
	 */
	public void setDateOfBirth(String DateOfBirth) {
		this.m_dDateOfBirth = DateOfBirth;
	}
	
	
	
	
	/**
	 * 
	 * @return blood group for Selected Member
	 */
	public String getBloodGroup() {
		return m_sBloodGroup;
	}
	
	
	
	
	/**
	 * Set the blood group for Selected Member 
	 * @param BloodGroup
	 */
	public void setBloodGroup(String BloodGroup) {
		this.m_sBloodGroup = BloodGroup;
	}
	
	
	
	
	/**
	 * 
	 * @return Occupation for Selected Member
	 */
	public String getOccupation() {
		return m_sOccupation;
	}
	
	
	
	
	/**
	 * Set the occupation for the member
	 * @param Occupation
	 */
	public void setOccupation(String Occupation) {
		this.m_sOccupation = Occupation;
	}
	
	
	
	
	
	/**
	 * 
	 * @return the medical history of member, which is specified at the time of joining gym
	 */
	public String getMedicalHistory() {
		return m_sMedicalHistory;
	}
	
	
	
	
	/**
	 * 
	 * @return gender for selected member
	 */
	public String getGender() {
		return m_sGender;
	}
	
	
	
	
	/**
	 * Sets the Gender for member
	 * @param Gender
	 */
	public void setGender(String Gender) {
		this.m_sGender = Gender;
	}
	
	
	
	/**
	 * set the Medical History for Member
	 * @param MedicalHistory
	 */
	
	public void setMedicalHistory(String MedicalHistory) {
		this.m_sMedicalHistory = MedicalHistory;
	}
	
	
	
	
	/**
	 * 
	 * @return Emergency contact number for Selected Member
	 */
	public long getEmergencyContactNo() {
		return m_lEmergencyContactNo;
	}
	
	
	
	
	/**
	 * sets the emergency contact number for Selected Member
	 * @param EmergencyContactNo 
	 */
	public void setEmergencyContactNo(long EmergencyContactNo) {
		this.m_lEmergencyContactNo = EmergencyContactNo;
	}
	
}
