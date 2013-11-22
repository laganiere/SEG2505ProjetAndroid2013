package com.eecs.seg2505_2013;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.swarmconnect.SwarmActivity;

public class MessagesActivity extends SwarmActivity implements Requester {

	public static int GET_MESSAGES_REQUEST_ID = 1;
	private int requestID;
	private Object answer;
	
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

	@Override
	public void acceptAnswer(int currentRequestID, Object currentAnswer) {
		
		requestID= currentRequestID;
		answer= currentAnswer;
		
		runOnUiThread(new Runnable() {
            @Override
            public void run() {
        		if (requestID == GET_MESSAGES_REQUEST_ID) {
        			List<String> messages = (List<String>)answer;
        			for (String message : messages) {
        				Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
        			}
        		}
            }
        });
	}
}
