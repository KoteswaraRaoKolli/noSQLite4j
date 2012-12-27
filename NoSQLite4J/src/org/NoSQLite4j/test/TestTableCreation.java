package org.NoSQLite4j.test;

import java.io.IOException;
import java.util.ArrayList;

import org.nosqlite4j.core.ddl.CreateTable;

public class TestTableCreation {
	public static void main(String [] args) throws IOException
	{
		try{
		ArrayList<String> ColumnFamily = new ArrayList<String>();
		ColumnFamily.add("user1");
		CreateTable table = new CreateTable("shop3");
		table.setColumnFamilies(ColumnFamily);
		table.createTable();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
   
}
