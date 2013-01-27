/**
 * 
 */
package com._3sq.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.daoimpl.MemberImpl;
import com._3sq.domainobjects.MeasurementInfo;
import com._3sq.domainobjects.Member;

/**
 * @author shani
 * 
 */
public class MemberDetailsController extends GenericForwardComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1427914022897958743L;

	private Intbox		memberId;
	private Textbox 	memberName;
	private Textbox 	memberAddress;
	private Longbox 	memberContactNumber;
	private Longbox	 	memberEmergencyContactNumber;
	private Datebox		memberDOB;
	private Combobox 	memberBloodGroup;
	private Radio 		membergender;
	private Textbox 	memberOccupation;
	private Textbox 	memberMedicalHistory;	
//	private Textbox memberImage;
	private Datebox 	memberRegDate;
	
	
	private Datebox 	measurementTakenDate;
	private Intbox  	memberHeight;
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
	private Decimalbox 	memberTrunkSM;
	private Decimalbox	memberLegSF;
	private Decimalbox	memberLegSM;
	private Decimalbox	memberArmSF;
	private Decimalbox	memberArmSM;

	
	private Window mdWindow;
	public String name="Rahul";
	public Member currMember;
	
	public void doAfterCompose(Component comp) throws Exception {
		 int mId = 0;
		 Object memId = Executions.getCurrent().getAttribute("memberId");
		 
		 if(memId != null)	{
			 mId = (Integer)memId;
			 System.out.println(memId);
			 currMember = MemberImpl.getmemberImpl().getMember(mId);
			 
			 mdWindow = (Window)comp.getFirstChild();
			 mdWindow.setVisible(true);
		
		
			 ////fill the details
			 	memberId.setValue(mId);
			 	memberName.setValue( currMember.getMemberName());
				memberAddress.setValue(currMember.getMemberAddress());
				
				memberContactNumber.setValue(currMember.getContactNumber());
				
				memberEmergencyContactNumber.setValue(currMember.getEmergencyContactNo());
				memberDOB.setValue(currMember.getDateOfBirth());
				
				memberBloodGroup.setValue(currMember.getBloodGroup());
				//membergender.set`
				memberOccupation.setValue(currMember.getOccupation());
				memberMedicalHistory.setValue(currMember.getMedicalHistory());
//				private Textbox memberImage;
				memberRegDate.setValue(currMember.getRegistrationDate());

				
				
				//measurementInfo...
				//getMeasureent Info... TODO : Obtain MeasurementInfo object
				MeasurementInfo msrInfo = new MeasurementInfo();
				
				measurementTakenDate.setValue(msrInfo.getMeasurementTakenDate());
				memberHeight.setValue(msrInfo.getHeight());
				memberWeight.setValue(""+msrInfo.getWeight());
				memberChest.setValue(""+msrInfo.getChest());
				memberWaist.setValue(""+msrInfo.getWaist());
				memberHips.setValue(""+msrInfo.getHips());
				memberThighs.setValue(""+msrInfo.getThig());
				memberCalfs.setValue(""+msrInfo.getCalf());
				memberArms.setValue(""+msrInfo.getArms());
				memberForearms.setValue(""+msrInfo.getForeamrs());
				memberFatInPer.setValue(""+msrInfo.getFatInPer());
				memberBodyAge.setValue(""+msrInfo.getBodyAge());
				memberBMI.setValue(""+msrInfo.getBMI());
				memberRM.setValue(""+msrInfo.getRM());
				
				memberVisceralFat.setValue(""+msrInfo.getVisceralFat());
				memberWholeBodySF.setValue(""+msrInfo.get_WholeBodySF());
				memberWholeBodySM.setValue(""+msrInfo.get_WholeBodySM());
				memberTrunkSF.setValue(""+msrInfo.getTrunkSF());
				memberTrunkSM.setValue(""+msrInfo.getTrunkSM());
				memberLegSF.setValue(""+msrInfo.getLegSF());
				memberLegSM.setValue(""+msrInfo.getLegSM());
				memberArmSF.setValue(""+msrInfo.getArmSF());
				memberArmSM.setValue(""+msrInfo.getArmSM());

		 }
	}

}
