package com._3sq.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

public class LoginWindowController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	
	@Wire private Textbox txtUserName;
	@Wire private Textbox txtUserPass;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		System.out.println(""+txtUserName);
	}

}
