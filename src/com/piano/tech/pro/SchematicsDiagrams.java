package com.piano.tech.pro;


import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.view.Menu;
import android.view.MenuItem;

public class SchematicsDiagrams extends Activity{
	
	private String title;
	private WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.schematicsdiagrams);
		
		//change title 
		title = "";
		Intent iin= getIntent();
		Bundle b = iin.getExtras();
	    if(b!=null)
	    {
	    	title =(String)b.get("Schematic");
	    	this.setTitle(title);
	    }

	    //change actionbar functionality
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		
		//add pdf file to web view.
		mWebView = (WebView)findViewById( R.id.webView_PDF);
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
			Intent i = new Intent(this, Schematics.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}
		return true;
	}

	public void addDataList()
	{
		String fileName = title;

		fileName = title.replace('"', '_');
		fileName = fileName.replace('/', 'A');
		fileName += ".png";
		String fullName = "file:///android_asset/Schematics/" + fileName;
//		String fullName = "file:///android_asset/Schematics/temp.png";
		
        Log.d("File Name", fullName);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setInitialScale(1);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl(fullName);
        
        
	}		
}