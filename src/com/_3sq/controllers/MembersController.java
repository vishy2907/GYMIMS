/**
 * 
 */
package com._3sq.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

import com._3sq.daoimpl.MemberImpl;
import com._3sq.daos.MemberDAO;
import com._3sq.datatransporter.LightWeightMember;

/**
 * @author VishalB
 *
 */
public class MembersController extends GenericForwardComposer{

	/**
	 * 
	 */
	
	Listbox lb;
	
	private static final long serialVersionUID = -6528261260274326242L;
	private MemberDAO members;
	
	
	List<LightWeightMember> tempList;
	
	public MembersController()	{
		members = MemberImpl.getmemberImpl();
		
		
	}
	
	public List<LightWeightMember> getAllMembers()	{
		HashMap<Integer, LightWeightMember> memList = members.loadPartialMembers();
		List<LightWeightMember> temp = new ArrayList<LightWeightMember>();
		for(LightWeightMember loc : memList.values())	{
			temp.add(loc);
		}
		return temp;
	}
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		 lb.setItemRenderer(new ListitemRenderer<LightWeightMember>() {
				@Override
				public void render(Listitem item, LightWeightMember data, int index)
						throws Exception {
					
					item.appendChild(new Listcell(""+data.getMemberId()));
					item.appendChild(new Listcell(data.getMemberName()));
					item.appendChild(new Listcell(data.getDateOfBirth()));
				}
	        });
	         
	        lb.setModel(new ListModelList<LightWeightMember>(getAllMembers()));
}
	
}
