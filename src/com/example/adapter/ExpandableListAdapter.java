package com.example.adapter;

import java.util.HashMap;
import java.util.List;

import com.example.karfai.Data;
import com.example.karfai.MainActivity;
import com.example.karfai.R;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private List<Data> listGroup;
	// private HashMap<String, List<Data>> data;
	private HashMap<Data, Data> data;
	private LayoutInflater infalInflater;
	private MainActivity main;
	private List<Data> test;


	public ExpandableListAdapter(LayoutInflater infalInflater,
			List<Data> listGroup, HashMap<Data, Data> data,
			MainActivity main) {
		this.infalInflater = infalInflater;
		this.listGroup = listGroup;
		this.data = data;
		this.main = main;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this.data.get(this.listGroup.get(groupPosition));
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(final int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {
		Log.d("!!!!!!!!", groupPosition+"");

		final Data value = (Data) getChild(groupPosition, childPosition);
		convertView = infalInflater.inflate(R.layout.list_child, parent, false);
		final EditText time = (EditText) convertView.findViewById(R.id.time);
		final EditText day = (EditText) convertView.findViewById(R.id.day);
		final EditText amount = (EditText) convertView
				.findViewById(R.id.amount);
		final ImageButton deleteButton = (ImageButton) convertView.findViewById(R.id.buttondelete);
		time.setText(value.getTime() + "");
		day.setText(value.getDay() + "");
		amount.setText(value.getAmount() + "");
		time.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				try {
					value.setTime(Double.parseDouble(time.getText().toString()));
				} catch (Exception e) {
					time.setText(value.getTime() + "");
				}

			}
		});
		day.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				try {
					value.setDay(Integer.parseInt(day.getText().toString()));
				} catch (Exception e) {
					day.setText(value.getDay() + "");

				}
			}
		});
		amount.setOnFocusChangeListener(new OnFocusChangeListener() {

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub\	
				try {
					value.setAmount(Integer.parseInt(amount.getText().toString()));
				} catch (Exception e) {
					amount.setText(value.getAmount() + "");

				}

			}
		});
		deleteButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				main.remove(groupPosition);
				
			}
		});

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
		// this.data.get(this.listGroup.get(groupPosition))
		// .size();
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
		String headerTitle = ((Data)(getGroup(groupPosition))).getName();
		if (headerTitle.equals("ADD")) {
			convertView = infalInflater.inflate(R.layout.list_buttonadd,
					parent, false);
			ImageButton add = (ImageButton) convertView
					.findViewById(R.id.buttonadd);
			add.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					main.displayView(1);

				}
			});
		} else {
			convertView = infalInflater.inflate(R.layout.list_group, parent,
					false);
			TextView header = (TextView) convertView.findViewById(R.id.header);
			header.setText(headerTitle);
		}

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
