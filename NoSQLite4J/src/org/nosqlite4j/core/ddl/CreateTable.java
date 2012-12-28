package org.nosqlite4j.core.ddl;

import java.io.File;
import java.util.ArrayList;

import org.nosqlite4j.core.Configuration;
import org.nosqlite4j.core.MetaStore;

public class CreateTable {
	private String TableName;
	private ArrayList<String> ColumnFamily;
	
	public CreateTable(String tablename)
	{
		TableName = tablename;
	}
	
	public ArrayList<String> getColumnFamily(String TableName)
	{
		return this.ColumnFamily;
	}
	public void setTableName(String nameOfNewTable)
	{
		this.TableName = nameOfNewTable;
	}
	
	public void setColumnFamilies(ArrayList<String> nameOfColumnFamily)
	{
		this.ColumnFamily = nameOfColumnFamily;
	}
	
	public void createTable() throws Exception
	{
		String columnFamilyDir;
		String DBLocation;
		Configuration config = new Configuration();
		DBLocation = config.getDBLoc();
		long StartTime = System.currentTimeMillis();
		MetaStore meta = new MetaStore();
		meta.setTable(this.TableName);
		meta.setColumnFamily(this.ColumnFamily);
		if(meta.checkTable()){
			System.err.println("Table Exists");
		}
		else{
		
		
		
		
		if(this.ColumnFamily.size() < 1)
		{
			System.err.println("ColumnFamily not specified");
		}
		else if(this.TableName == null)
		{
			System.err.println("Tablename not specified");
		}
		else if(DBLocation == null)
		{
			System.err.println("Error in Configuration DBLocation not specified");
		}
		else{
	
		(new File(DBLocation+"/"+this.TableName)).mkdirs();
		 int count = this.ColumnFamily.size();
		 //Creating directory for ColumnFamilies
while(count > 0)
{
			 columnFamilyDir = DBLocation+"/"+this.TableName+"/"+this.ColumnFamily.get(count-1);
			 (new File(columnFamilyDir)).mkdirs();
			count--; 
		 }
meta.update();
System.out.println("Table Created");
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken : "+(endTime-StartTime)+" ms");
		}
	
	  
	}

}
