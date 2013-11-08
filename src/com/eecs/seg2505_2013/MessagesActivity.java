package com.eecs.seg2505_2013;

import java.util.List;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmActivity;
import com.swarmconnect.SwarmMessage;
import com.swarmconnect.SwarmMessageThread;
import com.swarmconnect.SwarmMessageThread.GotMessagesCB;
import com.swarmconnect.SwarmMessageThread.GotThreadsCB;
import com.swarmconnect.SwarmUser;
import com.swarmconnect.SwarmUser.GotUserCB;

public class MessagesActivity extends SwarmActivity {

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
				
		SwarmMessageThread.getAllThreads(new GotThreadsCB() {
			// boucle de lecture des messages
			
			@Override
			public void gotThreads(List<SwarmMessageThread> threads) {
				Toast.makeText(getApplicationContext(), ""+threads.size(), Toast.LENGTH_SHORT).show();
				for (SwarmMessageThread swarmMessageThread : threads) {
					swarmMessageThread.getMessages(new GotMessagesCB() {
						
						@Override
						public void gotMessages(List<SwarmMessage> messages) {
							
							for (SwarmMessage message : messages) {
								if (!message.from.username.equals(Swarm.user.username)) {
									Toast.makeText(getApplicationContext(), message.message, Toast.LENGTH_SHORT).show();
								}
							}
						}
					});
				}
			}
		});
	}
	
	public void onSendMessage(View view) {
		String name = ((EditText)findViewById(R.id.editTextName)).getText().toString();
		final String message = ((EditText)findViewById(R.id.editTextMessage)).getText().toString();
		
		SwarmUser.getUser(name, new GotUserCB() {
			
			@Override
			public void gotUser(SwarmUser user) {
				if (user != null) {
					SwarmMessage.sendMessage(user.userId, message, null);
				}
			}
		});
	}
}
