/**
 * 
 */
package com._3sq.controllers;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Window;

import com._3sq.daoimpl.MemberImpl;
import com._3sq.domainobjects.Member;

/**
 * @author shani
 * 
 */
public class MemberDetailsController extends GenericForwardComposer<Component> {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1427914022897958743L;

	private int memberId;
	private Window mdWindow;

	public void doAfterCompose(Component comp) throws Exception {
		 int memberId = 0;
		 Object memId = Executions.getCurrent().getAttribute("memberId"); 
		 if(memId != null)	{
			 memberId = (int)memId;
			 Member member = MemberImpl.getmemberImpl().getMember(memberId);
			 mdWindow = (Window)comp.getFirstChild();
			 mdWindow.setVisible(true);
			}
	}

}
