/**
 * 
 */
package com._3sq;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

import org.zkoss.zul.Window;

import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.datatransporter.LightWeightMember;
import com._3sq.domainobjects.GymPlan;
import com._3sq.domainobjects.Member;
import com._3sq.messaging.MessageSender;

/**
 * @author VishaB
 * This class is the API class for  <b>3 Square Soft-Tech </b> GYM - IMS Software.. 
 */
public class GymImsImpl {


	  private static GymImsImpl singleInstance;
	  
	  public static GymImsImpl  getGymImsImpl() {
	    if (singleInstance == null) {
	      synchronized (GymImsImpl.class) {
	        if (singleInstance == null) {
	          singleInstance = new  GymImsImpl();
	        }
	      }
	    }
	    return singleInstance;
	  }
	
	private GymImsImpl()	{
		m_hmWindowList = new HashMap<String,Window>(10);
		// initiate property reader
		System.out.println("Loading properties files...");
		propertiesLoader();
		setProperties();
		//load the member data
		System.out.println("Loading all member Details...");
		loadAllMembersData();
		//load all plan names...
	}
	
	private HashMap<String,Window> m_hmWindowList;			//Will hold all the Window references which are available on Desktop.
	private Properties m_pGymProps;
	private TreeMap<Integer,LightWeightMember> m_tmALlMembers;

	//properties related stuff..
	private String m_sMessageCenterNo;
	private String m_sComPortNo;
	private String m_sUserId;
	private String m_sPassword;
	private String m_sWelcomeMessage;
	private String m_sDbmachinename;
	private String m_sDbport;
	private String m_sBackupDirectory;
	
	
//	private List<LightWeightMember> allMembers;
	private ArrayList<LightWeightMember> allActiveMembers;
//	private List<LightWeightMember> allInActiveMembers;
	
	
	private Member m_mCurrMember;
	private Date  m_dCurrSelDate;
	private boolean m_bMemberStatusFlag = true;
	


	MessageSender sms = new MessageSender(MessageSender.SYNCHRONOUS);
	//
	HashMap<Integer,GymPlan> allPlans;

	
	public boolean validateUser(String userID, String password){
		if(userID.equals(getUserId()) && password.equals(getPassword()))
			return true;
		else 
			return false;
	}
	
	
	//Perform all initialization of Properties parameters here in the constructor itself..
	
