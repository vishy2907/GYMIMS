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
	
	
	
	private float[]				m_fWholeBody;
	private float[] 			m_fTrunk;
	private float[]				m_fLeg;
	private float[]				m_fArm;
	
	
	/**
	 * 
	 * @return 
	 */
	public float[] getWholeBody() {
		return m_fWholeBody;
	}
	
	/**
	 * 
	 * @param wholeBody
	 */

	public void setWholeBody(float[] wholeBody) {
		m_fWholeBody = wholeBody;
	}

	public float[] getTrunk() {
		return m_fTrunk;
	}

	public void setTrunk(float[] trunk) {
		m_fTrunk = trunk;
	}

	public float[] getLeg() {
		return m_fLeg;
	}

	public void setLeg(float[] leg) {
		m_fLeg = leg;
	}

	public float[] getArm() {
		return m_fArm;
	}

	public void setArm(float[] arm) {
		m_fArm = arm;
	}

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
