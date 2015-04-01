package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;

import com.example.karfai.Data;
import com.example.karfai.MainActivity;
import com.example.karfai.R;

public class test extends BaseExpandableListAdapter{
	LayoutInflater infalInflater;
	List<Data> test;
	
	
	public test(LayoutInflater infalInflater,List<Data> test){
		this.test=test;
		this.infalInflater = infalInflater;
	}



	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return 0;
	}
	

	@Override
	public View getChildView(int groupPosition, int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = infalInflater.inflate(R.layout.list_child,parent,false);
		EditText time = (EditText) convertView.findViewById(R.id.time);
		EditText day = (EditText) convertView.findViewById(R.id.day);
		EditText amount = (EditText) convertView.findViewById(R.id.amount);
		Data value = (Data) getChild(groupPosition, childPosition);
		time.setText(value.getTime()+"");
		day.setText(value.getDay());
		amount.setText(value.getAmount());	
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Object getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return test.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return test.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		convertView = infalInflater.inflate(R.layout.list_group, parent, false);
		Data value = (Data) getGroup(groupPosition);
		EditText header = (EditText) convertView.findViewById(R.id.header);
		header.setText(value.getName());
		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public Object getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return test.get(groupPosition);
	}


}
