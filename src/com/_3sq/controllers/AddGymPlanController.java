package com._3sq.controllers;

import java.math.BigDecimal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Textbox;

import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.domainobjects.GymPlan;

public class AddGymPlanController extends GenericForwardComposer<Component> {

	private Intbox  planId;
	private Textbox planName;
	private Intbox  planFees;
	private Intbox  planDuration;
	
	private Button addPlanToDB;

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		addPlanToDB.addEventListener("onClick", new EventListener<MouseEvent>() {

			@Override
			public void onEvent(MouseEvent arg0) throws Exception {
			GymPlan	 newPlan = new GymPlan();
	
				newPlan.setPlanID(newPlan.getPlanID());
				
///////////////////////////////////////////////////////////////////
				
				String planNameVar = planName.getText();
				if(planNameVar!=null)
					newPlan.setPlanName(planNameVar);
				else
					newPlan.setPlanName("");
///////////////////////////////////////////////////////////////////

				int  planFeesVar = planFees.getValue();
				if(planFeesVar!=0)
					newPlan.setFees(planFeesVar);
				else
					newPlan.setFees(0);
///////////////////////////////////////////////////////////////////
				int  planDurVar = planDuration.getValue();
				if(planDurVar!=0)
					newPlan.setDurationInMonths(planDurVar);
				else
					newPlan.setDurationInMonths(0);
	
///////////////////////////////////////////////////////////////////
				
				GymPlanImpl.getgymImpl().addGymPlan(newPlan);
				
				
			}
			
		});
		
	}
	
	
}
	

