/**
 * 
 */
package com._3sq;

import java.util.HashMap;

import org.zkoss.zul.Window;

/**
 * @author VishaB
 * This class is the API class for  <b>3 Square Soft-Tech </b> GYM - IMS Software.. 
 */
public class GymIms {

	/**
	 * @param args
	 */
	private HashMap<String,Window> m_hmWindowList;			//Will hold all the Window references which are available on Desktop.
	//Perform all initialization of Properties parameters here in the constructor itself..
	public GymIms()	{
		m_hmWindowList = new HashMap<String,Window>(10);
	}
	
	
	//API Methods...
	/**
	 * 
	 * @return the map of all available windows indexed by ID
	 */
	public HashMap<String,Window> getAllWindowMap()	{
		return m_hmWindowList;
	}
	/**
	 * Registers the Window to GYM-IMS
	 * @param sWindowId
	 * @param newWind
	 * @return true if window registered
	 */
	public boolean registerWindow(String sWindowId, Window newWind)	{
		m_hmWindowList.put(sWindowId,newWind);
		return true;
	}
	/**
	 * De-Registers the window from GYM-IMS
	 * @param sWindowId
	 * @return true if window registered
	 */
	public boolean deRegisterWindow(String sWindowId)	{
		m_hmWindowList.remove(sWindowId);
		return true;
	}
	/**
	 * Returns the window indexed by Window ID
	 * @param sWindowId
	 * @return
	 */
	public Window getWindow(String sWindowId)	{
		return m_hmWindowList.get(sWindowId);
	}
	

}
