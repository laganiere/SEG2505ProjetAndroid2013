package com.eecs.seg2505_2013;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmActivity;

public class AskActivity extends SwarmActivity {
	
	Map<String, List<String>> sousDomaines; // sous-domaine
	
	Spinner domainSpinner;
	Spinner subDomainSpinner;
	ArrayAdapter<String> subDomainAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ask);
		sousDomaines= ((MyApplication)getApplicationContext()).getSousDomaines();
		domainSpinner = (Spinner) findViewById(R.id.spinner1);
		subDomainSpinner  = (Spinner) findViewById(R.id.spinner2);
		domainSpinner.setOnItemSelectedListener(new OnItemSelectedListener(){
			@Override
		    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int pos, long id) {
				//Toast.makeText(parentView.getContext(), "Selected domain " +
				//          parentView.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
				subDomainAdapter = new ArrayAdapter<String>(selectedItemView.getContext(), android.R.layout.simple_spinner_item, sousDomaines.get(parentView.getItemAtPosition(pos)));
				        
				// Specify the layout to use when the list of choices appears
				subDomainAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				// Apply the adapter to the spinner
				subDomainSpinner.setAdapter(subDomainAdapter);
		    }

			@Override
		    public void onNothingSelected(AdapterView<?> parentView) {
		        // would that even happen?
		    }
		});
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
	      //Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show();
	      Intent intent = new Intent(this, MainActivity.class);
		  startActivity(intent);
	      break;

	    default:
	      break;
	    }

	    return true;
	  } 
	
	public void onSubmit(View view) {
		Toast.makeText(this, "Submited", Toast.LENGTH_SHORT).show();
		Question question = new Question(new Utilisateur(Swarm.user.username));
		EditText text  = (EditText)findViewById(R.id.editText1);
		question.setTexte(text.getText().toString());
		question.setDate(new Date());
		question.setDomaine(new Domaine(subDomainSpinner.getItemAtPosition(subDomainSpinner.getSelectedItemPosition()).toString()));
		((MyApplication)getApplicationContext()).sendQuestion(question);
		finish();
	}
}
