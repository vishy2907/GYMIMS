/**
 * 
 */
package com._3sq.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.util.GenericForwardComposer;
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

/**
 * @author VishalB
 * 
 */
public class MembersController extends GenericForwardComposer<Component> {

	/**
	 * 
	 */

	private Listbox lb;
	private Button addNewMember;

	private static final long serialVersionUID = -6528261260274326242L;
	private MemberDAO members;

	List<LightWeightMember> tempList;

	Window memberDetails;

	public MembersController() {
		members = MemberImpl.getmemberImpl();
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

		lb.setItemRenderer(new ListitemRenderer<LightWeightMember>() {
			@Override
			public void render(Listitem item, LightWeightMember data, int index)
					throws Exception {

				item.appendChild(new Listcell("" + data.getMemberId()));
				item.appendChild(new Listcell(data.getMemberName()));
				item.appendChild(new Listcell(data.getDateOfBirth()));
			}
		});

		getAllMembers();
		ListModelList<LightWeightMember> tempModel = new ListModelList<LightWeightMember>(
				tempList);
		tempModel.setMultiple(false);

		lb.setModel(tempModel);

		final Component temp = comp;
		lb.addEventListener("onClick", new EventListener<MouseEvent>() {
			@Override
			public void onEvent(MouseEvent event) throws Exception {
				int memberId = tempList.get(lb.getSelectedIndex()).getMemberId();

				if (memberId != 0) {
					Component memDetails = temp.getFellowIfAny("memDetails");
					if (memDetails != null) {
						Component firstChild = memDetails.getFirstChild();
						System.out.println("First Child :" + firstChild);
						if (firstChild != null) {
							//memDetails.removeChild(firstChild);
							((Include)firstChild).setDynamicProperty("memberId", memberId);
							firstChild.invalidate();
						} else {
							Include includeTag = new Include();

							includeTag.setSrc("MemberDetail.zul");
							includeTag.setMode("defer");
							includeTag.setDynamicProperty("memberId", memberId);
							// includeTag.setId("memDetailsPage");

							memDetails.appendChild(includeTag);

							System.out.println("Added");
						}
					}
				}
			}
		});

		addNewMember.addEventListener("onClick",
				new EventListener<MouseEvent>() {
					@Override
					public void onEvent(MouseEvent event) throws Exception {
						Component memDetails = temp
								.getFellowIfAny("memDetails");
						if (memDetails != null) {
							memDetails.removeChild(memDetails.getFirstChild());
							System.out.println("I am removed...");
						} else
							System.out.println("No need to remove");

						Include includeTag = new Include();
						includeTag.setSrc("addMember.zul");
						includeTag.setMode("defer");
						// includeTag.setId("memDetailsPage");
						memDetails.appendChild(includeTag);
						System.out.println("Added Zul page");
					}
				});
	}

}
