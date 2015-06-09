package com.piano.tech.pro;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.text.TextUtils;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

public class Manufactures extends Activity{
	
	private SearchView mSearchView;
	private ListView mainListView ;
	private ArrayAdapter<String> listAdapter ;
	private ArrayList<String> planetList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.manufacturer);
		
//		ActionBar actionBar = getActionBar();
//		actionBar.setDisplayHomeAsUpEnabled(true);
		
		
		//add item to list view.
		addDataList();
	
		//search view setting.
		mSearchView = (SearchView) findViewById(R.id.searchView_Serial);
		mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
			
            @Override
            public boolean onQueryTextSubmit(String newText) {

            	return false;
            }

            @Override            
            public boolean onQueryTextChange(String newText) {
            
            	if (TextUtils.isEmpty(newText)) {
                    mainListView.clearTextFilter();
                } else {
                    mainListView.setFilterText(newText);
                }
            	
            	return true;
            }
        });

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId()) {
		
		case android.R.id.home:
			//Toast.makeText(this, "You clicked on the Application icon�, Toast.LENGTH_LONG).show();
			
			Intent i = new Intent(this, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}
		return true;
	}

	public void addDataList()
	{
		// Find the ListView resource. 
		mainListView = (ListView) findViewById( R.id.listView_Data);
		mainListView.setTextFilterEnabled(true);
		
		planetList = new ArrayList<String>();
		// Create and populate a List of planet names.

		//readManufactureData();
		
		// Create ArrayAdapter using the planet list.
		String[] planets = new String[]{"No data present"};  
		planetList.addAll( Arrays.asList(planets) );

		// Set the ArrayAdapter as the ListView's adapter.
		listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planetList);
		//listAdapter.remove("test");
		readManufactureData();		
		
		mainListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id)
			{
				String selectedFromList = (mainListView.getItemAtPosition(position).toString());
				
				Intent in = new Intent("android.intent.action.Serial");
				in.putExtra("Manufacturer", selectedFromList);
				startActivity(in);
				//Log.d("SearchView :", selectedFromList);
			}
		});
	}
	private void readManufactureData()
	{
		Thread thread = new Thread(new Runnable(){			
			@Override
			public void run() {
				try {
					// Create a URL for the desired page
					URL url = new URL("http://meerkata.com/uploads/manList.txt");
	
					// Read all the text returned by the server
					BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
					
					String str;
					while ((str = in.readLine()) != null)
						listAdapter.add(str);
					

					in.close();
					
				} catch (MalformedURLException e) {
				} catch (IOException e) {
				}
				

				updateUI();
			}
  		});
		
		thread.start();
	}
	
	private void updateUI()
	{
		//sleep(5000);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
            	if (listAdapter.getCount() > 1)
        			listAdapter.remove("No data present");
          
            	mainListView.setAdapter( listAdapter );
            }
        });        
		
	}
}
