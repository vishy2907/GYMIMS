package com._3sq.domainobjects;

import java.util.Date;

public class RegistrationPlan {
	
	Date  m_dStartDate;
	Date  m_dEndDate;
	int   m_iPlanID;
	public RegistrationPlan(Date startDate, Date endDate, int planID) {
		super();
		this.m_dStartDate = startDate;
		this.m_dEndDate = endDate;
		this.m_iPlanID = planID;
	}
	public Date getStartDate() {
		return m_dStartDate;
	}
	public void setStartDate(Date startDate) {
		this.m_dStartDate = startDate;
	}
	public Date getEndDate() {
		return m_dEndDate;
	}
	public void setEndDate(Date endDate) {
		this.m_dEndDate = endDate;
	}
	public int getPlanID() {
		return m_iPlanID;
	}
	public void setPlanID(int planID) {
		this.m_iPlanID = planID;
	}
	
	
}
