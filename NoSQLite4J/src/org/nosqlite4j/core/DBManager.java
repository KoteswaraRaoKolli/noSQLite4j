package org.nosqlite4j.core;

import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public abstract class DBManager {
	private String metaContent;
	public String read(String table) throws IOException, JSONException, NullPointerException
	{
		String values = null;
		MetaStore meta = new MetaStore();
		meta.setTable(table);
		if(meta.checkTable())
		{
			metaContent = meta.metaReader();
			JSONObject metadata = new JSONObject(metaContent);
			values = metadata.get(table).toString();
		}
		else
		{
			System.err.println("Table Does not exist");
		}
		return values;
		
	}
	public String getDBloc() {
		Configuration config = new Configuration();
		return config.DBLocation;
	}
	

}
