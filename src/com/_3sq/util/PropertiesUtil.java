package com._3sq.util;



import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;
public class PropertiesUtil
{

 private static Properties props;

 static
 {
   props = new Properties();
   try
   {
     PropertiesUtil util = new PropertiesUtil();
     props = util.getPropertiesFromClasspath("gym.properties");
   }
   catch (FileNotFoundException e)
   {
     e.printStackTrace();
   }
   catch (IOException e)
   {
     e.printStackTrace();
   }
 }

 // private constructor
 private PropertiesUtil()
 {
 }

 public static void main(String[] ars){
	 
 }
 public static String getProperty(String key)
 {
   return props.getProperty(key).trim();
 }

 public static Set<Object> getkeys()
 {
   return props.keySet();
 }

 /**
48
  * loads properties file from classpath
49
  *
50
  * @param propFileName
51
  * @return
52
  * @throws IOException
53
  */
 private Properties getPropertiesFromClasspath(String propFileName)
                                                                   throws IOException
 {
   Properties props = new Properties();
   InputStream inputStream =
       this.getClass().getClassLoader().getResourceAsStream(propFileName);
   if (inputStream == null)
   {
     throw new FileNotFoundException("property file '" + propFileName
         + "' not found in the classpath");
   }

   props.load(inputStream);
   return props;
 }
}
