package com.example.karfai;

import java.util.ArrayList;
import java.util.List;

public class huador {
	List<Data> listData;
	List<Data> itemAdd;
	public huador(){
		listData =new ArrayList<Data>();
		itemAdd = new ArrayList<Data>();
		Data data = new Data();
		data.setName("æ—¥≈¡");
		data.setWat(16);
		itemAdd.add(data);
		data = new Data();
		data.setName("·Õ√Ï");
		data.setWat(20);
		itemAdd.add(data);
		
	}

	public List<Data> getItemAdd() {
		
		return itemAdd;
	}

	public void setItemAdd(List<Data> itemAdd) {
		this.itemAdd = itemAdd;
		
	}

	public List<Data> getListData() {
		return listData;
	}

	public void setListData(List<Data> listData) {
		this.listData = listData;
	}
	public void addData(Data value){
		listData.add(value);
	}
	public void remove(int position){
		listData.remove(position);
	}
	
	
	

}
