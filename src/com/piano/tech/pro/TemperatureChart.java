package com.piano.tech.pro;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.view.Menu;
import android.view.MenuItem;

public class TemperatureChart extends Activity{
	
	private WebView mWebView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.temperaturechart);
		
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
			//Toast.makeText(this, "You clicked on the Application icon”, Toast.LENGTH_LONG).show();
			
			Intent i = new Intent(this, MainActivity.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			startActivity(i);
			return true;
		}
		return true;
	}

	public void addDataList()
	{
		String fullName = "file:///android_asset/Temperature Humidity Conversion Chart.html";
		
        Log.d("File Name", fullName);
        mWebView.getSettings().setUseWideViewPort(true);
        mWebView.setInitialScale(3);
        mWebView.getSettings().setBuiltInZoomControls(true);
        mWebView.loadUrl(fullName);
        
        
	}		
}