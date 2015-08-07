package th.ac.ru.uthai.karfai.adaptr;

import java.util.List;

import th.ac.ru.uthai.karfai.main.DataConfig;
import th.ac.ru.uthai.karfai.main.MainData;
import th.ac.ru.uthai.karfai.model.Data;

import com.example.karfai.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {
	private LayoutInflater infalInflater;
	private List<Data> items;
	
	public ListViewAdapter (LayoutInflater infalInflater,List<Data> items){
		this.infalInflater=infalInflater;
		this.items=items;
		
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return items.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return items.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Data value = items.get(position);
		convertView = infalInflater.inflate(R.layout.list_add, parent, false);
		TextView name = (TextView) convertView.findViewById(R.id.addname);
		String test = "delete";
		ImageView icon = (ImageView) convertView.findViewById(R.id.icon_add);
		
		//TextView wat = (TextView) convertView.findViewById(R.id.addwat);
		name.setText(value.getName());
		icon.setImageResource(MainData.getMainData().getDataConfig().getImageIcon(value.getIcon()));
		//wat.setText(value.getWat()+"");
		
		
		return convertView;
	}


}
