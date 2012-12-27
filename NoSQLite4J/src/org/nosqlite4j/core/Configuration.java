package org.nosqlite4j.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class Configuration {
	
	String DBLocation;
	
	public Configuration()
	{
		String tempStr[];
		 try{
			  
			  FileInputStream fstream = new FileInputStream("noSQL4j-config");
			  DataInputStream in = new DataInputStream(fstream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null)
			  {
				  if(strLine.startsWith("DBLocation"))
				  {
					  tempStr = strLine.split("=");
					  this.DBLocation = tempStr[1];
					  
				  } 
			  }
			  in.close();
			  
			
			    }catch (Exception e){
			    	e.printStackTrace();
			  }
		 
	}
	
	public String getDBLoc()
	{
	 return this.DBLocation;	
	}
	

}
