
package com._3sq.controllers; 
 
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com._3sq.GymImsImpl;
 
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
     
    @Listen("onOK = #pwd,#userId")
    public void onEnterKey()	{
    	submit();
    }

    @Listen("onClick = #ok")
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
    		GymImsImpl gym = GymImsImpl.getGymImsImpl();
    		if(gym.validateUser(uId, pass))	{
    			System.setProperty("validSession", "true");    
    			Executions.sendRedirect("/UI/homepage.zul");
    		}
    		else	{
    			errorLabel.setValue("Invalid Credentials.");
    			userId.setValue("");
    			pwd.setValue("");
        		errorLabel.setStyle("color:rgb(255,0,0);");
    		}
    		
    	}
    }

    
    @Listen("onClick=#cancel")
    public void cancel() {
        pwd.setText("");
        userId.setText("");
    }
}