/**
 * 
 */
package com._3sq.util;

import java.io.Serializable;

/**
 * @author VishalB
 * This class will hold the Meta data for the serializers. 
 *
 */
public class SerializerMetaData implements  Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 102617367309563754L;
	private int m_iGymPlanCount;		
	private int m_iMeasurementCount;
	private int m_iMemberCount;
	private int m_iRegistrationCount;			//the monthly membership info..

	public int getGymPlanCount() {
		return m_iGymPlanCount;
	}
	public void setGymPlanCount(int gymPlanCount) {
		m_iGymPlanCount = gymPlanCount;
	}
	public int getMeasurementCount() {
		return m_iMeasurementCount;
	}
	public void setMeasurementCount(int measurementCount) {
		m_iMeasurementCount = measurementCount;
	}
	public int getMemberCount() {
		return m_iMemberCount;
	}
	public void setMemberCount(int memberCount) {
		m_iMemberCount = memberCount;
	}
	public int getRegistrationCount() {
		return m_iRegistrationCount;
	}
	public void setRegistrationCount(int registrationCount) {
		m_iRegistrationCount = registrationCount;
	}
	
	
	@Override
	public String toString() {
		String op = "Members Count "+m_iMemberCount+"\n"+
					"GymPlans Count "+m_iGymPlanCount+"\n"+
					"Measurement Count "+m_iMeasurementCount+"\n"+
					"Registration Count "+m_iRegistrationCount;
	
		return op;
	}
	
	
}
