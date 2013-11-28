package com.eecs.seg2505_2013;

import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.EditText;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;


public class LectureReponse extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_lecturereponse);
		TextView tvQuestion = (TextView) findViewById(R.id.tvLectureLaQuestion);
		TextView tvAnswer = (TextView) findViewById(R.id.tvLectureLaReponse);
		tvQuestion.setMovementMethod(new ScrollingMovementMethod());
		tvAnswer.setMovementMethod(new ScrollingMovementMethod());		

		
	}
	
	public void btnEvaluationOnClick(View view){
		showPopUp();
	}
	
	private void showPopUp() {

		 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		 helpBuilder.setTitle("�valuation de la r�ponse");
		 
		 LayoutInflater inflater = getLayoutInflater();
		 View evalLayout = inflater.inflate(R.layout.evaluatepopuplayout, null);
		 final RatingBar rbQualite = (RatingBar)evalLayout.findViewById(R.id.rbQualite);
		 helpBuilder.setView(evalLayout);
		 
		 helpBuilder.setPositiveButton("Envoyer l'�valutation",
		   new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int which) {
		    	sendRating(rbQualite.getRating());
		    }
		   });
		 helpBuilder.setNegativeButton("Annuler",
				   new DialogInterface.OnClickListener() {

				    public void onClick(DialogInterface dialog, int which) {
				    	
				    }
				   });
		 // Remember, create doesn't show the dialog
		 AlertDialog helpDialog = helpBuilder.create();
		 helpDialog.show();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.reponse, menu);
		return true;
	}
	
	public void sendRating(float rating){
		
		Toast.makeText(LectureReponse.this, "L'�valuation de "+rating+" �toiles a �t� envoy�!", Toast.LENGTH_SHORT).show();
		Button btnEvaluer = (Button)findViewById(R.id.btnEvaluer);
		btnEvaluer.setText("�valuation envoy�");
		btnEvaluer.setEnabled(false);
	}

}
