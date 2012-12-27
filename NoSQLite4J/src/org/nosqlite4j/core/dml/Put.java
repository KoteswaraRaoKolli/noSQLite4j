package org.nosqlite4j.core.dml;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


import org.json.JSONException;
import org.json.JSONObject;
import org.nosqlite4j.core.DBManager;

public class Put extends DBManager{
	private String Table;
	private String ColumnFamily;
	private String Qualifier;
	private String Value;
	private String Key;

	public Put(String table, String colfam, String qualifier, String key,String value)
	{
		Table = table;
		ColumnFamily = colfam;
		Qualifier = qualifier;
		Value = value;
		setKey(key);
	}
	public void add() throws IOException, JSONException, NullPointerException
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
		else if(Qualifier == null)
		{
			System.err.println("Qualifier not set");
		}
		else if(Value == null)
		{
			System.err.println("Value not set");
		}
		else{
			keyvalue = read(Table);
			if(keyvalue.contains(ColumnFamily))
			{
				String DBloc = getDBloc();
				  FileWriter fstream = new FileWriter(DBloc+"/"+Table+"/"+ColumnFamily+"/"+Qualifier,true);
				  BufferedWriter out = new BufferedWriter(fstream);
				  JSONObject obj = new JSONObject();
				  obj.put(Key, Value);
				  out.write(obj+"\n");
				  out.close();
				  System.out.println("Value added to store");
			
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
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getKey() {
		return Key;
	}
	public void setKey(String key) {
		Key = key;
	}

}
