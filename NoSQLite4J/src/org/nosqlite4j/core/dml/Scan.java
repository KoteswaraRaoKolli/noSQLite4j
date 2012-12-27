package org.nosqlite4j.core.dml;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.nosqlite4j.core.DBManager;

public class Scan extends DBManager{
	private String Result = "";
	private String Table;
	public Scan(String tablename)
	{
		setTable(tablename);
	}
	public String scan() throws IOException
	{
		long StartTime = System.currentTimeMillis();
		File file = new File(getDBloc()+"/"+Table+"/");
		 read(file);
		 long endTime = System.currentTimeMillis();
			System.out.println("Time Taken : "+(endTime-StartTime)+" ms");
			 return Result;

	}
	
	protected void read(File file) throws IOException{
	
		 String tempFile = "";
   	if(file.isDirectory()){
   		
   		if(file.list().length==0){

   		}else{
       	   String files[] = file.list();

       	   for (String temp : files) {
       	      File fileDelete = new File(file, temp);
       	     read(fileDelete);
       	   }

   		}

   	}else{
   		FileInputStream fistream = new FileInputStream(file);
			  DataInputStream in = new DataInputStream(fistream);
			  BufferedReader br = new BufferedReader(new InputStreamReader(in));
			  String strLine;
			  while ((strLine = br.readLine()) != null)
			  {
				  tempFile = file.toString();
				  String [] res = tempFile.replaceAll(getDBloc(), "").split("/");
				  strLine = res[1]+"|"+res[2]+"|"+res[3]+"|"+strLine;
				 Result = Result + strLine+"\n";
			  }
			  
			  
			  in.close();
   	}
   	
}

	public String getTable() {
		return Table;
	}

	public void setTable(String table) {
		Table = table;
	}

}
