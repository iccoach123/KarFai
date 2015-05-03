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
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class Calculate extends Fragment{
	View rootView;
    List<Data> header;
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
		listData = main.getAllDataList();
		child = new  HashMap<Data,Data>();
		header = new ArrayList<Data>();
		rootView = inflater.inflate(R.layout.calulate,container,false);
		createData();
		Button calButton = (Button) rootView.findViewById(R.id.calButton);
		
		ExpandableListView expListView = (ExpandableListView) rootView.findViewById(R.id.expandableListView1);
		ExpandableListAdapter adapter =new ExpandableListAdapter(inflater, header, child,main);
		expListView.setAdapter(adapter);

		calButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				TextView showWat = (TextView) rootView.findViewById(R.id.sumwat);
				TextView showBill = (TextView) rootView.findViewById(R.id.charges);
				double sumWat =  total();
				showWat.setText(sumWat+"");
				showBill.setText(totalBill(sumWat));
				
			}
		});
		
		
		
		
		
		
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
	}
	private double total(){
		double sumwat =0;
		for(int i=0;i<listData.size();i++){
			Data value = listData.get(i);
			sumwat = sumwat + value.getWat()*value.getDay();
		}
		return sumwat;
	}
	private String totalBill(double sumWat){
		
		return 1+"";
	}
	
	

}
