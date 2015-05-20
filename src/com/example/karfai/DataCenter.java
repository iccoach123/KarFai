package com.example.karfai;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.res.Configuration;
import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

public class DataCenter implements Serializable {
	private ArrayList<Data> allDataList;
	private MainData md;
	private DatabaseManager dbm;
	private ArrayList<Data> itemAddList;
	private int[] imageIcon;
	private static DataCenter obj;
	private int display_width;
	private int display_height;
	private MainActivity main;
	private Configuration config;

	public Configuration getConfig() {
		return config;
	}

	public void setConfig(Configuration config) {
		this.config = config;
	}

	public MainActivity getMain() {
		return main;
	}

	public void setMain(MainActivity main) {
		this.main = main;
	}

	public static DataCenter getObj() {
		if (obj == null) {
			obj = new DataCenter();
		}
		return obj;
	}

	public DataCenter() {
		md = MainData.getMainData();
		dbm = md.getDatabaseManager();
		allDataList = new ArrayList<Data>();
		itemAddList = dbm.getAllData();
		imageIcon = new int[] { R.drawable.light, R.drawable.air,
				R.drawable.fan, R.drawable.tv, R.drawable.tv, R.drawable.rice,
				R.drawable.kettle, R.drawable.iron2, R.drawable.toaster,
				R.drawable.blenders,R.drawable.microwave,R.drawable.cleaner};

	}

	public int getDisplay_width() {
		return display_width;
	}

	public void setDisplay_width(int display_width) {
		this.display_width = display_width;
	}

	public int getDisplay_height() {
		return display_height;
	}

	public void setDisplay_height(int display_height) {
		this.display_height = display_height;
	}

	public int getImageIcon(int index) {
		return imageIcon[index];
	}

	public List<Data> getItemAddList() {

		return itemAddList;
	}

	public void setArrayItemAdd(ArrayList<Data> itemAddList) {
		this.itemAddList = itemAddList;

	}

	public ArrayList<Data> getAllDataList() {
		return allDataList;
	}

	public void setListData(ArrayList<Data> listData) {
		this.allDataList = listData;
	}

	public void addData(Data value) {
		allDataList.add(value);
	}

	public void removeData(int position) {
		allDataList.remove(position);
	}

}
