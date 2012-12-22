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

	private Date 				m_MeasurementTakenDate;
	private int 				m_Height;				//Height in centimeters
	private float 				m_Chest;
	private float 				m_Waist;
	private float 				m_Hips;
	private	float				m_Thighs;
	private float 				m_Calf;
	private float				m_Arms;
	private	float				m_Foreamrs;
	
	
	
	public Date getMeasurementTakenDate() {
		return m_MeasurementTakenDate;
	}
	public void setMeasurementTakenDate(Date measurementTakenDate) {
		m_MeasurementTakenDate = measurementTakenDate;
	}
	public int getHeight() {
		return m_Height;
	}
	public void setHeight(int height) {
		m_Height = height;
	}
	public float getChest() {
		return m_Chest;
	}
	public void setChest(float chest) {
		m_Chest = chest;
	}
	
	public float getWaist() {
		return m_Waist;
	}
	public void setWaist(float waist) {
		m_Waist = waist;
	}
	public float getHips() {
		return m_Hips;
	}
	public void setHips(float hips) {
		m_Hips = hips;
	}
	public float getThig() {
		return m_Thighs;
	}
	public void setThig(float thighs) {
		m_Thighs = thighs;
	}
	public float getCalf() {
		return m_Calf;
	}
	public void setCalf(float calf) {
		m_Calf = calf;
	}
	public float getArms() {
		return m_Arms;
	}
	public void setArms(float arms) {
		m_Arms = arms;
	}
	public float getForeamrs() {
		return m_Foreamrs;
	}
	public void setForeamrs(float foreamrs) {
		m_Foreamrs = foreamrs;
	}
	
	
}
