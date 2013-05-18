/**
 * 
 */
package com._3sq.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import com._3sq.GymImsImpl;
import com._3sq.daoimpl.GymPlanImpl;
import com._3sq.daoimpl.MeasurementImpl;
import com._3sq.daoimpl.MemberImpl;
import com._3sq.daoimpl.RegistrationPlanImpl;
import com._3sq.domainobjects.GymPlan;
import com._3sq.domainobjects.MeasurementInfo;
import com._3sq.domainobjects.Member;
import com._3sq.domainobjects.RegistrationPlan;

/**
 * This is the Serializer class for 3Sq Soft-tech
 * @author VishalB
 *
 */
public class _3sqSerializer {
	private Collection<Member> members;
	private Collection<GymPlan> gymPlans;
	private Collection<RegistrationPlan> allRegs;
	private Collection<MeasurementInfo>  msrMntInfos;
	private SerializerMetaData srzrMetaData;
	private File backupFile;
	
	DateFormat df = new SimpleDateFormat("dd-MMM-yyyy hh-mm a");
	
	private void setUpSerializer()	{

		System.out.println("Serialization Metadata creation started...");
		
		// load all the data into the memory...
		members = MemberImpl.getmemberImpl().retrieveAllMembersForSerialization();
		gymPlans = GymPlanImpl.getGymPlanImpl().retrieveAllGymPlansForSerialization();
		allRegs = RegistrationPlanImpl.getRegistrationPlanImpl().retrieveAllRegInfoForSerialization();
		msrMntInfos = MeasurementImpl.getMeasurementImpl().retrieveAllMsrmntsForSerialization();
		
		//Setup meta data..
		srzrMetaData = new SerializerMetaData();
		
		srzrMetaData.setGymPlanCount(gymPlans.size());
		srzrMetaData.setMeasurementCount(msrMntInfos.size());
		srzrMetaData.setMemberCount(members.size());
		srzrMetaData.setRegistrationCount(allRegs.size());
		
		System.out.println(srzrMetaData.toString());

		System.out.println("Serialization Metadata creation Finished ...");
		
	}
	
	
	/**
	 * Will serialize the Data and will generate a 
	 */
	public void serializeData()	throws Exception{
	
		System.out.println("Serializarion Started...");
		setUpSerializer();
		FileOutputStream fileOut = null;
		ObjectOutputStream out =null;
		
		
		try
	      {
			 System.out.println("Serialization started : ");
			 
			 File backupDir = new File(GymImsImpl.getGymImsImpl().getBackupDirectory());
			 if(backupDir.exists() == false){
				 backupDir.mkdir();
			 }
			 fileOut = new FileOutputStream(backupDir+"/Backup - "+df.format(new Date())+".backup");
			 out = new ObjectOutputStream(fileOut);
			 
	         //First always Meta data...
	         out.writeObject(srzrMetaData);
	         
	         //2nd : Gym Plans
	         if(gymPlans!=null)
	         for(GymPlan gmpP : gymPlans){
	        	 out.writeObject(gmpP);
	         }
	         
	         //3rd : Member
	         if(members!=null)
	         for(Member member : members)	{
	        	 out.writeObject(member);
	         }
	         
	         //4th : Measurements
	         if(msrMntInfos!=null)
	         for(MeasurementInfo msrmnt : msrMntInfos)	{
	        	 out.writeObject(msrmnt);
	         }
	         
	         //5th Registration Plans
	         if(allRegs!=null)
	         for(RegistrationPlan regPlan : allRegs)	{
	        	 out.writeObject(regPlan);
	         }

	      }finally{
	    	  out.close();
	    	  fileOut.close();
	      }
		System.out.println("Serialization Finished Successfully...");
	}
	
	
	public void setBackupFile(File backup){
		backupFile = backup;
	}
	
	public void deserializeData()	throws Exception{
		if(backupFile == null || backupFile.exists() == false )
			throw new Exception("Backup file does not exists.");
		
		FileInputStream fileIn = null;
		ObjectInputStream input = null;
		
		try
		{
			
			fileIn = new FileInputStream(backupFile);
			input = new ObjectInputStream(fileIn);
			
			System.out.println("Deserialization Process started...");
			

			//Load meta data...
			SerializerMetaData srzrMetaData = (SerializerMetaData) input.readObject();

			//Retrieve gym plans..
			int gymPlanCount = srzrMetaData.getGymPlanCount();
			gymPlans = new ArrayList<GymPlan>(gymPlanCount);
			GymPlan gymPlan;
			for(int i=0;i<gymPlanCount;i++)	{
				 gymPlan = (GymPlan) input.readObject();
				 gymPlans.add(gymPlan);
			}
			GymPlanImpl.getGymPlanImpl().addAllDeserializedGymPlans(gymPlans);

			//Retrieve members data
			int membersCount = srzrMetaData.getMemberCount();
			members = new ArrayList<Member>(membersCount);
			Member member;
			for(int i=0;i<membersCount;i++)	{
				 member = (Member) input.readObject();
				 members.add(member);
			}
			MemberImpl.getmemberImpl().addDeserializedMembers(members);
			
			//Retrieve Measurements..
			int msrmntCount = srzrMetaData.getMeasurementCount();
			msrMntInfos = new ArrayList<MeasurementInfo>(msrmntCount);
			MeasurementInfo msrInfo;
			for(int i=0;i<msrmntCount;i++)	{
				msrInfo = (MeasurementInfo) input.readObject();
				msrMntInfos.add(msrInfo);
			}
			MeasurementImpl.getMeasurementImpl().addAllDeserializedMeasurements(msrMntInfos);

			//Retrieve Registration Data..
			int registrationCount = srzrMetaData.getRegistrationCount();
			allRegs = new ArrayList<RegistrationPlan>(registrationCount);
			RegistrationPlan regPlan;
			for(int i=0;i<registrationCount;i++)	{
				 regPlan = (RegistrationPlan) input.readObject();
				 allRegs.add(regPlan);
			}
			RegistrationPlanImpl.getRegistrationPlanImpl().addAllDeserializedGymPlans(allRegs);
			
			System.out.println("Deserialization Process Completed....");
			
		}
		finally{
			input.close();
			fileIn.close();
		}
	}
	public static void main(String[] args) throws Exception{
		_3sqSerializer srzr1 = new _3sqSerializer();
		srzr1.serializeData();
		srzr1.setBackupFile(new File("C:/Backup/Backup - 16-Feb-2013 10-33.backup"));
		//srzr1.deserializeData();
	}
}
