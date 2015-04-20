package com.example.karfai;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
	private static String SCHEMA = "faceinfo";
	private String sql;
	private Context context;

	public DatabaseManager(Context context) {
		super(context, SCHEMA, null, 1);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		createData(db);
		resetDefalutData();
	}

	private void createData(SQLiteDatabase db) {
		sql = "CREATE TABLE `data` "
				+ "(`id` VARCHAR(45) PRIMARY KEY NOT NULL,"
				+ "`name` VARCHAR(45) NOT NULL DEFAULT 'Unknown name',"
				+ "`wat` VARCHAR(45) NULL," + "`image` VARCHAR(20) NULL,"
				+ "`detail` VARCHAR(45) NULL);";
		db.execSQL(sql);
		Log.d("Database", "Create Data");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	public void resetDefalutData() {
		SQLiteDatabase db = this.getWritableDatabase();
		deleteAllData();
		ArrayList<Data> listData = loadDefalutDataList();
		ContentValues values = new ContentValues();
		for(Data data : listData){
			values.put("name", data.getName());
			values.put("wat", data.getWat());
			values.put("detail", data.getDetail());
			db.insert("data", null, values);
		}
		db.close();

	}

	public void deleteAllData() {
		SQLiteDatabase db = this.getWritableDatabase();
		db.delete("data", null, null);
		db.close();
	}

	public ArrayList<Data> loadDefalutDataList() {

		ArrayList<Data> listData = new ArrayList<Data>();
		CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new InputStreamReader(context.getAssets()
					.open("test.csv")));

			String[] row = null;
			while ((row = csvReader.readNext()) != null) {
				Data data = new Data();
				data.setName(row[0]);
				data.setWat(Double.parseDouble(row[1]));
				data.setDetail(row[2]);
				listData.add(data);
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listData;
	}

}
