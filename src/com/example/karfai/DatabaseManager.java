package com.example.karfai;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
	private static String SCHEMA = "karfai";
	private String sql;
	private Context context;

	public DatabaseManager(Context context) {
		super(context, SCHEMA, null, 1);
		this.context = context;

	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createData(db);
		resetDefalutData(db);
	}

	private void createData(SQLiteDatabase db) {
		sql = "CREATE TABLE `karfaidata` "
				+ "(`id` INTEGER PRIMARY KEY AUTOINCREMENT ,"
				+ "`name` VARCHAR(45) NOT NULL DEFAULT 'Unknown name',"
				+ "`wat` VARCHAR(45) NULL," + "`image` VARCHAR(20) NULL,"
				+ "`detail` VARCHAR(45) NULL);";
		try{
		db.execSQL(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		Log.d("Database", "Create Data");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	}

	public void resetDefalutData(SQLiteDatabase db) {
		ArrayList<Data> listData = loadDefalutDataList();
		//deleteAllData(db);
		ContentValues values = new ContentValues();
		for (Data data : listData) {
			values.put("name", data.getName());
			values.put("wat", data.getWat());
			db.insert("karfaidata", null, values);
		}

	}

	public void deleteAllData(SQLiteDatabase db) {
		db.delete("karfaidata", null, null);
	}

	public ArrayList<Data> loadDefalutDataList() {

		ArrayList<Data> listData = new ArrayList<Data>();
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new InputStreamReader(context.getAssets()
					.open("default.csv")));

			String[] row = null;
			while ((row = csvReader.readNext()) != null) {
				Data data = new Data();
				data.setName(row[0].toString());
				data.setWat(Double.parseDouble(row[1].toString().replaceAll(",","")));
				listData.add(data);
				System.out.println(data.getName());
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listData;
	}

	public ArrayList<Data>  getAllData() {
		sql = "Select * from karfaidata;";
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		ArrayList<Data> allDataList = new ArrayList<Data>();
		while (cursor.moveToNext()) {
			Data data = new Data();
			data.setName(cursor.getString(1));
			data.setWat(cursor.getDouble(2));
			data.setIcon(cursor.getInt(3));
			allDataList.add(data);
			}
		cursor.close();
		return allDataList;
	}

}
