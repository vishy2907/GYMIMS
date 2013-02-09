/**
 * 
 */
package com._3sq.controllers.memberdetailscontrollers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;

import com._3sq.GymImsImpl;
import com._3sq.daoimpl.RegistrationPlanImpl;
import com._3sq.domainobjects.GymPlan;
import com._3sq.domainobjects.RegistrationPlan;


/**
 * @author shani
 *
 */
public class PaymentDateWise extends  SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire private Label memberId;
	@Wire private Label feesPaidDate;
	@Wire private Label receipt;
	@Wire private Label feesPaidFor;
	@Wire private Label gymPlan; 
	@Wire private Label fees;
	@Wire private Label duration;
	@Wire private Label planStartDate;
	@Wire private Label planEndDate;
	
	private  RegistrationPlan currPaymentInfo;

	public RegistrationPlan getCurrPaymentDetail()	{
		return currPaymentInfo;
	}
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
		GymImsImpl gym = GymImsImpl.getGymImsImpl();
		int memId = gym.getCurrMember().getMemberID();
		Date currDate = gym.getCurrSelectedDate();

		if(currDate!=null)	{
		
			currPaymentInfo = RegistrationPlanImpl.getRegistrationPlanImpl().getPaymentDetails(memId, currDate);
			memberId.setValue(""+memId);
			feesPaidDate.setValue(""+df.format(currPaymentInfo.getPaindAmtDate()));
			receipt.setValue(""+currPaymentInfo.getReceiptId());          
			feesPaidFor.setValue(""+currPaymentInfo.getReason());
			
			GymPlan gp = GymImsImpl.getGymImsImpl().getGymPlan(currPaymentInfo.getPlanID());
			gymPlan.setValue(""+gp.getFees());
			fees.setValue(""+currPaymentInfo.getPaidAmount());             
			duration.setValue(""+gp.getDurationInMonths());         
			planStartDate.setValue(""+df.format(currPaymentInfo.getStartDate()));    
			planEndDate.setValue(""+df.format(currPaymentInfo.getEndDate()));      
			gym.resetCurrSelectedDate();
		}
	}
}
