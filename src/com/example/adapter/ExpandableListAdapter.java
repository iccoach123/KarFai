package com.example.adapter;



import java.util.HashMap;
import java.util.List;

import com.example.karfai.Data;
import com.example.karfai.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private List<String> listGroup;
	private HashMap<String, List<Data>> data;
	private LayoutInflater infalInflater;

	
	
	public ExpandableListAdapter(LayoutInflater infalInflater, List<String> listGroup,
			HashMap<String, List<Data>> data) {
		this.infalInflater=infalInflater;
		this.listGroup=listGroup;
		this.data=data;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.data.get(this.listGroup.get(groupPosition)).get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		final Data value = (Data) getChild(groupPosition, childPosition);
		convertView = infalInflater.inflate(R.layout.list_child, parent, false);
		EditText time = (EditText) convertView.findViewById(R.id.time);
		EditText day = (EditText) convertView.findViewById(R.id.day);
		EditText amount = (EditText) convertView.findViewById(R.id.amount);
		time.setText(value.getTime()+"");
		day.setText(value.getDay()+"");
		amount.setText(value.getAmount()+"");
		
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this.data.get(this.listGroup.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this.listGroup.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this.listGroup.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		String headerTitle = (String) getGroup(groupPosition);
		convertView = infalInflater.inflate(R.layout.list_group, parent, false);
		TextView header = (TextView) convertView.findViewById(R.id.header);
		header.setText(headerTitle);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