	private void propertiesLoader() {
		try {
			if(m_pGymProps==null){
				m_pGymProps = new Properties();
				String propFileName = "gym.properties";
				InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName);

				if (inputStream == null)
				{
					throw new FileNotFoundException("property file '" + propFileName
							+ "' not found in the classpath");
				}
				else	{
					m_pGymProps.load(inputStream);

				}
			}
			} catch (Exception ex) {
				ex.printStackTrace();
		}
	}
	
	private void setProperties()	{
		if(m_pGymProps!=null)	{
			//set all properties here.
			//TODO : Remaining things... of... DB 
			setComPortNo(m_pGymProps.getProperty("comport","COM4"));
			setMessageCenterNo(m_pGymProps.getProperty("messagecenterno","919021000500"));
			setUserId(m_pGymProps.getProperty("userid","shani"));
			setPassword(m_pGymProps.getProperty("password","rocks"));
			setWelcomeMessage(m_pGymProps.getProperty("welcomeMessage","Welcome to gym"));
			setDbmachinename(m_pGymProps.getProperty("dbmachinename","localhost"));
			setDbport(m_pGymProps.getProperty("dbport","1521"));
			setBackupDirectory(m_pGymProps.getProperty("backupdirectory","C:/Backup/"));
		}
	}

	public Properties getPropertiesFileForGym()	{
		return m_pGymProps;
	}

	public String getDbmachinename() {
		return m_sDbmachinename;
	}

	public void setDbmachinename(String dbmachinename) {
		this.m_sDbmachinename = dbmachinename;
	}

	public String getDbport() {
		return m_sDbport;
	}

	public void setDbport(String dbport) {
		this.m_sDbport = dbport;
	}

	private void setWelcomeMessage(String welcomeMessage) {
		m_sWelcomeMessage = welcomeMessage;
	}
	public String getWelcomeMessage()	{
		return m_sWelcomeMessage;
	}
	

	public String getMessageCenterNo() {
		return m_sMessageCenterNo;
	}


	public void setMessageCenterNo(String messageCenterNo) {
		this.m_sMessageCenterNo = messageCenterNo;
	}


	public String getComPortNo() {
		return m_sComPortNo;
	}


	public void setComPortNo(String comPortNo) {
		this.m_sComPortNo = comPortNo;
	}


	public String getUserId() {
		return m_sUserId;
	}


	public void setUserId(String userId) {
		this.m_sUserId = userId;
	}


	public String getPassword() {
		return m_sPassword;
	}


	public void setPassword(String password) {
		this.m_sPassword = password;
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
	
	
	//Membership related stuff/
	/**
	 * loads all the members from the db and feels the details..
	 */
	private synchronized void loadAllMembersData() {
		if (allActiveMembers == null) {
			m_tmALlMembers = MemberImpl.getmemberImpl().loadPartialMembers(null);
			allActiveMembers  = new ArrayList<LightWeightMember>(m_tmALlMembers.values());
		}
	}
	
	public void resetAllMemberData()	{
		System.out.println("Resetting Data...");
		m_tmALlMembers = null;
		m_tmALlMembers  = MemberImpl.getmemberImpl().loadPartialMembers(null);
		allActiveMembers  = null;
		allActiveMembers  = new ArrayList<LightWeightMember>(m_tmALlMembers.values());
	}
	
	public ArrayList<LightWeightMember> rebuildMemberList()	{
		allActiveMembers = new ArrayList<LightWeightMember>(m_tmALlMembers.values()); 
		return allActiveMembers;
	}
	

	/**
	 * Useful in name search...
	 * @param name
	 * @return
	 */
	public ArrayList<LightWeightMember> getSubsetOfMembersBasedOnName(String name)	{
		if(allActiveMembers==null)
			return null;
		name = name.toLowerCase();
		ArrayList<LightWeightMember>tempList = new ArrayList<LightWeightMember>();
		for(LightWeightMember temp : allActiveMembers)	{
			if(temp.getMemberName().toLowerCase().contains(name))
				tempList.add(temp);
		}
		return tempList;
	}
//	/**
//	 *  @return all members of gym
//	 */
//	public List<LightWeightMember> getAllMembers()	{
//		return allActiveMembers;
//	}
	/**
	 *  @return all active members
	 */
	public ArrayList<LightWeightMember> getAllActiveMembers()	{
		return allActiveMembers;
	}
//	/**
//	 *  @return all In - active members
//	 */
//	public List<LightWeightMember> getAllInactiveMembers()	{
//		return allInActiveMembers;
//	}
	
	public void sendMessgeToSingleUser(String mobNo, String message)	{
		
		sms.sendMessage(mobNo,message,this.getComPortNo(),this.getMessageCenterNo());
	}

	public void sendWelcomeMessage(String contactNumber) {
		sms.sendMessage(contactNumber,this.getWelcomeMessage(),this.getComPortNo(),this.getMessageCenterNo());
	}
	

	
	//Load all the plan names here at the startup only...
	public HashMap<Integer,GymPlan> getGymPlans ()	{
		if(allPlans==null)	{
			allPlans=GymPlanImpl.getGymPlanImpl().getAllGymPlans();
		}
			return allPlans;
	}
	//Load given Plan Based on ID
		public GymPlan getGymPlan (int index)	{
			if(allPlans!=null)	{
				return allPlans.get(index);
			}
			else	{
				return getGymPlans().get(index);
			}
		}
		
	public Member getCurrMember() {
		return m_mCurrMember;
	}

	public void setCurrMember(Member currMember) {
		this.m_mCurrMember = currMember;
	}

	public Date getCurrSelectedDate() {
		return m_dCurrSelDate;
	}

	public void setCurrSelectedDate(Date currSelDate) {
		this.m_dCurrSelDate = currSelDate;
	}
	
	public void resetCurrSelectedDate(){
		m_dCurrSelDate = null;
	}
	
	
	public String getBackupDirectory() {
		return m_sBackupDirectory;
	}

	public void setBackupDirectory(String backupDirectory) {
		this.m_sBackupDirectory = backupDirectory;
	}

	//is it is true, it means u are working in an Active Members Part, else in an inactive member part 
	public boolean isMemberStatusFlag() {
		return m_bMemberStatusFlag;
	}

	public void setMemberStatusFlag(boolean bMemberStatusFlag) {
		this.m_bMemberStatusFlag = bMemberStatusFlag;
	}

	public TreeMap<Integer, LightWeightMember> getAllMembers() {
		return m_tmALlMembers;
	}

}
