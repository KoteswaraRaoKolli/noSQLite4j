package org.nosqlite4j.core.ddl;

import java.io.File;
import java.io.IOException;

import org.json.JSONException;
import org.nosqlite4j.core.Configuration;
import org.nosqlite4j.core.MetaStore;

public class DeleteTable {
	private String TableName;
	
	public DeleteTable(String tablename) throws JSONException, IOException
	{
		TableName = tablename;
	}

	public void setTableName(String tablename)
	{
	 TableName = tablename;
	}
	public void delete() throws JSONException, IOException
	{
		MetaStore meta = new MetaStore();
		meta.setTable(TableName);
		long StartTime = System.currentTimeMillis();
	if(meta.checkTable())
	{
	 	meta.deleteEntry();
		Configuration config = new Configuration();
		String DBLocation = config.getDBLoc();
		File directory = new File(DBLocation+"/"+TableName); 
		if(directory.exists())
		{
		  delete(directory);	
		}
		System.out.println("Table Deleted");
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken : "+(endTime-StartTime)+" ms");
	}
	else
	{
		System.err.print("Table Does not Exist");
	}
		
	}
	 public static void delete(File file)
		    	throws IOException{
		 
		    	if(file.isDirectory()){
		    		if(file.list().length==0){
		 
		    		   file.delete();
		 
		    		}else{
		 
		        	   String files[] = file.list();
		 
		        	   for (String temp : files) {
		        	      File fileDelete = new File(file, temp);
		 
		        	     delete(fileDelete);
		        	   }
		 
		        	   if(file.list().length==0){
		           	     file.delete();
		        	   }
		    		}
		 
		    	}else{
		    		file.delete();
		    	}
	 }

}
