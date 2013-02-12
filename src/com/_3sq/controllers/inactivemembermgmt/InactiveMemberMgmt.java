/**
 * 
 */
package com._3sq.controllers.inactivemembermgmt;

import java.util.ArrayList;
import java.util.TreeMap;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;
import com._3sq.controllers.MembersController;
import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.Member;
import com._3sq.util.RendererClasses;

/**
 * @author VishalB
 */
public class InactiveMemberMgmt extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire private Listbox iMemberList;
	@Wire private Textbox inputName;
	@Wire private Window InMemberDetailsPanel;
	static ArrayList<LightWeightMember> allInactiveMembers = null;
	static private int index=-1;
	static ListModelList<LightWeightMember> listModel = null;
	
	
	private static InactiveMemberMgmt singleInstance = null;
	
	public static InactiveMemberMgmt getInactiveMemberMgmtImpl() {
		if (singleInstance == null) {
			synchronized (InactiveMemberMgmt.class) {
				if (singleInstance == null) {
					singleInstance = new  InactiveMemberMgmt();
				}
			}
		}
		return singleInstance;
	}
	public InactiveMemberMgmt()	{
		if(singleInstance==null)
			singleInstance=this;
	}
	
	@Listen("onOK = #inputName")
	public void onInputProvided(Event event)	{
		String input = inputName.getValue();
		
		iMemberList.setItemRenderer(RendererClasses.listItemRendererForListBox);
		
		TreeMap<Integer,LightWeightMember> tmpMap = MemberImpl.getmemberImpl().loadPartialMembers(input);
		if(tmpMap==null || tmpMap.size()==0)
			return;
		allInactiveMembers = new ArrayList<LightWeightMember>(tmpMap.size());
		for(LightWeightMember temp : tmpMap.values())	{
			allInactiveMembers.add(temp);
		}
		listModel = new ListModelList<LightWeightMember>(allInactiveMembers);
		listModel.setMultiple(false);	
		
		iMemberList.setModel(listModel);
		iMemberList.invalidate();
	}
	
	@Listen("onClick = #iMemberList")
    public void onClickOfList(Event event){
		int memberId = 0;
		try	{
			index = iMemberList.getSelectedIndex();
				memberId = allInactiveMembers.get(index).getMemberId();
		}catch(ArrayIndexOutOfBoundsException ae)	{
		}
		if (memberId != 0) {
			Window memberDetailsPanel = GymImsImpl.getGymImsImpl().getWindow("InMemberDetailsPanel"); 
			
			if (memberDetailsPanel != null) {
				Component firstChild = memberDetailsPanel.getFirstChild();
				if(firstChild==null){
					//Set the current Member here...
					Member currMember = MemberImpl.getmemberImpl().getMember(memberId);
					GymImsImpl.getGymImsImpl().setCurrMember(currMember);
					
					Window window = (Window)Executions.createComponents("MemberDetailsPages/MemberDetailInformation.zul", null, null);
					window.setTitle("Member Information : "+currMember.getMemberName());
					window.doEmbedded();
					memberDetailsPanel.appendChild(window);
				} else  {
					if(firstChild.getId().equals("memberInfo")==false)	{
						memberDetailsPanel.removeChild(firstChild);
						
						Member currMember = MemberImpl.getmemberImpl().getMember(memberId);
						GymImsImpl.getGymImsImpl().setCurrMember(currMember);
						
						Window window = (Window)Executions.createComponents("MemberDetailsPages/MemberDetailInformation.zul", null, null);
						window.doEmbedded();
						window.setTitle("Member Information : "+currMember.getMemberName());
						memberDetailsPanel.appendChild(window);
					}
					else{
						Member currMember = MemberImpl.getmemberImpl().getMember(memberId);
						GymImsImpl.getGymImsImpl().setCurrMember(currMember);
						firstChild.invalidate();
						((Window)firstChild).setTitle("Member Information : "+currMember.getMemberName());
					}
				} 

					
			}
		}
    }
	
	
	@Listen("onCreate = #InMemberDetailsPanel")
	public void regWindow(Event event)	{
		GymImsImpl.getGymImsImpl().registerWindow("InMemberDetailsPanel", InMemberDetailsPanel);
	}
	
	public void refreshList(LightWeightMember temp){
		listModel.remove(index);
		allInactiveMembers.remove(temp);
	}

}
