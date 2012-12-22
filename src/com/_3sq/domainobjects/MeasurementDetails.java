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
public class MeasurementDetails {

	private Date 				m_DMeasurementTakenDate;
	private int 				m_iHeight;				//Height in centimeters
	private float 				m_fChest;
	private float 				m_fWaist;
	private float 				m_fHips;
	private	float				m_fThighs;
	private float 				m_fCalf;
	private float				m_fArms;
	private	float				m_fForeamrs;
	
	
	
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

}
