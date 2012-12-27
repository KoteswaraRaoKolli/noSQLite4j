package org.NoSQLite4j.test;

import java.io.IOException;

import org.nosqlite4j.core.ddl.DeleteTable;

public class TestTableDelete {
	public static void main(String [] args) throws IOException
	{
		try{
		DeleteTable table = new DeleteTable("ab1");
		table.delete();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
