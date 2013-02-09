/**
 * 
 */
package com._3sq.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.zul.Scrollview;
import org.zkoss.zul.Button;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;
import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.daos.MemberDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.Member;

/**
 * @author VishalB
 * 
 */
public class MembersController extends SelectorComposer<Component> {

	
	private static MembersController singleInstance;
	private MemberDAO members;
	
	private ListModelList<LightWeightMember> listModel;
	
	@Wire private Listbox orig;
	@Wire private Listbox dup;
	@Wire private Button addNewMember;
	@Wire private Scrollview sView;
	@Wire private Textbox serachByName;
	@Wire private Window MemberDetailsPanel;
	
	public List<LightWeightMember> memberList;		//will get filled runtime based on the input requirement/
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	private static final long serialVersionUID = -6528261260274326242L;

	private GymImsImpl gymImplObj = GymImsImpl.getGymImsImpl(); 
	
	public static MembersController  getMemberControllerImpl() {
		if (singleInstance == null) {
			synchronized (GymPlanImpl.class) {
				if (singleInstance == null) {
					singleInstance = new  MembersController();
				}
			}
		}
		return singleInstance;
	}

	public MembersController()	{
		if(System.getProperty("validSession")!=null && System.getProperty("validSession").equals("") ==false )	{
			members = MemberImpl.getmemberImpl();
			memberList = gymImplObj.getAllActiveMembers();	
			singleInstance = this;
		}else{
			Executions.sendRedirect("/UI/Login.zul");
		}
	}

	@Listen("onCreate = #MemberDetailsPanel")
    public void onCreateWindow(Event event){
		System.out.println("Window registered.. : MemberDetailsController");
		gymImplObj.registerWindow("MemberDetailsPanel", MemberDetailsPanel);
    }

	@Listen("onChange = #serachByName")
	//Make this event as onChange after
    public void onSubmit(Event event){
		String name = serachByName.getValue();
		if(name.equals(""))	{
			orig.setVisible(true);
			dup.setVisible(false);
		}else
		{
			orig.setVisible(false);
			dup.setVisible(true);
		}
			
    }

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


	
	@Listen("onClick = #orig")
    public void onClickOfList(Event event){
		int memberId = 0;
		try	{
			memberId= memberList.get(orig.getSelectedIndex()).getMemberId();
		}catch(ArrayIndexOutOfBoundsException ae)	{

		}
		if (memberId != 0) {
			Window memberDetailsPanel = gymImplObj.getWindow("MemberDetailsPanel"); 
			
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
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		if(System.getProperty("validSession")!=null && System.getProperty("validSession").equals("") ==false )	{
			System.setProperty("validSession","");
			
			//searchByType.setSelectedIndex(0);
			if(listItemRenderer!=null)
				orig.setItemRenderer(listItemRenderer);

			listModel = new ListModelList<LightWeightMember>(memberList);
			listModel.setMultiple(false);

			orig.setModel(listModel);

			final Component temp = comp;
//			lb.addEventListener("onClick", new EventListener<MouseEvent>() {
//				@Override
//				public void onEvent(MouseEvent event) throws Exception {
//					System.out.println("IHHIihih");
//				}
//			});

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
		else{
			Executions.sendRedirect("/UI/Login.zul");
		}
	}
	
	public void addItemToRender(LightWeightMember newMember)	{
		listModel.add(newMember);
	}
}
