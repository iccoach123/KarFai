package com.example.karfai;

import android.content.Context;

public class MainData {
	
	private static MainData md = null;
	private DatabaseManager dbm = null;
	
	private MainData(){
	}
	public static MainData getMainData(){
		if(md == null){
			md = new MainData();
		}
		return md;
	}
	
	public void setDatabaseManager(Context context){
		DatabaseManager dbm = new DatabaseManager(context);
	}
	
	public DatabaseManager getDatabaseManager(){
		return dbm;
	}
	
	
	
	
	
	

}
