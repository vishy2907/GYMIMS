package com._3sq.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;
import com._3sq.controllers.memberdetailscontrollers.MemberDetailInfo;
import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.RegistrationPlanImpl;
import com._3sq.domainobjects.GymPlan;
import com._3sq.domainobjects.RegistrationPlan;

public class AddRegController extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire	private Intbox memberId;
	@Wire	private Intbox receipt;
	@Wire 	private Datebox feesPaidDate;
	@Wire	private Combobox memberPlanN;
	@Wire	private Combobox memberPlanO;
	@Wire	private Datebox planStartDate;
	@Wire	private Datebox planEndDate;

	@Wire	private Button addregToDB;
	@Wire	private Radio newR;
	@Wire	private Radio oldR;

	@Wire	private Intbox fees;
	@Wire	private Textbox duration;
//	@Wire 	private Window regWindow;
	
	static int flag = 1;
	static String reason = "";
	static HashMap<Integer, GymPlan> allPlans;
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	@Listen("onCheck = #newR")
	public void onSelectNewR() {
		flag = 1;
		memberPlanN.setVisible(true);
		memberPlanO.setVisible(false);
		reason = "Fees towards New Registration";
		clearAll();
	}

	@Listen("onCheck = #oldR")
	public void onSelectOldR() {
		flag = 2;
		memberPlanN.setVisible(false);
		memberPlanO.setVisible(true);
		reason = "Fees towards Renewal";
		clearAll();
	}

	static GymPlan gym;

	@Listen("onSelect = #memberPlanN,#memberPlanO")
	public void onSelectionOfPlan() {
		String planName = "";
		if (flag == 1) {
			planName = memberPlanN.getSelectedItem().getLabel();
		} else {
			planName= memberPlanO.getSelectedItem().getLabel();
		}
		
		gym = getGymPlan(planName);
		fees.setValue(gym.getFees());
		duration.setValue(gym.getDurationInMonths() + " Month(s)");

		changeEndDate();
		addregToDB.setDisabled(false);
	}

	@Listen("onChange = #planStartDate")
	public void dateChanged() {
		changeEndDate();
	}

	//Add data to database...
	@Listen("onClick = #addregToDB")
	public void addData() {
		boolean bNoException = true;
		int receiptNo = receipt.getValue();
		
		
		RegistrationPlan rp = new  RegistrationPlan
						(receiptNo, gym.getPlanID(), 
						gym.getDurationInMonths(), planStartDate.getValue(),
						planEndDate.getValue(), gym.getFees(), 
						reason, gym.getFees(), 
						feesPaidDate.getValue());
				
		try	{
			RegistrationPlanImpl.getRegistrationPlanImpl().addRegistrationInfo(memberId.getValue(), rp, reason);
			MemberDetailInfo.getMemberDetailInfo().refreshMember();
		}catch(Exception e){
			bNoException = false;
		}
		
		if(bNoException){
			if(flag==1)
				Clients.showNotification("Payment Added Successfully.");
			else
				Clients.showNotification("Renewal of Membership successful.");

			//regWindow.detach();
		}else	{
			Clients.showNotification("Problem in adding registration info. Please check the Receipt No", "error", null, "middle_center", 20);
		}
		
	}

	private void changeEndDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(planStartDate.getValue());
		System.out.println("Today : " + cal.getTime());

		cal.add(Calendar.MONTH, gym.getDurationInMonths());
		cal.add(Calendar.DATE, -1);

		planEndDate.setValue(cal.getTime());
	}

	private void clearAll() {
		planStartDate.setValue(new Date());
		fees.invalidate();
		duration.invalidate();
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		receipt.setValue(RegistrationPlanImpl.getRegistrationPlanImpl().getNextReceiptNumber());
	
		feesPaidDate.setValue(new Date());
		planStartDate.setValue(new Date());
		// changeEndDate();
		allPlans = GymImsImpl.getGymImsImpl().getGymPlans();
		for (GymPlan plan : allPlans.values()) {
			String planName = plan.getPlanName();
			Comboitem item = new Comboitem(planName);
			if (planName.contains("New ") == true) {
				memberPlanN.appendChild(item);
			} else {
				memberPlanO.appendChild(item);
			}
		}
		//
		int mId = GymImsImpl.getGymImsImpl().getCurrMember().getMemberID();
		if (mId != -1) {
			memberId.setValue(mId);
			memberId.setReadonly(true);
		}
	}
	private GymPlan getGymPlan(String name)	{
		for (GymPlan plan : allPlans.values()) {
			if(name.equals(plan.getPlanName()))
				return plan;
		}
		return null;
	}
}
