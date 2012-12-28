package org.NoSQLite4j.test;

import java.io.IOException;

import org.nosqlite4j.core.dml.Get;

public class TestGet {
	public static void main(String [] args) throws IOException, NullPointerException
	{
		try{
		
		Get table = new Get("Shop6","user2","shopped10","date1");
		System.out.println(table.get());
		Get table2 = new Get("shop2","user2","shopped");
		System.out.println(table2.get());
	
		Get table3 = new Get("Shop3","user2");
		System.out.println(table3.get());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
}
