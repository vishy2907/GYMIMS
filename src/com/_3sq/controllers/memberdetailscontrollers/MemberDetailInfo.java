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

/**
 * @author VishalB
 * Class handles the all Member Detail Info Tabs
 */
public class MemberDetailInfo extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire Tabbox idMemberDerailedInfo ;
	@Wire Tab idPersInfo;
	@Wire Tab idMsrmnrDetHistory;
	@Wire Tab idPaymentHistory;
	
	@Wire Tabpanel idPersInfoPanel;
	@Wire Tabpanel idMsrmnrDetHistoryPanel;
	@Wire Tabpanel idPaymentHistoryPanel;
	@Wire Toolbarbutton toolbarButton;
	
	int id = 0;
	Date[] allMsrmntDates;
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	@Listen("onSelect = #idPersInfo")
	public void onSelectPersonalInfoTab()	{
		toolbarButton.setVisible(false);
		id=0;
	}
	
	
	@Listen("onSelect = #idPaymentHistory")
	public void onSelectPaymentHistory()	{
		toolbarButton.setVisible(true);
		toolbarButton.setLabel("Make Payment / Renew Membership");
		toolbarButton.setImage("/UI/images/money.jpg");
		id=1;
	}
	
	@Listen("onSelect = #idMsrmnrDetHistory")
	public void onMeasurementTab()	{
		toolbarButton.setVisible(true);
		toolbarButton.setLabel("Update / Add Measurement");
		toolbarButton.setImage("/UI/images/gym2.jpg");
		id=2;
	}
	
	
	@Listen("onClick = #toolbarButton")
	public void onToolbarButton()	{
		if(id==1)	{
			Window window = (Window)Executions.createComponents("/UI/AddRegistration.zul", null, null);
			window.doModal();
		}else if (id==2)	{
			Window window = (Window)Executions.createComponents("/UI/addMeasurement.zul", null, null);
			window.doModal();
		}
	}
}
