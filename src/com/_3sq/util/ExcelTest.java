package com._3sq.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
 
public class ExcelTest {
 
    public static void main(String[] args) {
 
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            Connection con = DriverManager.getConnection("jdbc:odbc:Excel2");
            Statement st = con.createStatement();
            System.out.println(con);
            ResultSet rs = st.executeQuery("Select * from [Sheet1$]");
 
            ResultSetMetaData rsmd = rs.getMetaData();
            int numberOfColumns = rsmd.getColumnCount();
            int i1=0;
            while (rs.next() && i1<10) {
 
            
            for (int i = 1; i <= numberOfColumns; i++) {
               if (i > 1)
                    System.out.print(", ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue);
                }
                System.out.println("");
            }
 
            st.close();
            con.close();
 
        } catch (Exception ex) {
            System.err.print("Exception: ");
            System.err.println(ex.getMessage());
        }
    }
}