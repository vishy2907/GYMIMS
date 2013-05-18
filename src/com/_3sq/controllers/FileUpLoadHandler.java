package com._3sq.controllers;

import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox.Button;
import org.zkoss.zul.Window;

import com._3sq.util._3sqBackupHandler;

public class FileUpLoadHandler extends SelectorComposer <Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4287913283455429341L;
	@Wire private Button restoreBackup;
	@Wire private Window backup;
	
	@Listen("onUpload = #restoreBackup")	
	public void onrestoreBackup(UploadEvent event)	{
		System.out.println(":BackupTaken");
		final Media media = event.getMedia();
		System.out.println(media.getName());

		  

		  Messagebox.show("Are you sure? Do you want to restore database?", "Question", Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION, new org.zkoss.zk.ui.event.EventListener() {
			    private Event evt;

				public void onEvent(Event evt) throws InterruptedException {
			        this.evt = evt;
					if (evt.getName().equals("onOK")) {
			            _3sqBackupHandler backup = new _3sqBackupHandler();
			            backup.restoreBackupFile(media.getName());
			            MembersController.getMemberControllerImpl().resetController();
			            Clients.showNotification("Backup Restored Sucessfully.", true);
			        }  else {
			            System.out.println("Restoration Canceled.....");
			        }
					backup.detach();
			    }
			});
		  
	}
	
}
