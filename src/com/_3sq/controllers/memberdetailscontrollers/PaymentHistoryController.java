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
import com._3sq.daoimpl.RegistrationPlanImpl;

/**
 * @author VishalB
 * Also Internally referred as Registration Controller Hander cz, takes the data from registration tables..
 * This class handles all the events fired on Payment Details History page of Payment Details Tab
 */
public class PaymentHistoryController extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Wire Window winPaymentHistory;
	@Wire Tabbox tabboxPayment;
	@Wire Tabs tabs;
	
	Include inc1;
	@Wire Window include;
	
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);	

		final GymImsImpl gymApp = GymImsImpl.getGymImsImpl(); 
		int currMemberId = gymApp.getCurrMember().getMemberID();
		Vector<Date> allPaymentDates = RegistrationPlanImpl.getRegistrationPlanImpl().getAllDatesOfHistoryPayment(currMemberId);
		
		System.out.println("all payment date..."+allPaymentDates);
		if(allPaymentDates==null)
			return;
		for(Date d : allPaymentDates)	{
			System.out.println(d);
		}
		if(allPaymentDates!=null)	{
			gymApp.setCurrSelectedDate(allPaymentDates.get(0));
			inc1= new Include();
			inc1.setSrc("/UI/MemberDetailsPages/PaymentDateWise.zul");
			inc1.setMode("defer");
			include.appendChild(inc1);

			Tab[] msrTab = new Tab[allPaymentDates.size()];

			int i=0;
			for(final Date eachDate : allPaymentDates )	{
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
	}

}
