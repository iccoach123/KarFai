package com.example.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.karfai.Data;
import com.example.karfai.DataCenter;
import com.example.karfai.MainActivity;
import com.example.karfai.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private List<Data> listGroup;
	// private HashMap<String, List<Data>> data;
	private HashMap<Data, Data> data;
	private LayoutInflater infalInflater;
	private MainActivity main;
	private List<Data> test;
	private TextView txtWat;
	private int selected;
	

	public TextView getTxtWat() {
		return txtWat;
	}

	public void setTxtWat(TextView txtWat) {
		this.txtWat = txtWat;
	}

	public ExpandableListAdapter(LayoutInflater infalInflater,
			List<Data> listGroup, HashMap<Data, Data> data, MainActivity main) {
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

		final Data value = (Data) getChild(groupPosition, childPosition);
		convertView = infalInflater.inflate(R.layout.list_child, parent, false);
		final EditText time = (EditText) convertView.findViewById(R.id.time);
		final EditText day = (EditText) convertView.findViewById(R.id.day);
		final EditText amount = (EditText) convertView
				.findViewById(R.id.amount);
		final ImageButton deleteButton = (ImageButton) convertView
				.findViewById(R.id.buttondelete);
		EditText wat = (EditText) convertView.findViewById(R.id.wat);
		time.setText(value.getTime() + "");
		time.setFocusable(false);
		day.setText(value.getDay() + "");
		day.setFocusable(false);
		amount.setText(value.getAmount() + "");
		amount.requestFocus();
		
		wat.setText((int)value.getWat() + "" );
		wat.setFocusable(false);
		time.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogTime(groupPosition, childPosition,time);

			}
		});
		/*
		 * time.addTextChangedListener(new TextWatcher() {
		 * 
		 * @Override public void onTextChanged(CharSequence s, int start, int
		 * before, int count) { // TODO Auto-generated method stub try {
		 * 
		 * double inputValue = Double.parseDouble(s.toString());
		 * value.setTime(inputValue); } catch (Exception e) {
		 * 
		 * }
		 * 
		 * }
		 * 
		 * @Override public void beforeTextChanged(CharSequence s, int start,
		 * int count, int after) { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void afterTextChanged(Editable s) { // TODO
		 * Auto-generated method stub
		 * 
		 * } });
		 */
		day.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogDay(groupPosition, childPosition,day);

			}
		});

		/*
		 * day.addTextChangedListener(new TextWatcher() {
		 * 
		 * @Override public void onTextChanged(CharSequence s, int start, int
		 * before, int count) { // TODO Auto-generated method stub try{ int
		 * inputValue = Integer.parseInt(s.toString());
		 * value.setDay(inputValue); }catch(Exception e){
		 * 
		 * }
		 * 
		 * }
		 * 
		 * @Override public void beforeTextChanged(CharSequence s, int start,
		 * int count, int after) { // TODO Auto-generated method stub
		 * 
		 * }
		 * 
		 * @Override public void afterTextChanged(Editable s) { // TODO
		 * Auto-generated method stub
		 * 
		 * } });
		 */
	
		amount.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				try {
					int inputValue = Integer.parseInt(s.toString());
					value.setAmount(inputValue);
					
					main.displayView(0);
				} catch (Exception e) {

				}
				

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				

			}
		});
		deleteButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogDelete(groupPosition);

			}
		});

		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
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
		String headerTitle = ((Data) (getGroup(groupPosition))).getName();
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
			ImageView imgIcon = (ImageView) convertView
					.findViewById(R.id.icon_group);
			Data data = (Data) getChild(groupPosition, 0);
			imgIcon.setImageResource(DataCenter.getObj().getImageIcon(0));

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

	private void dialogDelete(final int groupPosition) {
		AlertDialog.Builder builder = new AlertDialog.Builder(main);
		builder.setMessage("Do you want to delete?");
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				main.remove(groupPosition);
			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

			}
		});
		builder.show();
	}

	private void dialogDay(final int groupPosition, final int childPosition,final EditText day) {
		AlertDialog.Builder builder = new AlertDialog.Builder(main);
		int dayofmonth = 31;
		CharSequence[] number = new CharSequence[32];

		for (int i = 0; i <= dayofmonth; i++) {
			number[i] = i + "";
		}
		builder.setTitle("จำนวนวันที่ใช้ต่อเดือน");
		builder.setSingleChoiceItems(number, 0,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						selected = which;

					}
				});
		builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub

				day.setText(selected + "");
				Data data = (Data) getChild(groupPosition, childPosition);
				data.setDay(selected);
				main.displayView(0);

			}
		});
		builder.setNegativeButton("No", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.dismiss();

			}
		});
		builder.show();
	}

	private void dialogTime(final int groupPosition, final int childPosition,final EditText time) {
		AlertDialog.Builder builder = new AlertDialog.Builder(main);
		View v = infalInflater.inflate(R.layout.clock, null, false);
		final TimePicker tp = (TimePicker) v.findViewById(R.id.timePicker1);
		tp.setIs24HourView(true);
		final Data data = (Data) getChild(groupPosition, childPosition);
		tp.setCurrentHour(0);
		tp.setCurrentMinute(0);
		builder.setTitle("จำนวนเวลาต่อวัน");
		builder.setView(v);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				tp.getCurrentHour();
				Toast.makeText(main,
						tp.getCurrentHour() + ":" + tp.getCurrentMinute(),
						Toast.LENGTH_SHORT).show();
				String gettime = tp.getCurrentHour() + "."
						+ tp.getCurrentMinute();
				time.setText(gettime);

				data.setTime(Double.parseDouble(gettime));
				main.displayView(0);
			}
		});
		builder.setNegativeButton("Cancle",
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						dialog.dismiss();

					}
				});
		builder.show();
	}

}
