/**
 * 
 */
package com._3sq.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Tab;

import com._3sq.GymImsImpl;

/**
 * @author shani
 *
 */
public class HomepageTabHandler extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param args
	 */
	@Wire private Tab memberShipMgmt; 	
	@Wire private Tab reports; 			
	@Wire private Tab inactiveMemberMgmt;
	
	@Listen("onClick = #memberShipMgmt")
	public void onMembershipMgmt(Event event)	{
		System.out.println("On members...");
		GymImsImpl.getGymImsImpl().setMemberStatusFlag(true);
	}
	
	@Listen("onClick = #inactiveMemberMgmt")
	public void onInactiveMembershipMgmt(Event event)	{
		System.out.println("On Inactive members...");
		GymImsImpl.getGymImsImpl().setMemberStatusFlag(false);
	}
	
	
}
