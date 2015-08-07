package th.ac.ru.uthai.karfai.database;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import th.ac.ru.uthai.karfai.model.Data;

import com.opencsv.CSVReader;

import android.R.string;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseManager extends SQLiteOpenHelper {
	private static String SCHEMA = "karfai";
	private String sql;
	private Context context;

	public DatabaseManager(Context context) {
		super(context, SCHEMA, null, 3);
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
		try {
			db.execSQL(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Log.d("Database", "Create Data");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		resetDefalutData(db);
	}

	public void resetDefalutData(SQLiteDatabase db) {
		ArrayList<Data> listData = loadDefalutDataList();
		deleteAllData(db);
		ContentValues values = new ContentValues();
		for (Data data : listData) {
			values.put("name", data.getName());
			values.put("wat", data.getWat());
			values.put("image", data.getIcon());
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
				data.setWat(Double.parseDouble(row[1].toString().replaceAll(
						",", "")));
				data.setIcon(Integer.parseInt(row[2]));
				listData.add(data);
				System.out.println(data.getName());
			}
			csvReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return listData;
	}

	public ArrayList<Data> getAllData() {
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

	public ArrayList<Cursor> getData(String Query) {
		// get writable database
		SQLiteDatabase sqlDB = this.getWritableDatabase();
		String[] columns = new String[] { "mesage" };
		// an array list of cursor to save two cursors one has results from the
		// query
		// other cursor stores error message if any errors are triggered
		ArrayList<Cursor> alc = new ArrayList<Cursor>(2);
		MatrixCursor Cursor2 = new MatrixCursor(columns);
		alc.add(null);
		alc.add(null);

		try {
			String maxQuery = Query;
			// execute the query results will be save in Cursor c
			Cursor c = sqlDB.rawQuery(maxQuery, null);

			// add value to cursor2
			Cursor2.addRow(new Object[] { "Success" });

			alc.set(1, Cursor2);
			if (null != c && c.getCount() > 0) {

				alc.set(0, c);
				c.moveToFirst();

				return alc;
			}
			return alc;
		} catch (SQLException sqlEx) {
			Log.d("printing exception", sqlEx.getMessage());
			// if any exceptions are triggered save the error message to cursor
			// an return the arraylist
			Cursor2.addRow(new Object[] { "" + sqlEx.getMessage() });
			alc.set(1, Cursor2);
			return alc;
		} catch (Exception ex) {

			Log.d("printing exception", ex.getMessage());

			// if any exceptions are triggered save the error message to cursor
			// an return the arraylist
			Cursor2.addRow(new Object[] { "" + ex.getMessage() });
			alc.set(1, Cursor2);
			return alc;
		}

	}

}
