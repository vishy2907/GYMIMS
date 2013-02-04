package com._3sq.domainobjects;

import java.util.Date;

public class RegistrationPlan {
	
	int 	m_iReceiptId;
	int   	m_iPlanID;
	int 	m_iDurationInMonth;
	Date  	m_dStartDate;
	Date  	m_dEndDate;
	int 	m_iFees;
	
	
	
	public RegistrationPlan(int receiptId, int planID,
			int durationInMonth, Date startDate, Date endDate, int fees) {
		super();
		this.m_iReceiptId = receiptId;
		this.m_iPlanID = planID;
		this.m_iDurationInMonth = durationInMonth;
		this.m_dStartDate = startDate;
		this.m_dEndDate = endDate;
		this.m_iFees = fees;
	}
	
	public int getFees() {
		return m_iFees;
	}

	public void setFees(int Fees) {
		this.m_iFees = Fees;
	}

	public int getReceiptId() {
		return m_iReceiptId;
	}
	public void setReceiptId(int receiptId) {
		this.m_iReceiptId = receiptId;
	}
	public int getDurationInMonth() {
		return m_iDurationInMonth;
	}
	public void setDurationInMonth(int durationInMonth) {
		this.m_iDurationInMonth = durationInMonth;
	}
	public RegistrationPlan() {
		// TODO Auto-generated constructor stub
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
