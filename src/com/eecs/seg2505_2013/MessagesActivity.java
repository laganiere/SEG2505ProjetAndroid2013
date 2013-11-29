package com.eecs.seg2505_2013;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmActivity;
import com.swarmconnect.SwarmUser;

public class MessagesActivity extends SwarmActivity implements Requester {

	public static int GET_MESSAGES_REQUEST_ID = 1;
	public static int GET_QUESTIONS_REQUEST_ID = 2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messages);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.messages, menu);
		return true;
	}

	public void onGetMessages(View view) {
		String name = ((EditText)findViewById(R.id.editTextFromUser)).getText().toString();
		((MyApplication)getApplicationContext()).getMessagesFromUser(name, this, GET_MESSAGES_REQUEST_ID);
		
	}
	
	public void onSendMessage(View view) {
		String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
		String message = ((EditText)findViewById(R.id.editTextMessage)).getText().toString();
		
		((MyApplication)getApplicationContext()).sendMessage(message, name);
	}
	
	public void onGetQuestions(View view) {
		((MyApplication)getApplicationContext()).getQuestionMessages(this, GET_QUESTIONS_REQUEST_ID);
	}
	
	public void onSendQuestion(View view) {
		String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
		String message = ((EditText)findViewById(R.id.editTextMessage)).getText().toString();
		
		Question q = new Question(new Utilisateur(Swarm.user.username));
		q.setTexte(message);
		String randomDomaine = ((MyApplication)getApplicationContext()).getSuperDomaines().get(0);
		q.setDomaine(new Domaine(randomDomaine));
		((MyApplication)getApplicationContext()).sendQuestion(q);
	}

	@Override
	public void acceptAnswer(final int currentRequestID, final Object currentAnswer) {
		
		runOnUiThread(new Runnable() {
            @Override
            public void run() {
        		if (currentRequestID == GET_MESSAGES_REQUEST_ID) {
        			List<String> messages = (List<String>)currentAnswer;
        			for (String message : messages) {
        				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        			}
        		} else if (currentRequestID == GET_QUESTIONS_REQUEST_ID) {
        			List<Question> questions = (List<Question>)currentAnswer;
        			for (Question question : questions) {
        				Toast.makeText(getApplicationContext(), question.getTexte(), Toast.LENGTH_SHORT).show();
        			}
        		}
            }
        });
	}
}
