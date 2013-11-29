package com.eecs.seg2505_2013;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;


public class ReponseActivity extends Activity {

	EditText txtReponse;
	TextView tvRefus;
	AlertDialog levelDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_reponse);
		TextView tvQuestion = (TextView) findViewById(R.id.textViewQuestion);
		tvQuestion.setMovementMethod(new ScrollingMovementMethod());
		txtReponse = (EditText) findViewById(R.id.etReponse);
		tvRefus = (TextView) findViewById(R.id.tvRefus);
		tvRefus.setText("");

		
	}
	
	public void envoyerReponseOnClick(View view){

		if(!tvRefus.getText().toString().matches("")){
			Toast.makeText(ReponseActivity.this, "Votre refus a été envoyée.", Toast.LENGTH_SHORT).show();
		}
		else if (tvRefus.getText().toString().matches("")) {
			if(txtReponse.getText().toString().matches("")){
				Toast.makeText(ReponseActivity.this, "Vous devez saisir une réponse.", Toast.LENGTH_SHORT).show();
			}
			else{
				Toast.makeText(ReponseActivity.this, "Votre réponse a été envoyée.", Toast.LENGTH_SHORT).show();
			}
		}
	
	}
	
	public void envoyerRefusOnClick(View view){
		showPopUp();
	}
	
	private void showPopUp() {

	
		// Strings to Show In Dialog with Radio Buttons
		final CharSequence[] items = {" Question trop difficile "," Question Non Pertinente "," Question Incomplète "," Trop Occupé "};
		            
		                // Creating and Building the Dialog 
		                AlertDialog.Builder builder = new AlertDialog.Builder(this);
		                builder.setTitle("Choisir la raison du refus:");
		                builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int item) {
		                   
		                    
		                    switch(item)
		                    {
		                        case 0:
		                                // Your code when first option seletced
		                        	tvRefus.setText("Refus: Question Trop difficile");
		                               	break;
		                        case 1:
		                                // Your code when 2nd  option seletced
		                                tvRefus.setText("Refus: Question Non Pertinente");
		                                break;
		                        case 2:
		                               // Your code when 3rd option seletced
		                        		tvRefus.setText("Refus: Incomplète");
		                                break;
		                        case 3:
		                                 // Your code when 4th  option seletced        
		                        		tvRefus.setText("Refus: Trop Occupé");
		                                break;
		                        
		                    }
		                    txtReponse.setText("");
		                    txtReponse.setBackgroundResource(android.R.color.darker_gray);
		                    txtReponse.setEnabled(false);
		                    levelDialog.dismiss();    
		                    }
		                });
		                levelDialog = builder.create();
		                levelDialog.show();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reponse, menu);
		return true;
	}
	
	public String getQuestion(){
		
		//question = ...;
		return "Ceci est une question";
		
	}

}
