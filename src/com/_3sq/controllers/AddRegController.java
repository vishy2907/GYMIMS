package com._3sq.controllers;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;

import com._3sq.daoimpl.RegistrationPlanImpl;
import com._3sq.domainobjects.RegistrationPlan;

public class AddRegController extends GenericForwardComposer<Component> {

	
	private Intbox memberId;
	private Intbox planId;
	private Datebox planStartDate;
	private Datebox planEndDate;
	
	private Button addregToDB;
	
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		addregToDB.addEventListener("onClick", new EventListener<MouseEvent>() {

			@Override
			public void onEvent(MouseEvent arg0) throws Exception {
				RegistrationPlan newRegPlan = new RegistrationPlan();
				int memId = (Integer)Executions.getCurrent().getAttribute("memberId");
				
				
////////////////////////////////////////////////////////////////////////////////				
				newRegPlan.setPlanID(planId.getValue());
////////////////////////////////////////////////////////////////////////////////
				Date planStartDateVar = planStartDate.getValue();
				if(planStartDateVar!=null)
					newRegPlan.setStartDate(planStartDateVar);
				else
					newRegPlan.setStartDate(new Date());
////////////////////////////////////////////////////////////////////////////////
				Date planEndDateVar = planEndDate.getValue();
				if(planEndDateVar!=null)
					newRegPlan.setEndDate(planEndDateVar);
				else
					newRegPlan.setEndDate(new Date());
	
////////////////////////////////////////////////////////////////////////////////
				
				RegistrationPlanImpl.getmemberImpl().addRegistrationInfo(memId, newRegPlan);
			}
			
		});
		
	}
	
	
}
