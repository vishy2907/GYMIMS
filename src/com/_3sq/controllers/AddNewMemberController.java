package com._3sq.controllers;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;

import com._3sq.daoimpl.MemberImpl;
import com._3sq.domainobjects.Member;

public class AddNewMemberController extends GenericForwardComposer<Component> {

	private Intbox memberId;
	private Textbox memberName;
	private Textbox memberAddress;
	private Longbox memberContactNumber;
	private Longbox memberEmergencyContactNumber;
	private Datebox memberDOB;
	private Combobox memberBloodGroup;
	private Radio membergender;
	private Textbox memberOccupation;
	private Textbox memberMedicalHistory;	
//	private Textbox memberImage;
	private Datebox memberRegDate;
	
	private Button addMemberToDB;
	

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		addMemberToDB.addEventListener("onClick", new EventListener<MouseEvent>() {

			@Override
			public void onEvent(MouseEvent arg0) throws Exception {
				Member newMember = new Member();
				
				newMember.setMemberID(memberId.getValue());
				newMember.setMemberName(memberName.getText());
				newMember.setMemberAddress(memberAddress.getText());
				newMember.setContactNumber(memberContactNumber.getValue());
				newMember.setDateOfBirth(memberDOB.getValue());
				newMember.setBloodGroup(memberBloodGroup.getValue());
				newMember.setOccupation(memberOccupation.getText());
				newMember.setMedicalHistory(memberMedicalHistory.getText());
				newMember.setGender(membergender.getLabel());
				newMember.setEmergencyContactNo(memberEmergencyContactNumber.getValue());
				newMember.setRegistrationDate(memberRegDate.getValue());
	//			newMember.setImage(memmemberImage.getValue());
		
				MemberImpl.getmemberImpl().addMember(newMember);
			}
			
		});
		
	}
	
	
}
