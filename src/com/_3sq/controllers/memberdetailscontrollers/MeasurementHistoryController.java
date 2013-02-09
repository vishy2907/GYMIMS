/**
 * 
 */
package com._3sq.controllers.memberdetailscontrollers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;
import com._3sq.daoimpl.MeasurementImpl;

/**
 * @author VishalB
 ** This class handles all the events fired on Measurement History of Measurement History Tab
 */
public class MeasurementHistoryController extends SelectorComposer<Component> {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;

	public MeasurementHistoryController()	{
		
	}
	
	@Wire Window winMeasurementHistory;
	@Wire Tabbox tabboxMeasurement;
	@Wire Tabs tabs;
	Include inc1;
	@Wire Window include;
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");

	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);	

		final GymImsImpl gymApp = GymImsImpl.getGymImsImpl(); 
		int currMemberId = gymApp.getCurrMember().getMemberID();
		Vector<Date> allMsrmntDates = MeasurementImpl.getMeasurementImpl().getAllMeasurementDates(currMemberId);
		
		if(allMsrmntDates!=null)	{
			gymApp.setCurrSelectedDate(allMsrmntDates.get(0));
			inc1= new Include();
			inc1.setSrc("/UI/MemberDetailsPages/MsrmntDateWise.zul");
			inc1.setMode("defer");
			include.appendChild(inc1);
			
			Tab[] msrTab = new Tab[allMsrmntDates.size()];

			int i=0;
			for(final Date eachDate : allMsrmntDates )	{
				msrTab[i] = new Tab();
				
				msrTab[i].setLabel(df.format(eachDate));

				tabs.appendChild(msrTab[i]);
				msrTab[i].addEventListener("onClick", new EventListener<Event>() {
					@Override
					public void onEvent(Event event) throws Exception {
						gymApp.setCurrSelectedDate(eachDate);
						include.getFirstChild().invalidate();
					}
				});
				i++;
			}
			
		}
		else	{
			if(include.getFirstChild()!=null)
				include.getFirstChild().detach();
			return;
		}
	}
}
