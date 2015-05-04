package com.example.karfai;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;
import android.util.DisplayMetrics;
import android.view.Display;

public class DataCenter {
	private ArrayList<Data> allDataList;
	private MainData md;
	private DatabaseManager dbm;
	private ArrayList<Data> itemAddList;
	private int[] imageIcon;
	private static DataCenter obj ;
	private int display_width;
	private int display_height;
	
	public static DataCenter getObj() {
		if (obj == null){
			obj = new DataCenter();
		}
		return obj;
	}

	public DataCenter(){
		md = MainData.getMainData();
		dbm = md.getDatabaseManager();
		allDataList = new ArrayList<Data>();
		itemAddList = dbm.getAllData();
		imageIcon = new int[]{R.drawable.ic_launcher,R.drawable.delete};	
		
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
	public void addData(Data value){
		allDataList.add(value);
	}
	public void removeData(int position){
		allDataList.remove(position);
	}
	
	
	
	

}
