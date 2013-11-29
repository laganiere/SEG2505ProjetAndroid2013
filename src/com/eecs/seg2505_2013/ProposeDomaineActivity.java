package com.eecs.seg2505_2013;

import java.util.List;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProposeDomaineActivity extends Activity {

	List<String> groupList;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addcategorie);
		
		// Les choix de domaine bidon 
		groupList= ((MyApplication)getApplicationContext()).getSuperDomaines();
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);		
		ArrayAdapter <String> adapter = new ArrayAdapter <String> (this, android.R.layout.simple_spinner_dropdown_item);
		adapter.addAll(groupList);
		spinner.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addcategorie, menu);
		return true;
	}
	
	public void cancel(View view) {
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);		
	}
	
	public void submit(View view) {
		TextView tvDomaine = (TextView) findViewById(R.id.editText2);
		if (tvDomaine.length() == 0) {
			Toast.makeText(getApplicationContext(), "S'il vous plait inserer un domaine valide.", Toast.LENGTH_SHORT).show();
		} else {
			Intent intent = new Intent(this, SubmissionActivity.class);
			startActivity(intent);	
		}
	}
	
	public void enableSpinner(View view) {
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		if (((CheckBox) view).isChecked()) {
			spinner.setClickable(true);
		}
		else {
			spinner.setClickable(false);
		}
		
	}

}
