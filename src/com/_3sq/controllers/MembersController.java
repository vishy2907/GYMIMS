/**
 * 
 */
package com._3sq.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Window;

import com._3sq.daoimpl.MemberImpl;
import com._3sq.daos.MemberDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.Member;

/**
 * @author VishalB
 * 
 */
public class MembersController extends SelectorComposer<Component> {


	@Wire
	private Listbox lb;
	@Wire
	private Button addNewMember;

	private static final long serialVersionUID = -6528261260274326242L;
	private MemberDAO members;

	public List<LightWeightMember> tempList;
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	
	public ListitemRenderer<LightWeightMember> listItemRenderer = new ListitemRenderer<LightWeightMember>() {
		@Override
		public void render(Listitem item, LightWeightMember data, int index)
				throws Exception {

			item.appendChild(new Listcell("" + data.getMemberId()));
			item.appendChild(new Listcell(data.getMemberName()));
			Date testDate = data.getDateOfBirth();

			if(testDate!=null)
				item.appendChild(new Listcell(""+df.format(testDate)));
			else
				item.appendChild(new Listcell("NA"));

		}
	};	

	public void addMemberToPanel(Member member)	{
			}
	
	Window memberDetails;

	public MembersController() {
		members = MemberImpl.getmemberImpl();
		getAllMembers();
	}

	
	public List<LightWeightMember> getAllMembers() {
		if (tempList == null) {
			tempList = new ArrayList<LightWeightMember>();
			HashMap<Integer, LightWeightMember> memList = members
					.loadPartialMembers();
			for (LightWeightMember loc : memList.values()) {
				tempList.add(loc);
			}
		}

		return tempList;
	}

	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
		if(listItemRenderer!=null)
			lb.setItemRenderer(listItemRenderer);
		
		ListModelList<LightWeightMember> tempModel = new ListModelList<LightWeightMember>(tempList);
		tempModel.setMultiple(false);

		lb.setModel(tempModel);

		final Component temp = comp;
		lb.addEventListener("onClick", new EventListener<MouseEvent>() {
			@Override
			public void onEvent(MouseEvent event) throws Exception {
				int memberId = 0;
				try	{
					memberId= tempList.get(lb.getSelectedIndex()).getMemberId();
				}catch(ArrayIndexOutOfBoundsException ae)	{
					
				}
				if (memberId != 0) {
					Component memDetails = temp.getFellowIfAny("MemberDetailsPanel");
					if (memDetails != null) {
						Component firstChild = memDetails.getFirstChild();
						if (firstChild != null) {
							// Here check for ID 
							if(firstChild.getId().equals("MemberDetail")==false)
								temp.removeChild(firstChild);
							else{
							((Include)firstChild).setDynamicProperty("memberId", memberId);
							firstChild.invalidate();
							}
						} else {
							Include includeTag = new Include();
							
							includeTag.setId("MemberDetail");
							includeTag.setSrc("MemberDetail.zul");
							includeTag.setMode("defer");
							includeTag.setDynamicProperty("memberId", memberId);
							// includeTag.setId("memDetailsPage");
							memDetails.appendChild(includeTag);
						}
					}
				}
			}
		});

		addNewMember.addEventListener("onClick",new EventListener<MouseEvent>() {
			@Override
			public void onEvent(MouseEvent event) throws Exception {
				Component memDetails = temp.getFellowIfAny("MemberDetailsPanel");
				if (memDetails != null) {
					Component firstChild = memDetails.getFirstChild();
					if(firstChild!=null)
					memDetails.removeChild(firstChild);
				} else	{

				}
				Include includeTag = new Include();
				includeTag.setSrc("AddNewMember.zul");
				includeTag.setMode("defer");
				// includeTag.setId("memDetailsPage");
				memDetails.appendChild(includeTag);
			}
		});
	}
}
