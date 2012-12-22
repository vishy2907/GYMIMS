/**
 * 
 */
package com._3sq.domainobjects;

/**
 * @author Vishal b
 * @version 1.0
 *
 */

public class BodyComposition {
	private float 				m_fWeight;
	private int 				m_iFatInPer;
	private int					m_iBodyAge;
	private int 				m_iBMI;
	private int					m_iRM;
	private float 				m_fVisceralFat;
	
	
	//Data types of these can be reviewed afterwoard
	// Confirm with Mahesh and other gym coaches
	//Array of 2 for SF and SM
	//Measurements for the Body Area
	//TODO : generate the getters and setters for them
	
	private String[] 			m_sTrunk;
	private String[]			m_sLeg;
	private String[]			m_sArm;
	
	
	
	public float getWeight() {
		return m_fWeight;
	}
	
	public void setWeight(float weight) {
		m_fWeight = weight;
	}
	
	public int getFatInPer() {
		return m_iFatInPer;
	}
	
	public void setFatInPer(int fatInPer) {
		m_iFatInPer = fatInPer;
	}
	
	public int getBodyAge() {
		return m_iBodyAge;
	}
	
	public void setBodyAge(int bodyAge) {
		m_iBodyAge = bodyAge;
	}
	
	public int getBMI() {
		return m_iBMI;
	}
	public void setBMI(int bMI) {
		m_iBMI = bMI;
	}
	public int getRM() {
		return m_iRM;
	}
	public void setRM(int rM) {
		m_iRM = rM;
	}
	public float getVisceralFat() {
		return m_fVisceralFat;
	}
	public void setVisceralFat(float visceralFat) {
		m_fVisceralFat = visceralFat;
	}
}
