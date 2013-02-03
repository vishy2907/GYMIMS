package com._3sq.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;

public class MessageSenderController extends SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Wire Textbox mobNo;
	@Wire Textbox msg;
	@Wire Window sendSMS;


	@Listen("onClick = #sendMsg")
    public void onSendMessage(Event event){
		System.out.println("Sending message to : "+mobNo.getValue());
		GymImsImpl.getGymImsImpl().sendMessgeToSingleUser(mobNo.getValue(), msg.getValue());
		sendSMS.detach();
	}
}
