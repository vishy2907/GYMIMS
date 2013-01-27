package com._3sq.controllers;


import java.math.BigDecimal;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Intbox;

import com._3sq.daoimpl.MeasurementImpl;
import com._3sq.domainobjects.MeasurementInfo;

public class AddNewMeasurementController extends GenericForwardComposer<Component> {

	
	private Datebox measurementTakenDate;
	private Intbox  memberHeight;
	private Decimalbox	memberWeight;
	private Decimalbox	memberChest;
	private Decimalbox	memberWaist;
	private Decimalbox	memberHips;
	private Decimalbox	memberThighs;
	private Decimalbox	memberCalfs;
	private Decimalbox	memberArms;
	private Decimalbox	memberForearms;
	private Decimalbox	memberFatInPer;
	private Decimalbox	memberBodyAge;
	private Decimalbox	memberBMI;
	private Decimalbox	memberRM;
	
	private Decimalbox	memberVisceralFat;
	private Decimalbox	memberWholeBodySF;
	private Decimalbox	memberWholeBodySM;
	private Decimalbox	memberTrunkSF;
	private Decimalbox memberTrunkSM;
	private Decimalbox	memberLegSF;
	private Decimalbox	memberLegSM;
	private Decimalbox	memberArmSF;
	private Decimalbox	memberArmSM;

	private Button addMeasurementToDB;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		
		addMeasurementToDB.addEventListener("onClick", new EventListener<MouseEvent>() {

			@Override
			public void onEvent(MouseEvent arg0) throws Exception {
				
				MeasurementInfo newMeasurement = new MeasurementInfo();
				int memId = (Integer)Executions.getCurrent().getAttribute("memberId");
				
			
				newMeasurement.setMeasurementTakenDate(measurementTakenDate.getValue());				
				newMeasurement.setHeight(memberHeight.getValue());
				
///////////////////////////////////////////////////////////////////
				BigDecimal height = memberChest.getValue();
				if(height!=null)
					newMeasurement.setHeight(height.intValue());
				else
					newMeasurement.setHeight(0);
///////////////////////////////////////////////////////////////////
				
				BigDecimal weight = memberWeight.getValue();
				if(weight!=null)
					newMeasurement.setWeight(weight.floatValue());
				else
					newMeasurement.setWeight(0f);
///////////////////////////////////////////////////////////////////
				
				BigDecimal chest = memberChest.getValue();
				if(chest!=null)
					newMeasurement.setChest(chest.floatValue());
				else
					newMeasurement.setChest(0f);
				
///////////////////////////////////////////////////////////////////

				BigDecimal Waist = memberWaist.getValue();
				if(Waist!=null)
					newMeasurement.setWaist(Waist.floatValue());
				else
					newMeasurement.setWaist(0f);

							
///////////////////////////////////////////////////////////////////
				BigDecimal Hips = memberHips.getValue();
				if(Hips!=null)
					newMeasurement.setHips(Hips.floatValue());
				else
					newMeasurement.setHips(0f);

				
///////////////////////////////////////////////////////////////
				BigDecimal Thighs = memberThighs.getValue();
				if(Thighs!=null)
					newMeasurement.setThig(Thighs.floatValue());
				else
					newMeasurement.setThig(0f);

				
				
///////////////////////////////////////////////////////////////////

				BigDecimal calf = memberCalfs.getValue();
				if(calf!=null)
					newMeasurement.setCalf(calf.floatValue());
				else
					newMeasurement.setCalf(0f);

///////////////////////////////////////////////////////////////////
				BigDecimal Arms = memberArms.getValue();
				if(Arms!=null)
					newMeasurement.setArms(Arms.floatValue());
				else
					newMeasurement.setArms(0f);
///////////////////////////////////////////////////////////////////
				BigDecimal Foreamrs = memberForearms.getValue();
				if(chest!=null)
					newMeasurement.setForeamrs(Foreamrs.floatValue());
				else
					newMeasurement.setForeamrs(0f);
///////////////////////////////////////////////////////////////////
				BigDecimal FatInPer = memberFatInPer.getValue();
				if(chest!=null)
					newMeasurement.setFatInPer(FatInPer.intValue());
				else
					newMeasurement.setFatInPer(0);
///////////////////////////////////////////////////////////////////
				BigDecimal BodyAge = memberBodyAge.getValue();
				if(chest!=null)
					newMeasurement.setBodyAge(BodyAge.intValue());
				else
					newMeasurement.setBodyAge(0);

///////////////////////////////////////////////////////////////////

				BigDecimal BMI = memberBMI.getValue();
				if(BMI!=null)
					newMeasurement.setBMI(BMI.intValue());
				else
					newMeasurement.setBMI(0);
///////////////////////////////////////////////////////////////////

				BigDecimal VisceralFat = memberVisceralFat.getValue();
				if(VisceralFat!=null)
					newMeasurement.setVisceralFat(VisceralFat.floatValue());
				else
					newMeasurement.setVisceralFat(0f);

///////////////////////////////////////////////////////////////////
				BigDecimal WholeBodySF = memberWholeBodySF.getValue();
				if(WholeBodySF!=null)
					newMeasurement.setWholeBodySF(WholeBodySF.floatValue());
				else
					newMeasurement.setWholeBodySF(0f);
///////////////////////////////////////////////////////////////////
				BigDecimal WholeBodySM = memberWholeBodySM.getValue();
				if(WholeBodySM!=null)
					newMeasurement.setWholeBodySM(WholeBodySM.floatValue());
				else
					newMeasurement.setWholeBodySM(0f);

///////////////////////////////////////////////////////////////////
				BigDecimal TrunkSF = memberTrunkSF.getValue();
				if(TrunkSF!=null)
					newMeasurement.setTrunkSF(TrunkSF.floatValue());
				else
					newMeasurement.setTrunkSF(0f);
///////////////////////////////////////////////////////////////////
				BigDecimal TrunkSM = memberTrunkSM.getValue();
				if(TrunkSM!=null)
					newMeasurement.setTrunkSM(TrunkSM.floatValue());
				else
					newMeasurement.setTrunkSM(0f);				
///////////////////////////////////////////////////////////////////

				BigDecimal LegsSF = memberLegSF.getValue();
				if(LegsSF!=null)
					newMeasurement.setLegSF(LegsSF.floatValue());
				else
					newMeasurement.setLegSF(0f);
///////////////////////////////////////////////////////////////////
				BigDecimal LegSM = memberLegSM.getValue();
				if(LegSM!=null)
					newMeasurement.setLegSM(LegSM.floatValue());
				else
					newMeasurement.setLegSM(0f);
			
///////////////////////////////////////////////////////////////////
				BigDecimal ArmSF = memberArmSF.getValue();
				if(ArmSF!=null)
					newMeasurement.setArmSF(ArmSF.floatValue());
				else
					newMeasurement.setArmSF(0f);

//////////////////////////////////////////////////////////////////

				BigDecimal ArmSM = memberArmSM.getValue();
				if(ArmSM!=null)
					newMeasurement.setArmSM(ArmSM.floatValue());
				else
					newMeasurement.setArmSM(0f);

				MeasurementImpl.getMeasurementImpl().addBodyMeasurement(memId, newMeasurement);
				
				
			}
			
		});
		
	}
	
	
}
