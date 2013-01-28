/**
 * 
 */
package com._3sq.controllers;

import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Decimalbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.daoimpl.MemberImpl;
import com._3sq.domainobjects.Member;

/**
 * @author shani
 * 
 */

public class MemberDetailsController extends SelectorComposer<Component>  {
	/**
	 * 
	 */
	private int mId;

	private static final long serialVersionUID = -1427914022897958743L;

	@Wire
	private Window 		memDetailWindow;
	@Wire
	private Grid 		mdGrid;
	@Wire
	private Intbox		memberId;
	@Wire
	private Textbox 		memberName;
	@Wire
	private Textbox 		memberAddress;
	@Wire
	private Longbox 		memberContactNumber;
	@Wire
	private Longbox	 	memberEmergencyContactNumber;
	@Wire
	private Datebox		memberDOB;
	@Wire
	private Combobox 	memberBloodGroup;
	@Wire
	private Radio 		membergender;
	@Wire
	private Textbox 		memberOccupation;
	@Wire
	private Textbox 		memberMedicalHistory;	
//	private Textbox memberImage;
	@Wire
	private Datebox 		memberRegDate;
	
	
	@Wire
	private Datebox 		measurementTakenDate;
	@Wire
	private Intbox  		memberHeight;
	@Wire
	private Decimalbox	memberWeight;
	@Wire
	private Decimalbox	memberChest;
	@Wire
	private Decimalbox	memberWaist;
	@Wire
	private Decimalbox	memberHips;
	@Wire
	private Decimalbox	memberThighs;
	@Wire
	private Decimalbox	memberCalfs;
	@Wire
	private Decimalbox	memberArms;
	@Wire
	private Decimalbox	memberForearms;
	@Wire
	private Decimalbox	memberFatInPer;
	@Wire
	private Decimalbox	memberBodyAge;
	@Wire
	private Decimalbox	memberBMI;
	@Wire
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
	private String name="Rahul";
	private  Member currMember;

	private Integer meId;

	public Member getCurrMember()	{
		return currMember;
	}
	public void setCurrMember(Member no)	{
		
	}
	
	public MemberDetailsController	()	{
		System.out.println("Hiiiiiiiiiiiiiiiiiiiii.");
		meId=2;
	}
	
	 public void doAfterCompose(Component comp) throws Exception {
		 super.doAfterCompose(comp);
		 
		 System.out.println("After Compose");
		 Object memId = Executions.getCurrent().getAttribute("memberId");
		 
		 System.out.println("Member ID : "+memberId );
//		 	memberId.setValue(3);
//		 
		 
		 if(memId != null)	{
			 mId = (Integer)memId;
			 System.out.println(memId);
			 currMember = MemberImpl.getmemberImpl().getMember(mId);
			 
			 
			 ////fill the details
			 
			 	memberId.setValue(currMember.getMemberID());
			 	memberName.setValue( currMember.getMemberName());
				memberAddress.setValue(currMember.getMemberAddress());
				
				memberContactNumber.setValue(currMember.getContactNumber());
				
				memberEmergencyContactNumber.setValue(currMember.getEmergencyContactNo());
				Date d = currMember.getDateOfBirth();
				System.out.println("Date D : "+d);
				memberDOB.setValue(d);
				
				memberBloodGroup.setSelectedIndex(1);	//TODO...
				//membergender.setSelected(selected)
				memberOccupation.setValue(currMember.getOccupation());
				memberMedicalHistory.setValue(currMember.getMedicalHistory());
//				private Textbox memberImage;
				memberRegDate.setValue(currMember.getRegistrationDate());

				
				
				//measurementInfo...
				//getMeasureent Info... TODO : Obtain MeasurementInfo object
//				MeasurementInfo msrInfo = new MeasurementInfo();
//				
//				measurementTakenDate.setValue(msrInfo.getMeasurementTakenDate());
//				memberHeight.setValue(msrInfo.getHeight());
//				memberWeight.setValue(""+msrInfo.getWeight());
//				memberChest.setValue(""+msrInfo.getChest());
//				memberWaist.setValue(""+msrInfo.getWaist());
//				memberHips.setValue(""+msrInfo.getHips());
//				memberThighs.setValue(""+msrInfo.getThig());
//				memberCalfs.setValue(""+msrInfo.getCalf());
//				memberArms.setValue(""+msrInfo.getArms());
//				memberForearms.setValue(""+msrInfo.getForeamrs());
//				memberFatInPer.setValue(""+msrInfo.getFatInPer());
//				memberBodyAge.setValue(""+msrInfo.getBodyAge());
//				memberBMI.setValue(""+msrInfo.getBMI());
//				memberRM.setValue(""+msrInfo.getRM());
//				
//				memberVisceralFat.setValue(""+msrInfo.getVisceralFat());
//				memberWholeBodySF.setValue(""+msrInfo.get_WholeBodySF());
//				memberWholeBodySM.setValue(""+msrInfo.get_WholeBodySM());
//				memberTrunkSF.setValue(""+msrInfo.getTrunkSF());
//				memberTrunkSM.setValue(""+msrInfo.getTrunkSM());
//				memberLegSF.setValue(""+msrInfo.getLegSF());
//				memberLegSM.setValue(""+msrInfo.getLegSM());
//				memberArmSF.setValue(""+msrInfo.getArmSF());
//				memberArmSM.setValue(""+msrInfo.getArmSM());

		 }
	}

}
