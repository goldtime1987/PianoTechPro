package com.piano.tech.pro;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.content.Intent;
import android.app.ActionBar;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);

		ActionBar actionBar = getActionBar();
		actionBar.hide();
		
		setContentView(R.layout.activity_main);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onClickSerialNumber(View v)//1
	{
		startActivity(new Intent("android.intent.action.Manufactures"));
	}
	
	public void onClickSchematics(View v)//2
	{
		startActivity(new Intent("android.intent.action.Schematics"));
	}
	
	public void onClickTemperatureChart(View v)//3
	{
		startActivity(new Intent("android.intent.action.TemperatureChart"));
	}

	public void onClickThanks(View v)//6
	{
		startActivity(new Intent("android.intent.action.Thanks"));
	}

	
//	public void onClickElectronicFork(View v)//4
//	{
//		startActivity(new Intent("android.intent.action.ElectronicFork"));
//	}
//	
//	public void onClickAuralAide(View v)//5
//	{
//		startActivity(new Intent("android.intent.action.AuralAide"));
//	}
//	
//	public void onClickGeometryCalculations(View v)//6
//	{
//		startActivity(new Intent("android.intent.action.PianoActionGeometryCalculations"));
//	}
//
	public void onClickContactInfo(View v)//6
	{
		startActivity(new Intent("android.intent.action.InfoContact"));
	}

	public void onClickWebSite(View v)//7
	{
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.pianolifesaver.com"));
		startActivity(browserIntent);
	}
}
