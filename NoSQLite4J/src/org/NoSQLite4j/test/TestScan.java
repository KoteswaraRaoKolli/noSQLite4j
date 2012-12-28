package org.NoSQLite4j.test;

import java.io.IOException;

import org.nosqlite4j.core.dml.Scan;

public class TestScan {
	public static void main(String [] args) throws IOException, NullPointerException
	{
		try{
		Scan table = new Scan("shop3");
		System.out.println(table.scan());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
