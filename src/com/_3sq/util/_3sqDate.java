/**
 * 
 */
package com._3sq.util;

import java.util.Date;

/**
 * @author VishalB
 * Use this class in case of Date Conversions
 */
public class _3sqDate {
	
	public static java.sql.Date utilDateToSqlDate(java.util.Date utilDate){
		java.sql.Date sqlDate = null;
		if(utilDate!= null)
			sqlDate  =  new java.sql.Date(utilDate.getTime());
		return sqlDate;
	}
	
	public static java.util.Date sqlDateToUtilDate(java.sql.Date sqlDate){
		java.util.Date utilDate = null;
		if(sqlDate!=null)
		 utilDate = new Date(sqlDate.getTime());
		return utilDate;
	}
}
