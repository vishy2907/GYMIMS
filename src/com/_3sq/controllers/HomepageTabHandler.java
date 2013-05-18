/**
 * 
 */
package com._3sq.controllers;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;

import org.zkoss.util.media.AMedia;
import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Fileupload;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Toolbarbutton;
import org.zkoss.zul.Window;

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
	@Wire private Toolbarbutton backupManager;
	
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
	
	@Listen("onClick = #backupManager")
	public void onBackupManager(Event event) throws FileNotFoundException	{
		System.out.println("Backup Manager invoked...");
		Window window = (Window)Executions.createComponents("/UI/BackupHandler.zul", null, null);
		//Register Window
		window.doModal();
	}
	
		
}
