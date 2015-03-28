package com.example.karfai;


import java.util.List;

import com.example.fragment.Additems;
import com.example.fragment.Calculate;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.opencsv.CSVReader;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {
	private DrawerLayout mDrawerLayout;
	private List<Fragment> listFragment;
	private huador listData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listData = new huador();

        //mDrawerLayout = (DrawerLayout) findViewById(R.id.frame_container);
        displayView(0);

        
        
        URL url = getClass().getResource("test.csv");
        //File file = new File(url.getPath());
        String csvFilename = "..//test.csv";
        CSVReader csvReader = null;
		try {
			csvReader = new CSVReader(new InputStreamReader(getAssets().open("test.csv")));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
        String[] row = null;
        try {
			while((row = csvReader.readNext()) != null) {
			    System.out.println(row[0]
			              + " # " + row[1]
			              + " #  " + row[2]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        try {
			csvReader.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void displayView(int position) {
		Fragment fragment ;
		//Log.d("1", "1");

		switch (position) {
		case 0:
			fragment = new Calculate(this);
			break;
		case 1:
			fragment = new Additems(this);
			break;

		
		default:
			fragment = null;
		}
		
		if (fragment != null) {
			Log.d("!null","!null");
			FragmentManager fragmentManager = getFragmentManager();
			fragmentManager.beginTransaction().addToBackStack(null)
					.replace(R.id.frame_container, fragment).commit();

		}else{
			Log.d("null","null");
		}
	}
    public List<Data> getListData(){
		return listData.getListData();
    	
    }
    public void addListData(Data item){
    	
    	Data selectvalue = new Data();
    	
    	selectvalue.setId(listData.getListData().size()+1);
    	selectvalue.setName(item.getName());
    	selectvalue.setWat(item.getWat());
    	listData.addData(selectvalue);
    	displayView(0);
    }
    public List<Data> getitems(){
    	return listData.getItemAdd();
    }

}
