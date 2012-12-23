/**
 * 
 */
package com._3sq.domainobjects;

/**
 * @author Vishal B
 * Domain object for Storing the Gym Plans 
 */
/**
 * @author shani
 *
 */
public class GymPlan {
	private int				m_iPlanID;
	private String 			m_sPlanName;
	private int				m_iFees;
	
	
	
	public int getPlanID() {
		return m_iPlanID;
	}
	public void setPlanID(int planID) {
		this.m_iPlanID = planID;
	}
	public String getPlanName() {
		return m_sPlanName;
	}
	public void setPlanName(String planName) {
		this.m_sPlanName = planName;
	}
	public int getFees() {
		return m_iFees;
	}
	public void setFees(int fees) {
		this.m_iFees = fees;
	}
	
	
}	
