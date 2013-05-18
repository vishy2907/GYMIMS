package com._3sq.controllers;


import java.math.BigDecimal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Intbox;

import com._3sq.GymImsImpl;
import com._3sq.controllers.memberdetailscontrollers.MemberDetailInfo;
import com._3sq.daoimpl.MeasurementImpl;
import com._3sq.domainobjects.MeasurementInfo;

public class AddNewMeasurementController extends SelectorComposer<Component> {

	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 7740373982720476819L;
	@Wire private Datebox measurementTakenDate;
	@Wire private Intbox	    memberHeight;
	@Wire private Decimalbox	memberWeight;
	@Wire private Decimalbox	memberChest;
	@Wire private Decimalbox	memberWaist;
	@Wire 	private Decimalbox	memberHips;
	@Wire private Decimalbox	memberThighs;
	@Wire private Decimalbox	memberCalfs;
	@Wire private Decimalbox	memberArms;
	@Wire private Decimalbox	memberForearms;
	@Wire 	private Decimalbox	memberFatInPer;
	@Wire private Decimalbox	memberBodyAge;
	@Wire private Decimalbox	memberBMI;
	@Wire private Decimalbox	memberRM;
	
	@Wire private Decimalbox	memberVisceralFat;
	@Wire private Decimalbox	memberWholeBodySF;
	@Wire private Decimalbox	memberWholeBodySM;
	@Wire private Decimalbox	memberTrunkSF;
	@Wire private Decimalbox 	memberTrunkSM;
	@Wire private Decimalbox	memberLegSF;
	@Wire private Decimalbox	memberLegSM;
	@Wire private Decimalbox	memberArmSF;
	@Wire private Decimalbox	memberArmSM;

	@Wire private Button addMeasurementToDB;
	
	@Listen("onClick = #addMeasurementToDB")
	public void onClkAddMsrmnt(){
		System.out.println("Hiii................................");
		MeasurementInfo newMeasurement = new MeasurementInfo();
		
		int memId = GymImsImpl.getGymImsImpl().getCurrMember().getMemberID();
		
	
		newMeasurement.setMeasurementTakenDate(measurementTakenDate.getValue());				
		newMeasurement.setHeight(memberHeight.getValue());
		
		BigDecimal height = memberChest.getValue();
		if(height!=null)
			newMeasurement.setHeight(height.intValue());
		else
			newMeasurement.setHeight(0);
		
		BigDecimal weight = memberWeight.getValue();
		if(weight!=null)
			newMeasurement.setWeight(weight.floatValue());
		else
			newMeasurement.setWeight(0f);
		
		BigDecimal chest = memberChest.getValue();
		if(chest!=null)
			newMeasurement.setChest(chest.floatValue());
		else
			newMeasurement.setChest(0f);
		

		BigDecimal Waist = memberWaist.getValue();
		if(Waist!=null)
			newMeasurement.setWaist(Waist.floatValue());
		else
			newMeasurement.setWaist(0f);

					
		BigDecimal Hips = memberHips.getValue();
		if(Hips!=null)
			newMeasurement.setHips(Hips.floatValue());
		else
			newMeasurement.setHips(0f);

		
		BigDecimal Thighs = memberThighs.getValue();
		if(Thighs!=null)
			newMeasurement.setThig(Thighs.floatValue());
		else
			newMeasurement.setThig(0f);

		
		

		BigDecimal calf = memberCalfs.getValue();
		if(calf!=null)
			newMeasurement.setCalf(calf.floatValue());
		else
			newMeasurement.setCalf(0f);

		BigDecimal Arms = memberArms.getValue();
		if(Arms!=null)
			newMeasurement.setArms(Arms.floatValue());
		else
			newMeasurement.setArms(0f);

		BigDecimal Foreamrs = memberForearms.getValue();
		if(Foreamrs!=null)
			newMeasurement.setForeamrs(Foreamrs.floatValue());
		else
			newMeasurement.setForeamrs(0f);

		BigDecimal FatInPer = memberFatInPer.getValue();
		if(FatInPer!=null)
			newMeasurement.setFatInPer(FatInPer.intValue());
		else
			newMeasurement.setFatInPer(0);

		BigDecimal BodyAge = memberBodyAge.getValue();
		if(BodyAge!=null)
			newMeasurement.setBodyAge(BodyAge.intValue());
		else
			newMeasurement.setBodyAge(0);


		BigDecimal BMI = memberBMI.getValue();
		if(BMI!=null)
			newMeasurement.setBMI(BMI.intValue());
		else
			newMeasurement.setBMI(0);

		
		BigDecimal VisceralFat = memberVisceralFat.getValue();
		if(VisceralFat!=null)
			newMeasurement.setVisceralFat(VisceralFat.floatValue());
		else
			newMeasurement.setVisceralFat(0f);

		BigDecimal WholeBodySF = memberWholeBodySF.getValue();
		if(WholeBodySF!=null)
			newMeasurement.setWholeBodySF(WholeBodySF.floatValue());
		else
			newMeasurement.setWholeBodySF(0f);

		BigDecimal WholeBodySM = memberWholeBodySM.getValue();
		if(WholeBodySM!=null)
			newMeasurement.setWholeBodySM(WholeBodySM.floatValue());
		else
			newMeasurement.setWholeBodySM(0f);

		BigDecimal TrunkSF = memberTrunkSF.getValue();
		if(TrunkSF!=null)
			newMeasurement.setTrunkSF(TrunkSF.floatValue());
		else
			newMeasurement.setTrunkSF(0f);

		BigDecimal TrunkSM = memberTrunkSM.getValue();
		if(TrunkSM!=null)
			newMeasurement.setTrunkSM(TrunkSM.floatValue());
		else
			newMeasurement.setTrunkSM(0f);				

		
		BigDecimal LegsSF = memberLegSF.getValue();
		if(LegsSF!=null)
			newMeasurement.setLegSF(LegsSF.floatValue());
		else
			newMeasurement.setLegSF(0f);

		BigDecimal LegSM = memberLegSM.getValue();
		if(LegSM!=null)
			newMeasurement.setLegSM(LegSM.floatValue());
		else
			newMeasurement.setLegSM(0f);
	
		BigDecimal ArmSF = memberArmSF.getValue();
		if(ArmSF!=null)
			newMeasurement.setArmSF(ArmSF.floatValue());
		else
			newMeasurement.setArmSF(0f);



		BigDecimal ArmSM = memberArmSM.getValue();
		if(ArmSM!=null)
			newMeasurement.setArmSM(ArmSM.floatValue());
		else
			newMeasurement.setArmSM(0f);

		try{
			MeasurementImpl.getMeasurementImpl().addBodyMeasurement(memId, newMeasurement);
			Clients.showNotification("Measurement Added Successfully.");
			MemberDetailInfo.getMemberDetailInfo().refreshMember();
			
			
		}catch(Exception e){
			Clients.showNotification("Problem occured during adding measurement.");
		}
		
	}
}
	

