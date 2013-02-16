/**
 * 
 */
package com._3sq.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

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
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;
import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.daos.MemberDAO;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.Member;
import com._3sq.util.RendererClasses;

/**
 * @author VishalB
 * 
 */
public class MembersController extends SelectorComposer<Component> {

	
	private static MembersController singleInstance;
	private MemberDAO members;
	
	private ListModelList<LightWeightMember> listModel;
	private ListModelList<LightWeightMember> tempListModel;
	
	
	@Wire private Listbox orig;
	@Wire private Listbox dup;
	@Wire private Button addNewMember;
	@Wire private Scrollview sView;
	@Wire private Textbox serachByName;
	@Wire private Window MemberDetailsPanel;
	
	public ArrayList<LightWeightMember> memberList;		//will get filled runtime based on the input requirement/
	private ArrayList<LightWeightMember> subMembers;
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

	@Listen("onChanging = #serachByName")
	public void onChange()	{
		String name = serachByName.getValue();
		if(name.equals(""))	{
			orig.setVisible(true);
			dup.setVisible(false);
			sView.invalidate();
		}
	}
	@Listen("onOK = #serachByName")
	//Make this event as onChange after
    public void onSubmit(Event event){
		String name = serachByName.getValue();
		if(name.equals(""))	{
			orig.setVisible(true);
			dup.setVisible(false);
			sView.invalidate();
		}else
		{
			orig.setVisible(false);
			dup.setVisible(true);
			GymImsImpl gym =GymImsImpl.getGymImsImpl(); 
			subMembers = gym.getSubsetOfMembersBasedOnName(name); 
			tempListModel = new ListModelList<LightWeightMember>(subMembers);
			tempListModel.setMultiple(false);

			dup.setModel(tempListModel);
			dup.invalidate();
			sView.invalidate();
		}
    }


		

	@Listen("onClick = #orig,#dup")
    public void onClickOfList(Event event){
		GymImsImpl gym = GymImsImpl.getGymImsImpl();
		
		int memberId = 0;
		try	{
			if(orig.isVisible())	
				memberId = listModel.get(orig.getSelectedIndex()).getMemberId();
			else
				memberId = tempListModel.get(dup.getSelectedIndex()).getMemberId();
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
		
		System.out.println("invalidating...");

		if(System.getProperty("validSession")!=null && System.getProperty("validSession").equals("") ==false )	
		{
			System.setProperty("validSession","");
			
			//searchByType.setSelectedIndex(0);
			if(RendererClasses.listItemRendererForListBox!=null)	{
				orig.setItemRenderer(RendererClasses.listItemRendererForListBox);
				dup.setItemRenderer(RendererClasses.listItemRendererForListBox);
			}

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
	
	public void RefreshListModel(boolean addNew,LightWeightMember newM)	{
		
		//Rit nw, Call is goin to be coming only from t20 placess
		if(addNew==true)	{
			listModel.add(newM);
		}else{
			int index=-1;
			for(LightWeightMember temp : GymImsImpl.getGymImsImpl().getAllMembers().values())
			{	index++;
				if(temp==newM)		{
					System.out.println("Same Index"+temp.getMemberId());
					break;
				}
			}
			listModel.add(index, newM);
		}
		gymImplObj.getAllActiveMembers().add(newM);
		orig.invalidate();
	}
}
