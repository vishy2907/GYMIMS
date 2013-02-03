/**
 * 
 */
package com._3sq.controllers.memberdetailscontrollers;

import java.util.ArrayList;
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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Longbox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.daoimpl.MemberImpl;
import com._3sq.domainobjects.Member;

/**
 * This class handles all the events fired on Personal Details of Member details Tab
 * @author VishalB
 *
 */
public class PersonalInformationController extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire	private Window 		memDetailWindow;
	@Wire	private Grid 		mdGrid;
	@Wire	private Intbox		memberId;
	@Wire	private Textbox 	memberName;
	@Wire	private Textbox 	memberAddress;
	@Wire	private Longbox 	memberContactNumber;
	@Wire	private Longbox	 	memberEmergencyContactNumber;
	@Wire	private Datebox		memberDOB;
	@Wire	private Combobox 	memberBloodGroup;
	@Wire	private Radio 		male;
	@Wire	private Radio 		female;
	@Wire	private Textbox 	memberOccupation;
	@Wire	private Textbox 	memberMedicalHistory;	
	//	private Image memberImage;
	@Wire	private Datebox 	memberRegDate;
	@Wire	private Button 		sendMessage;

	
	private  Member currMember;

	public Member getCurrMember()	{
		return currMember;
	}

	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		

		int memId = Integer.parseInt(System.getProperty("MemberId","0"));
		System.out.println("Here I came..."+memId);
		
		if(memId != 0)	{
			System.out.println("Selected Member : "+memId);
			currMember = MemberImpl.getmemberImpl().getMember(memId);

			sendMessage.setTooltip("Send message to "+currMember.getMemberName());
			////fill the details

			memberId.setValue(currMember.getMemberID());
			memberName.setValue( currMember.getMemberName());
			memberAddress.setValue(currMember.getMemberAddress());

			memberContactNumber.setValue(currMember.getContactNumber());

			memberEmergencyContactNumber.setValue(currMember.getEmergencyContactNo());
			memberDOB.setValue(currMember.getDateOfBirth());

			List<String >bloodGroups = new ArrayList<String>();
			bloodGroups.add("A +ve");
			bloodGroups.add("A -ve");
			bloodGroups.add("B +ve");
			bloodGroups.add("B -ve");
			bloodGroups.add("AB+ve" );
			bloodGroups.add("AB-ve" );
			bloodGroups.add("O +ve" );
			bloodGroups.add("O -ve"	);;
			
			int index = bloodGroups.indexOf(currMember.getBloodGroup());
			if(index!=-1)
				memberBloodGroup.setSelectedItem(memberBloodGroup.getItemAtIndex(index));

			if(currMember.getGender().equalsIgnoreCase("Male"))
				male.setSelected(true);
			else
				female.setSelected(true);

			memberOccupation.setValue(currMember.getOccupation());
			memberMedicalHistory.setValue(currMember.getMedicalHistory());
			//				private Textbox memberImage;
			memberRegDate.setValue(currMember.getRegistrationDate());

		}
	}

	@Listen("onClick = #sendMessage")
	public void onSendMessage(Event event){
		HashMap<String,String> arguments = new HashMap<String,String>();
		arguments.put("name", currMember.getMemberName());
		arguments.put("mobNo", ""+currMember.getContactNumber());
		Window window = (Window)Executions.createComponents("/UI/SendSMS.zul", null, arguments);
		//Register Window
		window.doModal();
	}
	
	
	@Listen("onChange = textbox")
	void dataChanged()	{
		System.out.println("Hiii ");
	}
//	memberName;                   
//	memberAddress;                
//	memberContactNumber;          
//	memberEmergencyContactNumber; 
//	memberDOB;                    
//	memberBloodGroup;             
//	male;                         
//	female;                       
//	memberOccupation;             
//	memberMedicalHistory;	      
//	e;                            
//	memberRegDate;                
//	sendMessage;                  

	
	
}
