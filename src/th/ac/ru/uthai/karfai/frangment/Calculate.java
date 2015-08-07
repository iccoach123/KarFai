package th.ac.ru.uthai.karfai.frangment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import th.ac.ru.uthai.karfai.adaptr.ExpandableListAdapter;
import th.ac.ru.uthai.karfai.main.Calculator;
import th.ac.ru.uthai.karfai.main.DataCenter;
import th.ac.ru.uthai.karfai.main.MainActivity;
import th.ac.ru.uthai.karfai.model.Data;

import com.example.karfai.R;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupCollapseListener;
import android.widget.ExpandableListView.OnGroupExpandListener;
import android.widget.TextView;
import android.widget.Toast;

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


		ExpandableListView expListView = (ExpandableListView) rootView
				.findViewById(R.id.expandableListView1);
		ViewGroup.LayoutParams params = expListView.getLayoutParams();
		if (!landscape) {
			params.height = (DataCenter.getObj().getDisplay_height() * 80) / 100;
		}else{
			params.width = (DataCenter.getObj().getDisplay_width() * 65) / 100;
			params.height = (DataCenter.getObj().getDisplay_height() * 70) / 100;
		}
		expListView.setLayoutParams(params);

		ExpandableListAdapter adapter = new ExpandableListAdapter(inflater,
				header, child, main);
		expListView.setAdapter(adapter);
		for(int i = 0;i<listData.size();i++){
			if(listData.get(i).isStatusExpand()){
				expListView.expandGroup(i);
			}
			else{
				expListView.collapseGroup(i);
			}
		}
		expListView.setOnGroupExpandListener(new  OnGroupExpandListener() {
			
			@Override
			public void onGroupExpand(int groupPosition) {
				// TODO Auto-generated method stub
				listData.get(groupPosition).changeStatusExpand(true);
			}
		});
		expListView.setOnGroupCollapseListener(new OnGroupCollapseListener() {
			
			@Override
			public void onGroupCollapse(int groupPosition) {
				// TODO Auto-generated method stub
				listData.get(groupPosition).changeStatusExpand(false);
				
			}
		});
		
		TextView showWat = (TextView) rootView
				.findViewById(R.id.sumwat);
		TextView showBill = (TextView) rootView
				.findViewById(R.id.charges);

		
		double sumWat = total();
		showWat.setText(sumWat+"");
		showBill.setText(totalBill(sumWat));

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
