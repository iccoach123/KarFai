package th.ac.ru.uthai.karfai.main;

import th.ac.ru.uthai.karfai.database.DatabaseManager;
import android.content.Context;

public class MainData {
	
	private static MainData md = null;
	private DatabaseManager dbm = null;
	private MainActivity ma;
	private DataConfig dataConfig;
	
	public MainActivity getMainActivity() {
		return ma;
	}
	public void setMa(MainActivity ma) {
		this.ma = ma;
	}
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
	public DataConfig getDataConfig() {
		if(dataConfig == null){
			dataConfig = new DataConfig();
		}
		return dataConfig;
	}
	
	
	
	
	
	

}
