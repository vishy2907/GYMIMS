/**
 * 
 */
package com._3sq.controllers.memberdetailscontrollers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Window;

import com._3sq.daoimpl.MeasurementImpl;
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
	@Wire Tabpanels panaels;
	final DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);	

		//TODO : Load all measurement dates to build the UI
		int currMemberId = Integer.parseInt(System.getProperty("MemberId"));
		Date[] allPaymentDates = RegistrationPlanImpl.getRegistrationPlanImpl().getAllDatesOfHistoryPayment(currMemberId);

		
		if(allPaymentDates!=null)	{

			Tabpanel[] msrTabpanel = new Tabpanel[allPaymentDates.length];
			Tab[] msrTab = new Tab[allPaymentDates.length];

			int i=0;
			for(Date eachDate : allPaymentDates )	{
				msrTab[i] = new Tab();
				msrTabpanel[i] = new Tabpanel();

				msrTab[i].setLabel(df.format(eachDate));

				tabs.appendChild(msrTab[i]);
				panaels.appendChild(msrTabpanel[i]);
				i++;
			}
		}
	}

}
