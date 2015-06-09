package com.piano.tech.pro;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.view.MenuItem;

public class Schematics extends Activity{
	
	private ListView mainListView ;
	private ArrayAdapter<String> listAdapter ;
	private ArrayList<String> planetList;
	private String fileContent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.schematics);
		
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//add item to list view.
		addDataList();	
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
		readSchematicsData();

		// Find the ListView resource. 
		mainListView = (ListView) findViewById( R.id.listView_Data);
		planetList = new ArrayList<String>();
		// Create ArrayAdapter using the planet list.
		
		String[] planets = TextUtils.split(fileContent, "\n");
		planetList.addAll( Arrays.asList(planets) );
		
		// Set the ArrayAdapter as the ListView's adapter.
		listAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, planetList);
		
		mainListView.setAdapter( listAdapter );
		
		mainListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,int position, long id)
			{
				String selectedFromList = (mainListView.getItemAtPosition(position).toString());  

				Intent in = new Intent("android.intent.action.SchematicsDiagrams");
				in.putExtra("Schematic", selectedFromList);
				startActivity(in);

				//Log.d("SearchView :", selectedFromList);
			}
		});
	}

	private void readSchematicsData()
	{
		AssetManager assetManager = getAssets();
		
		// To load text file
        InputStream input;
		try {
			input = assetManager.open("schematics.txt");
			
	         int size = input.available();
	         byte[] buffer = new byte[size];
	         input.read(buffer);
	         input.close();

	         // byte buffer into a string
	         fileContent = new String(buffer);	         
	      
	    } catch (IOException e) {
			e.printStackTrace();
		}		
	}	
}