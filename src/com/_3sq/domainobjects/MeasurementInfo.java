 /**
 * 
 */
package com._3sq.domainobjects;

import java.util.Date;

/**
 * @author Vishal B
 * @version 1.0
 * Use : Main Domain Object class for storing the Measurement details for the member 
 */	
public class MeasurementInfo {

	private Date 				m_DMeasurementTakenDate;
	private int 				m_iHeight;				//Height in centimeters
	private float 				m_fWeight;
	private float 				m_fChest;
	private float 				m_fWaist;
	private float 				m_fHips;
	private	float				m_fThighs;
	private float 				m_fCalf;
	private float				m_fArms;
	private	float				m_fForeamrs;
	
	
	private int 				m_iFatInPer;
	private int					m_iBodyAge;
	private int 				m_iBMI;
	
	

	private int					m_iRM;
	private float 				m_fVisceralFat;
	private float				m_fWholeBodySF;
	private float				m_fWholeBodySM;
	private float	 			m_fTrunkSF;
	private float 				m_fTrunkSM;
	private float				m_fLegSF;
	private float				m_fLegSM;
	private float				m_fArmSF;
	private float				m_fArmSM;
	
	

	public Date getMeasurementTakenDate() {
		return m_DMeasurementTakenDate;
	}
	
	
	
	public void setMeasurementTakenDate(Date measurementTakenDate) {
		m_DMeasurementTakenDate = measurementTakenDate;
	}
	
	
	public int getHeight() {
		return m_iHeight;
	}
	
	public void setHeight(int height) {
		m_iHeight = height;
	}
	
	
	public float getChest() {
		return m_fChest;
	}
	
	
	public void setChest(float chest) {
		m_fChest = chest;
	}
	
	
	public float getWaist() {
		return m_fWaist;
	}
	
	
	public void setWaist(float waist) {
		m_fWaist = waist;
	}
	
	
	
	public float getHips() {
		return m_fHips;
	}
	
	
	
	
	public void setHips(float hips) {
		m_fHips = hips;
	}
	
	
	
	public float getThig() {
		return m_fThighs;
	}
	
	
	
	public void setThig(float thighs) {
		m_fThighs = thighs;
	}
	
	
	
	public float getCalf() {
		return m_fCalf;
	}
	
	
	
	public void setCalf(float calf) {
		m_fCalf = calf;
	}
	
	
	
	public float getArms() {
		return m_fArms;
	}
	
	
	
	public void setArms(float arms) {
		m_fArms = arms;
	}
	
	
	
	public float getForeamrs() {
		return m_fForeamrs;
	}
	
	
	public void setForeamrs(float foreamrs) {
		m_fForeamrs = foreamrs;
	}
	

	public float get_WholeBodySF() {
		return m_fWholeBodySF;
	}




	public void setWholeBodySF(float WholeBodySF) {
		m_fWholeBodySF = WholeBodySF;
	}



	public float get_WholeBodySM() {
		return m_fWholeBodySM;
	}




	public void setWholeBodySM(float WholeBodySM) {
		m_fWholeBodySM = WholeBodySM;
	}	
	
	
	public float getTrunkSF() {
		return m_fTrunkSF;
	}

	
	
	
	public void setTrunkSF(float trunkSF) {
		m_fTrunkSF = trunkSF;
	}


	
	public float getTrunkSM() {
		return m_fTrunkSM;
	}

	
	
	
	public void setTrunkSM(float trunkSM) {
		m_fTrunkSM = trunkSM;
	}

	
	
	
	
	public float getLegSF() {
		return m_fLegSF;
	}

	
	
	
	public void setLegSF(float legSF) {
		m_fLegSF = legSF;
	}


	public float getLegSM() {
		return m_fLegSM;
	}

	
	
	
	public void setLegSM(float legSM) {
		m_fLegSM = legSM;
	}


	
	
	
	
	public float getArmSF() {
		return m_fArmSF;
	}

	
	
	
	public void setArmSF(float armSF) {
		m_fArmSF = armSF;
	}

	public float getArmSM() {
		return m_fArmSM;
	}

	
	
	
	public void setArmSM(float armSM) {
		m_fArmSM = armSM;
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
