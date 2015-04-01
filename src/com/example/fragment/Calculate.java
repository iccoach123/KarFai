package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.adapter.ExpandableListAdapter;
import com.example.adapter.test;
import com.example.karfai.Data;
import com.example.karfai.MainActivity;
import com.example.karfai.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class Calculate extends Fragment{
	View rootView;
    List<Data> header;
    //HashMap<String, List<Data>> child;
    HashMap<Data, Data> child;
    MainActivity main;
    List<Data> listData;
    public Calculate (MainActivity main){
    	this.main=main;
    }

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listData = main.getListData();
		//child = new  HashMap<String, List<Data>>();
		child = new  HashMap<Data,Data>();
		header = new ArrayList<Data>();
		rootView = inflater.inflate(R.layout.calulate,container,false);
		createData();
		ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView1);
		ExpandableListAdapter adapter =new ExpandableListAdapter(inflater, header, child,main);
		//test adapter =new test(inflater,listData);
		expListView.setAdapter(adapter);
		
		return rootView;
	}
	private void createData(){
		for(int i=0;i<listData.size();i++){
			header.add(listData.get(i));
			
			child.put(listData.get(i), listData.get(i));
		}
		Data val = new Data();
		val.setName("ADD");
		header.add(val);
		
		
		/*header.add("¾Ñ´ÅÁ");
		header.add("ËÅÍ´ä¿");
		header.add("áÍÃì");
		for(int i=0;i<header.size();i++){
			Data data = new Data();
			List<Data> listdata = new ArrayList<Data>();
			listdata.add(data);
			child.put(header.get(i),listdata );
		}*/
	}
	

}
