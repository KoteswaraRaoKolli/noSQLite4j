package org.nosqlite4j.core;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MetaStore {
	private String Table;
	private ArrayList<String>ColumnFamily;
	private JSONObject StoreThis;
	 Configuration config = new Configuration();
	 String DBloc = config.getDBLoc();
	 
	 public MetaStore() throws IOException
	 {
		 File file = new File(DBloc+"/"+"metastore");
		 file.createNewFile();
	 }
	
	public String getTable() {
		return Table;
	}
	public void setTable(String table) {
		Table = table;
	}
	public ArrayList<String> getColumnFamily() {
		return ColumnFamily;
	}
	public void setColumnFamily(ArrayList<String> columnFamily) {
		ColumnFamily = columnFamily;
	}
	
	public void deleteEntry()
	{
		 try{
			 
			 String metaContent = this.metaReader();

		if(metaContent.equals(""))
		{
			StoreThis = new JSONObject();
		
		}
		else{
			StoreThis = new JSONObject(metaContent);
		}
			  StoreThis.remove(Table);
			  
		
			
			  FileWriter fstream = new FileWriter(DBloc+"/"+"metastore");
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(StoreThis.toString());
			  out.close();
			  }catch (Exception e){
				  e.printStackTrace();
			  }
			  
	}
	

	public void alterEntry(String tablename)
	{
		try{
			 
			 String metaContent = this.metaReader();

		if(metaContent.equals(""))
		{
			System.err.println("Table does not exist");
		
		}
		else{
			StoreThis = new JSONObject(metaContent);
		}
			 JSONArray JArr = new JSONArray(StoreThis.get(Table).toString());
			  StoreThis.remove(Table);
			  StoreThis.put(tablename, JArr);
			  
			  
		
			
			  FileWriter fstream = new FileWriter(DBloc+"/"+"metastore");
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(StoreThis.toString());
			  out.close();
			  }catch (Exception e){
				  e.printStackTrace();
			  }	
		
	}
	
	public void update()
	{
		 try{
			 
			 String metaContent = this.metaReader();

		if(metaContent.equals(""))
		{
			StoreThis = new JSONObject();
		
		}
		else{
			StoreThis = new JSONObject(metaContent);
		}
		JSONArray JArr = new JSONArray(ColumnFamily);
			  StoreThis.put(Table, JArr);
			  
		
			
			  FileWriter fstream = new FileWriter(DBloc+"/"+"metastore");
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.write(StoreThis.toString());
			  out.close();
			  }catch (Exception e){
				  e.printStackTrace();
			  }
			  
	}
	public boolean checkTable() throws JSONException, IOException
	{
		boolean check;
		 
		
        String metaContent = this.metaReader();
		if(metaContent.equals(""))
		{
			check = false;
		}
		else{
			JSONObject tempTab = new JSONObject(metaContent);
		     
			if(tempTab.has(Table))
			{
				check = true;
				return check;
			}
			else{
				check = false;
			}
		}
		 
		
		return check;
		
	}

	public String metaReader() throws IOException
	{
		 String metaContent = "";
		  FileInputStream fistream = new FileInputStream(DBloc+"/"+"metastore");
		  DataInputStream in = new DataInputStream(fistream);
		  BufferedReader br = new BufferedReader(new InputStreamReader(in));
		  String strLine;
		  while ((strLine = br.readLine()) != null)
		  {
			  metaContent = metaContent + strLine;
		  }
		  in.close();
		  return metaContent;
	}
	

	

}
