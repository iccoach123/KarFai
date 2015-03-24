package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.adapter.ExpandableListAdapter;
import com.example.karfai.Data;
import com.example.karfai.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class Calculate extends Fragment{
	View rootView;
    List<String> header;
    HashMap<String, List<Data>> child;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		child = new  HashMap<String, List<Data>>();
		header = new ArrayList<String>();
		rootView = inflater.inflate(R.layout.calulate,container,false);
		createData();
		ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView1);
		ExpandableListAdapter adapter =new ExpandableListAdapter(inflater, header, child);
		expListView.setAdapter(adapter);
		
		return rootView;
	}
	private void createData(){
		
		header.add("æ—¥≈¡");
		header.add("À≈Õ¥‰ø");
		header.add("·Õ√Ï");
		for(int i=0;i<header.size();i++){
			Data data = new Data();
			List<Data> listdata = new ArrayList<Data>();
			listdata.add(data);
			child.put(header.get(i),listdata );
		}
	}
	

}
