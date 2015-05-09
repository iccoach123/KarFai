package com.example.fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.example.adapter.ExpandableListAdapter;
import com.example.adapter.test;
import com.example.karfai.Calculator;
import com.example.karfai.Data;
import com.example.karfai.DataCenter;
import com.example.karfai.MainActivity;
import com.example.karfai.R;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class Calculate extends Fragment {
	View rootView;
	List<Data> header;
	HashMap<Data, Data> child;
	MainActivity main;
	List<Data> listData;
	Configuration Config;
	boolean landscape;


	public Calculate() {
		this.main = DataCenter.getObj().getMain();
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		listData = main.getAllDataList();
		child = new HashMap<Data, Data>();
		header = new ArrayList<Data>();
		if (DataCenter.getObj().getConfig().orientation == DataCenter.getObj()
				.getConfig().ORIENTATION_PORTRAIT) {
			rootView = inflater.inflate(R.layout.calulate, container, false);
			landscape = false;
		} else {
			rootView = inflater.inflate(R.layout.calculate_land, container,
					false);
			landscape = true;
		}

		createData();

		Button calButton = (Button) rootView.findViewById(R.id.calButton);

		ExpandableListView expListView = (ExpandableListView) rootView
				.findViewById(R.id.expandableListView1);
		ViewGroup.LayoutParams params = expListView.getLayoutParams();
		if (!landscape) {
			params.height = (DataCenter.getObj().getDisplay_height() * 70) / 100;
		}else{
			params.width = (DataCenter.getObj().getDisplay_width() * 65) / 100;
			params.height = (DataCenter.getObj().getDisplay_height() * 70) / 100;
		}
		expListView.setLayoutParams(params);

		ExpandableListAdapter adapter = new ExpandableListAdapter(inflater,
				header, child, main);
		expListView.setAdapter(adapter);

		calButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				TextView showWat = (TextView) rootView
						.findViewById(R.id.sumwat);
				TextView showBill = (TextView) rootView
						.findViewById(R.id.charges);
				double sumWat = total();
				showWat.setText(sumWat + "");
				showBill.setText(totalBill(sumWat));

			}
		});

		return rootView;
	}

	private void createData() {
		for (int i = 0; i < listData.size(); i++) {
			header.add(listData.get(i));

			child.put(listData.get(i), listData.get(i));
		}
		Data val = new Data();
		val.setName("ADD");
		header.add(val);

	}

	private double total() {
		double sumWat = 0;

		for (int i = 0; i < listData.size(); i++) {
			Data data = listData.get(i);
			sumWat = sumWat + Calculator.watCal(data);
		}

		return sumWat;
	}

	private String totalBill(double sumWat) {
		return Calculator.billCal(sumWat) + "";

	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		this.Config = newConfig;
		super.onConfigurationChanged(newConfig);
	}

}
