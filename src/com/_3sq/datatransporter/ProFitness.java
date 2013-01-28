package com._3sq.datatransporter;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.format.CellDateFormatter;

import com._3sq.daoimpl.MemberImpl;

public class ProFitness {

	public HashMap<Integer,LightWeightMember> getAllMembers() {
	
		String fname = "E:\\vishal\\VIshal Data\\ALl Gym Data\\Book1.xls";
		FileInputStream input = null;
		BufferedInputStream binput = null;
		POIFSFileSystem poifs = null;
		HashMap<Integer,LightWeightMember> allMembers = new HashMap<Integer,LightWeightMember>();
		try {
			input = new FileInputStream(fname);
			binput = new BufferedInputStream(input);
			poifs = new POIFSFileSystem(binput);

			// Biff8EncryptionKey.setCurrentUserPassword("MYPASSWORD");

			HSSFWorkbook workbook = new HSSFWorkbook(poifs);
			//
			HSSFSheet sheet = workbook.getSheetAt(0);
			int rows = sheet.getLastRowNum();
			
			allMembers = new  HashMap<Integer,LightWeightMember>(rows);
			
			System.out.println("rows:" + rows);
			String name="";
			String mobNo="";
			String dob=""; 
			for (int i = 0; i <= rows; i++) {
				
	

				HSSFRow row = sheet.getRow(i);
				if (row == null)
					continue;

				// Member Name
				HSSFCell cell = row.getCell(0);
				if (cell == null)
					break;
				HSSFRichTextString val = cell.getRichStringCellValue();
				name = val.toString();	
				// //Date Of Birth
				cell = row.getCell(1);
				if (cell != null) {
					try
					{
					double dVal = cell.getNumericCellValue();
					Date date = HSSFDateUtil.getJavaDate(dVal);

					dob = new CellDateFormatter(cell.getCellStyle()
							.getDataFormatString()).format(date);
					dob=dob.substring(0, dob.length() - 2);
					}catch(Exception e)
					{
					//	System.out.print("\n Row : " + (i+1) + " : ");
						dob= cell.getRichStringCellValue().toString();
						//System.out.print("Case 2 : block 2"+" : "+dob);
						
					}
				} else {
					// Add Blank Code
					dob="";
				}

				// Mobile No
				cell = row.getCell(2);
				if (cell != null) {
					try {
						Double dVal = cell.getNumericCellValue();
						long l = dVal.longValue();
						
						mobNo = String.valueOf(l);
					} catch (Exception e) {	//this is the case for Landline nu..

						//TODO : Do nothing rit nw
						System.out.print("\n Row : " + i + " : ");
					
						mobNo = cell.getRichStringCellValue().toString();
						System.out.print("Case 2 : block 3 : "+mobNo);
					}
				} else {
					// blank code
					mobNo="";
				}

				LightWeightMember temp = new LightWeightMember(i+1, name, dob, mobNo);
				allMembers.put(i+1, temp);
				
			}
			System.out.println(allMembers.get(0));
		} catch (Exception e) {
			System.err.println(e.toString());
		} finally {
			try {
				binput.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return allMembers;
	}
	
	private static ProFitness pObj;

	private ProFitness() {
		// TODO Auto-generated constructor stub
	}
		
//	public static void main(String[] args) {
//		
//		 HashMap<Integer,LightWeightMember> allMembers = getObject().getAllMembers();
//		 System.out.println(allMembers.size());
//		 MemberImpl member = MemberImpl.getmemberImpl();
//		 
//		 for(LightWeightMember temp : allMembers.values()){
//			System.out.println(temp.getMemberId());
//			 try {
//				member.addLightMember(temp);
//			} catch (SQLException e) {
//				System.out.println("*****************"+temp.getMemberId()+" NAME : "+temp.getMemberName()+" DOB "+temp.getDateOfBirth()+" MOB : "+temp.getMobileNumber());
//				e.printStackTrace();
//			}
//		 }
//	}

	public static ProFitness getObject() {
		if(pObj==null)
			return new ProFitness();
		else
			return pObj;
	}
	
	public static void main(String[] args) throws Exception {
		
		 HashMap<Integer,LightWeightMember> allMembers = getObject().getAllMembers();
		 System.out.println(allMembers.size());
		 MemberImpl member = MemberImpl.getmemberImpl();
		 
		 for(LightWeightMember temp : allMembers.values()){
			System.out.println(temp.getMemberId());
			 member.addLightMember(temp);
		 }
	}

}
