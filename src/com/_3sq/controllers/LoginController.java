
package com._3sq.controllers; 
 
import java.io.IOException;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
 
public class LoginController extends SelectorComposer<Window> {
 
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Wire
    Textbox userId;
	@Wire
    Textbox pwd;
	
    @Wire
    Label errorLabel;
     
    @Listen("onClick=#ok")
    public void submit() {
    	String uId = userId.getValue();
    	String pass = pwd.getValue();
    	
    	if(uId == null || uId.equals("")==true || pass == null || pass.equals("")==true)	{
    		errorLabel.setValue("Invalid Inputs Provided.");
    		errorLabel.setStyle("color:rgb(255,0,0);");
    	}
    	else	{
    		
    		errorLabel.setValue("");
    		//Temp. I am assuming UID and PASSWORDS are
    		// mahesh mahesh
    		
    		
				Executions.sendRedirect("/UI/homepage.zul");
			
    	
    	}
    }

    @Listen("onClick=#cancel")
    public void cancel() {
        pwd.setText("");
        userId.setText("");
    }
}