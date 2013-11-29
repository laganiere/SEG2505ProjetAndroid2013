package com.eecs.seg2505_2013;


import android.os.Bundle;
import android.app.Activity;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;


public class EvaluationActivity extends Activity {

	boolean evalue;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_evaluation);
		TextView tvQuestion = (TextView) findViewById(R.id.textViewQuestion);
		TextView tvAnswer = (TextView) findViewById(R.id.tvAnswer);
		tvQuestion.setMovementMethod(new ScrollingMovementMethod());
		tvAnswer.setMovementMethod(new ScrollingMovementMethod());		
		evalue = false;
	}
	
	public void envoyerEvalOnClick(View view){

		showPopUp();
		
	}
	
	private void showPopUp() {

		 AlertDialog.Builder helpBuilder = new AlertDialog.Builder(this);
		 helpBuilder.setTitle("Évaluation de la réponse");
		 
		 LayoutInflater inflater = getLayoutInflater();
		 View evalLayout = inflater.inflate(R.layout.evaluation_popuplayout, null);
		 final RatingBar rbQualite = (RatingBar)evalLayout.findViewById(R.id.rbQualite);
		 helpBuilder.setView(evalLayout);
		 
		 helpBuilder.setPositiveButton("Envoyer l'évalutation",
		   new DialogInterface.OnClickListener() {

		    public void onClick(DialogInterface dialog, int which) {
		    	sendRating(rbQualite.getRating());
		    	evalue = true;
		    	Button btnEval = (Button) findViewById(R.id.btnEvaluer);
		    	btnEval.setEnabled(false);
		    }
		   });
		 helpBuilder.setNegativeButton("Annuler",
				   new DialogInterface.OnClickListener() {

				    public void onClick(DialogInterface dialog, int which) {
				    	
				    }
				   });
		
		 AlertDialog helpDialog = helpBuilder.create();
		 helpDialog.show();
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		
		getMenuInflater().inflate(R.menu.reponse, menu);
		return true;
	}
	
	public void sendRating(float rating){
		
		Toast.makeText(EvaluationActivity.this, "L'évaluation de "+rating+" étoiles a été envoyé!", Toast.LENGTH_SHORT).show();
		
	}

}
