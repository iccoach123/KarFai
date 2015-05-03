package com.example.karfai;

import java.util.ArrayList;
import java.util.List;

public class huador {
	ArrayList<Data> allDataList;
	MainData md;
	DatabaseManager dbm;
	ArrayList<Data> itemAddList;
	public huador(){
		md = MainData.getMainData();
		dbm = md.getDatabaseManager();
		allDataList = new ArrayList<Data>();
		itemAddList = dbm.getAllData();
		
		
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
