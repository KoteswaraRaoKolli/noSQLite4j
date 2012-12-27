package org.nosqlite4j.core.dml;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONException;
import org.json.JSONObject;
import org.nosqlite4j.core.DBManager;

public class Get extends DBManager {
	private String Table;
	private String ColumnFamily;
	private String Qualifier;
	private String Key;
	private String Result;
	public Get(String table, String colfam, String qualifier, String key)
	{
		Table = table;
		ColumnFamily = colfam;
		Qualifier = qualifier;
		setKey(key);
	}
	public Get(String table, String colfam, String qualifier)
	{
		Table = table;
		ColumnFamily = colfam;
		Qualifier = qualifier;
	}
	public Get(String table, String colfam)
	{
		Table = table;
		ColumnFamily = colfam;
	}
	public String get() throws IOException, JSONException, NullPointerException
	{
		
		try{
		long StartTime = System.currentTimeMillis();
		String keyvalue = null;
		if(Table == null)
		{
			System.err.println("Table name not set");
		}
		else if(ColumnFamily == null)
		{
			System.err.println("Column family not set");
		}
		else{
			keyvalue = read(Table);
			if(keyvalue.contains(ColumnFamily))
			{
				String DBloc = getDBloc();
				Fetch(DBloc);
			
			}
			else{
				System.err.println("Column family does not exist");
			}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken : "+(endTime-StartTime)+" ms");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Result;
	
	}
	
	protected void Fetch(String DBloc) throws IOException, JSONException
	{
		 String found = "";
		 JSONObject obj;
		 if(Key == null && Qualifier == null)
		 {
			 File file = new File(DBloc+"/"+Table+"/"+ColumnFamily+"/");
			 read(file);
		 }
		 else if(Key == null)
		 {
		  FileInputStream fistream = new FileInputStream(DBloc+"/"+Table+"/"+ColumnFamily+"/"+Qualifier+"/");
		  DataInputStream in = new DataInputStream(fistream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  while ((strLine = br.readLine()) != null)
		  {
			 found = found + strLine+"\n";
		  }
		  Result = found;
		  in.close();
		 }
		 else{
			 FileInputStream fistream = new FileInputStream(DBloc+"/"+Table+"/"+ColumnFamily+"/"+Qualifier+"/");
			  DataInputStream in = new DataInputStream(fistream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null)
			  {
				obj = new JSONObject(strLine);
				if(obj.get(Key) != null)
				{
					found = found+obj.get(Key)+"\n"; 
				}
			  }
			  Result = found;
			  in.close();
		 }
		  

	}
	
	protected void read(File file) throws IOException{
			 String found = "";
	    	if(file.isDirectory()){
	    		
	    		if(file.list().length==0){
	 
	    		}else{
	        	   String files[] = file.list();
	 
	        	   for (String temp : files) {
	        	      File fileDelete = new File(file, temp);
	        	     read(fileDelete);
	        	   }
	 
	    		}
	 
	    	}else{
	    		FileInputStream fistream = new FileInputStream(file);
  			  DataInputStream in = new DataInputStream(fistream);
  			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
  			  String strLine;
  			 while ((strLine = br.readLine()) != null)
			  {
				 found = found + strLine+"\n";
			  }
			  Result = found;
  			  in.close();
	    	}
	    	
 }
	
	public String getTable() {
		return Table;
	}
	public void setTable(String table) {
		Table = table;
	}
	public String getColumnFamily() {
		return ColumnFamily;
	}
	public void setColumnFamily(String columnFamily) {
		ColumnFamily = columnFamily;
	}
	public String getQualifier() {
		return Qualifier;
	}
	public void setQualifier(String qualifier) {
		Qualifier = qualifier;
	}
	
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}


}
