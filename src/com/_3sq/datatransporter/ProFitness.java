package com._3sq.datatransporter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com._3sq.daoimpl.MemberImpl;

public class ProFitness {

	public HashMap<Integer,LightWeightMember> getAllMembers() {

		String fname = "E:\\vishal\\VIshal Data\\ALl Gym Data\\Book1.xls";
		HashMap<Integer,LightWeightMember> allMembers = new HashMap<Integer,LightWeightMember>();
		try	{
			
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection con = DriverManager.getConnection("jdbc:odbc:Excel2");
			Statement st = con.createStatement();
			System.out.println(con);
			ResultSet rs = st.executeQuery("Select * from [Sheet1$]");

			ResultSetMetaData rsmd = rs.getMetaData();
			int counter=0;
			int memberID = 1;
			String name = "";
			Date dob = null;
			long mobNo = 0l;
			
			while (rs.next() ) {
counter++;
					try	{
						name = rs.getString(1);
					
						dob = rs.getDate(2);
						
						mobNo = rs.getLong(3);
					}catch(Exception e){
						System.out.println("Prob.........................");
						break;
					}
					//System.out.println("\n"+memberID+ ": " +name +" : "+dob+" : "+mobNo );	

					LightWeightMember temp = new LightWeightMember(memberID, name, dob, ""+mobNo);
					allMembers.put(memberID, temp);
					memberID++;
			}
				System.out.println("Counter : "+(counter));
			st.close();
			con.close();

		} catch (Exception ex) {
			System.err.print("Exception: ");
			System.err.println(ex.getMessage());
		}
		return allMembers;
	}
		private static ProFitness pObj;
	
		public static ProFitness getObject() {
			if(pObj==null)
				return new ProFitness();
			else
				return pObj;
		}

		public static void main(String[] args) throws Exception {

			HashMap<Integer,LightWeightMember> allMembers = getObject().getAllMembers();
			System.out.println("SIZE OF HASH MAP : "+allMembers.size());
			MemberImpl member = MemberImpl.getmemberImpl();

			for(LightWeightMember temp : allMembers.values()){
				//System.out.println(temp.getMemberId());
				member.addLightMember(temp);
			}
		}

	}
