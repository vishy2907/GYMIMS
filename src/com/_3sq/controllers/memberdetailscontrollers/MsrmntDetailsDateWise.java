/**
 * 
 */
package com._3sq.controllers.memberdetailscontrollers;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Intbox;

import com._3sq.GymImsImpl;
import com._3sq.daoimpl.MeasurementImpl;
import com._3sq.domainobjects.MeasurementInfo;

/**
 * @author shani
 * This class will control the Member's measurements date wise...
 */
public class MsrmntDetailsDateWise extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Wire 	private Datebox 	measurementTakenDate;
	@Wire	private Label  memberHeight;
	@Wire	private Label	memberWeight;
	@Wire	private Label	memberChest;
	@Wire	private Label	memberWaist;
	@Wire	private Label	memberHips;
	@Wire	private Label	memberThighs;
	@Wire	private Label	memberCalfs;
	@Wire	private Label	memberArms;
	@Wire	private Label	memberForearms;
	@Wire	private Label	memberFatInPer;
	@Wire	private Label	memberBodyAge;
	@Wire	private Label	memberBMI;
	@Wire	private Label	memberRM;
	@Wire 	private Label	memberVisceral;
	@Wire	private Label	memberWholeBodySF;
	@Wire	private Label	memberWholeBodySM;
	@Wire	private Label	memberTrunkSF;
	@Wire	private Label 	memberTrunkSM;
	@Wire	private Label	memberLegSF;
	@Wire	private Label	memberLegSM;
	@Wire	private Label	memberArmSF;
	@Wire	private Label	memberArmSM;
	
	private  MeasurementInfo currMeasurementInfo;

	public MeasurementInfo getCurrMember()	{
		return currMeasurementInfo;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		GymImsImpl gym = GymImsImpl.getGymImsImpl();
		
		int memId = gym.getCurrMember().getMemberID();
		Date currDate = gym.getCurrSelectedDate();
		
		if(currDate != null )	{
		
			currMeasurementInfo = MeasurementImpl.getMeasurementImpl().getMeasurement(memId, currDate);
			
			measurementTakenDate.setValue(currDate);
			memberHeight.setValue(""+currMeasurementInfo.getHeight());
			memberWeight.setValue(""+currMeasurementInfo.getWeight());
			memberChest.setValue(""+currMeasurementInfo.getChest());
			memberWaist.setValue(""+currMeasurementInfo.getWaist());
			memberHips.setValue(""+currMeasurementInfo.getHips());
			memberThighs.setValue(""+currMeasurementInfo.getThig());
			memberCalfs.setValue(""+currMeasurementInfo.getCalf());
			memberArms.setValue(""+currMeasurementInfo.getArms());
			memberForearms.setValue(""+currMeasurementInfo.getForeamrs());
			memberFatInPer.setValue(""+currMeasurementInfo.getFatInPer());
			memberBodyAge.setValue(""+currMeasurementInfo.getBodyAge());
			memberBMI.setValue(""+currMeasurementInfo.getBMI());
			memberRM.setValue(""+currMeasurementInfo.getRM());
			memberVisceral.setValue(""+currMeasurementInfo.getVisceralFat());
			memberWholeBodySF.setValue(""+currMeasurementInfo.get_WholeBodySF());
			memberWholeBodySM.setValue(""+currMeasurementInfo.get_WholeBodySM());
			memberTrunkSF.setValue(""+currMeasurementInfo.getTrunkSF());
			memberTrunkSM.setValue(""+currMeasurementInfo.getTrunkSM());
			memberLegSF.setValue(""+currMeasurementInfo.getLegSF());
			memberLegSM.setValue(""+currMeasurementInfo.getLegSM());
			memberArmSF.setValue(""+currMeasurementInfo.getArmSF());
			memberArmSM.setValue(""+currMeasurementInfo.getArmSM());
			
			
			gym.resetCurrSelectedDate();
		}
	}
}
