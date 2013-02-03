/**
 * 
 */
package com._3sq.controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
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
 * @author VishalB
 * 
 */

public class MemberDetailsController extends SelectorComposer<Component>  {
	/**
	 * 
	 */
	private int mId;

	private static final long serialVersionUID = -1427914022897958743L;


	
	@Wire
	private Datebox 	measurementTakenDate;
	@Wire
	private Intbox  	memberHeight;
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

	@Wire
	private Decimalbox	memberVisceralFat;
	@Wire
	private Decimalbox	memberWholeBodySF;
	@Wire
	private Decimalbox	memberWholeBodySM;
	@Wire
	private Decimalbox	memberTrunkSF;
	@Wire
	private Decimalbox 	memberTrunkSM;
	@Wire
	private Decimalbox	memberLegSF;
	@Wire
	private Decimalbox	memberLegSM;
	@Wire
	private Decimalbox	memberArmSF;
	@Wire
	private Decimalbox	memberArmSM;
	@Wire
	private Button 	sendMessage;
	
	private  Member currMember;

	public Member getCurrMember()	{
		return currMember;
	}

	public void setCurrMember(Member no)	{
	}
	
	public MemberDetailsController	()	{
	}

		 

				
				
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

	 

