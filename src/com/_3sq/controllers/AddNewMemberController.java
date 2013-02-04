package com._3sq.controllers;


import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.Member;

public class AddNewMemberController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 2451960659521816339L;

	@Wire private Intbox  	memberId;
	@Wire private Textbox 	memberName;
	@Wire private Textbox 	memberAddress;
	@Wire private Longbox 	memberContactNumber;
	@Wire private Longbox 	memberEmergencyContactNumber;
	@Wire private Datebox 	memberDOB;
	@Wire private Combobox 	memberBloodGroup;
	@Wire private Radio 	male;
	@Wire private Radio 	female;
	@Wire private Textbox 	memberOccupation;
	@Wire private Textbox 	memberMedicalHistory;	
//	Wire private Textbox memberImage;
	@Wire private Datebox 	memberRegDate;
	@Wire private Button 	addMemberToDB;
	@Wire private Window	addNewMember;
	@Wire private Listbox lb;
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		//Set MemberID to incremental Position...
		//Setup Initial Information by Manually
		
		int nextMemberId = MemberImpl.getmemberImpl().getNextMemberID();
		memberId.setValue(nextMemberId);
		memberContactNumber.setMaxlength(10);
		memberEmergencyContactNumber.setMaxlength(10);
		memberRegDate.setValue(new Date());
		
		addMemberToDB.addEventListener("onClick", new EventListener<MouseEvent>() {
			@Override
			public void onEvent(MouseEvent arg0) throws Exception {
				
				Member newMember = new Member();
				
				newMember.setMemberID(memberId.getValue());
				
				String tName = memberName.getValue();
					newMember.setMemberName(tName);

				String tAddress = memberAddress.getValue();
				if(tAddress!=null)
					newMember.setMemberAddress(tAddress);
				else
					newMember.setMemberAddress("");
				
				long no = memberContactNumber.getValue();
				newMember.setContactNumber(no);
					
				
				Date dob = memberDOB.getValue();
				if(dob!=null)
					newMember.setDateOfBirth(dob);
				else
					newMember.setDateOfBirth(new Date());
				
				newMember.setBloodGroup(memberBloodGroup.getValue());
				
				String occu = memberOccupation.getValue();
				if(occu!=null)	{
					newMember.setOccupation(occu);
					System.out.println("occu :"+occu);
				}
				else
					newMember.setOccupation("");
				
				String his = memberMedicalHistory.getValue();
				if(his!=null)
					newMember.setMedicalHistory(his);
				else
					newMember.setMedicalHistory("");
				
				if(male.isChecked())
					newMember.setGender("Male");
				else if(female.isChecked())
					newMember.setGender("Female");
				
				newMember.setEmergencyContactNo(memberEmergencyContactNumber.getValue());
				
				Date regDate = memberRegDate.getValue();
				newMember.setRegistrationDate(regDate);
				
				
				//newMember.setImage(memmemberImage.getValue());
				System.out.println("Adding New Member to the database...");
				if( MemberImpl.getmemberImpl().addMember(newMember))	{
					Clients.showNotification("Member Addess Successfully.", true);
					LightWeightMember newM = new LightWeightMember(newMember.getMemberID(),newMember.getMemberName(),newMember.getDateOfBirth(),true,"");
					MembersController.getMemberControllerImpl().addItemToRender(newM);
					GymImsImpl.getGymImsImpl().sendWelcomeMessage(""+newMember.getContactNumber());
					GymImsImpl.getGymImsImpl().getAllActiveMembers().add(newM);
					GymImsImpl.getGymImsImpl().getAllActiveMembers().add(newM);
					lb.invalidate();
					addNewMember.detach();
				}
				else
					Clients.showNotification("Error in adding member","error", null,null,5);
			}
		});
	}
}
