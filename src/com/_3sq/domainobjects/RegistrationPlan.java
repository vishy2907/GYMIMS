package com._3sq.domainobjects;

import java.util.Date;

public class RegistrationPlan {
	
	int 	m_iReceiptId;
	int   	m_iPlanID;
	int 	m_iDurationInMonth;
	Date  	m_dStartDate;
	Date  	m_dEndDate;
	int 	m_iFees;
	String 	m_sReason;
	int 	m_iPaidAmount;
	Date	m_dPaindAmtDate;
	
	
	public RegistrationPlan(int receiptId, int planID, int durationInMonth,
			Date startDate, Date endDate, int fees, String reason,
			int paidAmount, Date paindAmtDate) {
		super();
		m_iReceiptId = receiptId;
		m_iPlanID = planID;
		m_iDurationInMonth = durationInMonth;
		m_dStartDate = startDate;
		m_dEndDate = endDate;
		m_iFees = fees;
		m_sReason = reason;
		m_iPaidAmount = paidAmount;
		m_dPaindAmtDate = paindAmtDate;
	}
	
	public RegistrationPlan()	{
		
	}
	
	public int getReceiptId() {
		return m_iReceiptId;
	}
	public void setReceiptId(int receiptId) {
		m_iReceiptId = receiptId;
	}
	public int getPlanID() {
		return m_iPlanID;
	}
	public void setPlanID(int planID) {
		m_iPlanID = planID;
	}
	public int getDurationInMonth() {
		return m_iDurationInMonth;
	}
	public void setDurationInMonth(int durationInMonth) {
		m_iDurationInMonth = durationInMonth;
	}
	public Date getStartDate() {
		return m_dStartDate;
	}
	public void setStartDate(Date startDate) {
		m_dStartDate = startDate;
	}
	public Date getEndDate() {
		return m_dEndDate;
	}
	public void setEndDate(Date endDate) {
		m_dEndDate = endDate;
	}
	public int getFees() {
		return m_iFees;
	}
	public void setFees(int fees) {
		m_iFees = fees;
	}
	public String getReason() {
		return m_sReason;
	}
	public void setReason(String reason) {
		m_sReason = reason;
	}
	public int getPaidAmount() {
		return m_iPaidAmount;
	}
	public void setPaidAmount(int paidAmount) {
		m_iPaidAmount = paidAmount;
	}
	public Date getPaindAmtDate() {
		return m_dPaindAmtDate;
	}
	public void setPaindAmtDate(Date paindAmtDate) {
		m_dPaindAmtDate = paindAmtDate;
	}
}
