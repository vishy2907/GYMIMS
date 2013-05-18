/**
 * 
 */
package com._3sq.controllers.memberdetailscontrollers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

import com._3sq.controllers.MembersController;
import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.MemberImpl;

/**
 * @author VishalB
 * Class handles the all Member Detail Info Tabs
 */
public class MemberDetailInfo extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1L;
	@Wire Tabbox idMemberDerailedInfo ;
	@Wire Tab idPersInfo;
	@Wire Tab idMsrmnrDetHistory;
	@Wire Tab idPaymentHistory;
	
	@Wire Tabpanel idPersInfoPanel;
	@Wire Tabpanel idMsrmnrDetHistoryPanel;
	@Wire Tabpanel idPaymentHistoryPanel;
	@Wire Toolbarbutton msrmntButton;
	@Wire Toolbarbutton paymentButton;
	@Wire Window memberInfo;
	
	private static MemberDetailInfo singleInstance;
	public static MemberDetailInfo  getMemberDetailInfo() {
		if (singleInstance == null) {
			synchronized (GymPlanImpl.class) {
				if (singleInstance == null) {
					singleInstance = new  MemberDetailInfo();
				}
			}
		}
		return singleInstance;
	}

	public MemberDetailInfo() {
		singleInstance = this;
	}
	
	Date[] allMsrmntDates;
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Listen("onSelect = #idPersInfo")
	public void onSelectPersonalInfoTab()	{
		paymentButton.setVisible(true);
		msrmntButton.setVisible(true);
		paymentButton.setLabel(" ");
		msrmntButton.setLabel(" ");
	}

	@Listen("onSelect = #idPaymentHistory")
	public void onSelectPaymentHistory()	{
		paymentButton.setVisible(true);
		paymentButton.setLabel("Make Payment / Renew Membership");
		msrmntButton.setVisible(false);
	}
	
	@Listen("onSelect = #idMsrmnrDetHistory")
	public void onMeasurementTab()	{
		paymentButton.setVisible(false);
		msrmntButton.setVisible(true);
		msrmntButton.setLabel("Update / Add Measurement");
	}
	
	@Listen("onClick = #paymentButton")
	public void onToolbarButton()	{
			Window window = (Window)Executions.createComponents("/UI/AddRegistration.zul", null, null);
			window.doModal();
	}
	
	@Listen("onClick = #msrmntButton")
	public void onClickOnTemp()	{
			Window window = (Window)Executions.createComponents("/UI/addMeasurement.zul", null, null);
			window.doModal();
	}

	public void refreshMember() {
		System.out.println("Refreshing...");
		idMsrmnrDetHistoryPanel.invalidate();
		idPaymentHistoryPanel.invalidate();
	}
}
