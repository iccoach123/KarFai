package com.example.karfai;

import th.ac.ru.uthai.karfai.database.DatabaseManager;
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
		dbm = new DatabaseManager(context);
	}
	
	public DatabaseManager getDatabaseManager(){
		return dbm;
	}
	
	
	
	
	
	

}
