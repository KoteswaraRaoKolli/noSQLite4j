package org.NoSQLite4j.test;

import java.io.IOException;

import org.nosqlite4j.core.ddl.AlterTable;

public class TestAlterTable {
	public static void main(String [] args) throws IOException
	{
		try{
		AlterTable table = new AlterTable("shop3","Shop6");
		table.alter();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
