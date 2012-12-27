package org.NoSQLite4j.test;

import java.io.IOException;

import org.nosqlite4j.core.dml.Put;

public class TestPut {
	public static void main(String [] args) throws IOException, NullPointerException
	{
		try{
		Put table = new Put("shop2","user1","shopped10","date1","article15");
		table.add();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}

}
