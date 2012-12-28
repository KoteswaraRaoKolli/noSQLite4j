package org.nosqlite4j.core.ddl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;
import org.nosqlite4j.core.Configuration;
import org.nosqlite4j.core.MetaStore;

public class AlterTable {
	String TableName;
	String newTableName;
	ArrayList<String> ColumnFamily;
	public AlterTable(String tablename, String newTable)
	{
		TableName = tablename;
		newTableName = newTable;
	}
	public AlterTable(String tablename,ArrayList<String> newColumnFamily )
	{
		ColumnFamily = newColumnFamily;
	}
	
	public void alter() throws IOException, JSONException
	{
		long startTime = System.currentTimeMillis();
		MetaStore meta = new MetaStore();
		meta.setTable(newTableName);
		if(meta.checkTable())
		{
			System.err.println("Table "+newTableName+" exists");
		}
		else{
		meta.setTable(TableName);
		if(meta.checkTable())
		{
		
		meta.alterEntry(newTableName);
		Configuration config = new Configuration();
		File dir = new File(config.getDBLoc()+"/"+TableName);
		File newDir = new File(config.getDBLoc()+"/"+newTableName);
		dir.renameTo(newDir);
		System.out.println("Table Altered");
		}
		else
		{
			System.err.println("Table "+TableName+" does not exist");
		}
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time Taken : "+(endTime - startTime)+" ms");
		
		
	}
	
	public void setTableName(String tablename)
	{
		TableName = tablename;
	}	
	public void setColumnFamily(ArrayList<String> newColumnFamily)
	{
		ColumnFamily = newColumnFamily;
	}
	public void setNewTableName(String tablename)
	{
		newTableName = tablename;
	}	

}
