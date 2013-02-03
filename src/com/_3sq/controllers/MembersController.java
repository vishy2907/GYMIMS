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
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
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
	
	@Wire
	private Listbox lb;
	@Wire
	private Button addNewMember;
	@Wire 
	private Textbox serachByName;
	@Wire 
	private Combobox searchByType;
	
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
			memberList = gymImplObj.getAllMembers();	
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

	
	@Listen("onOK = #serachByName")
    public void onSubmit(Event event){
		System.out.println("On Entrer");
    }

	@Listen("onChange = #searchByType")
    public void onSelectedCriteriaChange(Event event){
		//First remove all elems
		listModel.removeAll(memberList);
		
		Comboitem selection = searchByType.getSelectedItem();
		if(selection.getLabel().equals("All"))
			memberList = gymImplObj.getAllMembers();
		else if(selection.getLabel().equals("Active"))
			memberList = gymImplObj.getAllActiveMembers();
		else
			memberList = gymImplObj.getAllInactiveMembers();
		
		listModel.addAll(memberList);
		
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


	
	@Listen("onClick = #lb")
    public void onClickOfList(Event event){
		int memberId = 0;
		try	{
			memberId= memberList.get(lb.getSelectedIndex()).getMemberId();
		}catch(ArrayIndexOutOfBoundsException ae)	{

		}
		if (memberId != 0) {
			Window memberDetailsPanel = gymImplObj.getWindow("MemberDetailsPanel"); 
			
			if (memberDetailsPanel != null) {
				Component firstChild = memberDetailsPanel.getFirstChild();
				if (firstChild != null) {
					System.out.println("ID OF CHILD : "+firstChild.getId());
					if(firstChild.getId().equals("memberInfo")==false)
						memberDetailsPanel.removeChild(firstChild);
					else{
						System.setProperty("MemberId", ""+memberId);
						firstChild.invalidate();
					}
				} else {
					System.setProperty("MemberId",""+ memberId);
					Window window = (Window)Executions.createComponents("MemberDetailsPages/MemberDetailInformation.zul", null, null);
					window.doEmbedded();
					memberDetailsPanel.appendChild(window);
				}
			}
		}
    }
	
	
	Window memberDetails;
	
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
	
		if(System.getProperty("validSession")!=null && System.getProperty("validSession").equals("") ==false )	{
			System.setProperty("validSession","");
			
			searchByType.setSelectedIndex(0);
			if(listItemRenderer!=null)
				lb.setItemRenderer(listItemRenderer);

			listModel = new ListModelList<LightWeightMember>(memberList);
			listModel.setMultiple(false);

			lb.setModel(listModel);

			final Component temp = comp;
			lb.addEventListener("onClick", new EventListener<MouseEvent>() {
				@Override
				public void onEvent(MouseEvent event) throws Exception {
					
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
		else{
			Executions.sendRedirect("/UI/Login.zul");
		}
	}
	
	public void addItemToRender(LightWeightMember newMember)	{
		listModel.add(newMember);
	}
}
