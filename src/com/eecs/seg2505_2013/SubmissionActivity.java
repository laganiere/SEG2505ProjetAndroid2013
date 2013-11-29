package com.eecs.seg2505_2013;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SubmissionActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_submission);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.submission, menu);
		return true;
	}
	
	public void 
	mainPage(View view) {
		Intent intent = new Intent(this, MainActivity.class);
	    startActivity(intent);
	}
	
	public void suggerer(View view) {
		Intent intent = new Intent(this, ProposeDomaineActivity.class);
	    startActivity(intent);
	}
}
