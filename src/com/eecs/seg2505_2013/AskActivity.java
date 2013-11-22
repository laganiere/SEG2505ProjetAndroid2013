package com.eecs.seg2505_2013;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.swarmconnect.SwarmActivity;

public class AskActivity extends SwarmActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ask);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ask, menu);
		return true;
	}
	
	@Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    case R.id.action_home:
	      Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
	      Intent intent = new Intent(this, MainActivity.class);
		  startActivity(intent);
	      break;

	    default:
	      break;
	    }

	    return true;
	  } 
	
	public void onSubmit(View view) {
		Toast.makeText(this, "Submit clicked", Toast.LENGTH_SHORT).show();
	}
}
