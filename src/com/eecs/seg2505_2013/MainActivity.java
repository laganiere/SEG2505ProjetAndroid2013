package com.eecs.seg2505_2013;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.swarmconnect.NotificationMessage;
import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmActivity;
import com.swarmconnect.SwarmNotification;
import com.swarmconnect.delegates.SwarmNotificationDelegate;

public class MainActivity extends SwarmActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Swarm.init(this, 7867, "69134a0037a51acc2ba200f784cf61d9");
		
		Swarm.addNotificationDelegate(new SwarmNotificationDelegate() {

			@Override
			public boolean gotNotification(SwarmNotification notif) {
				if (notif instanceof NotificationMessage) {
					NotificationMessage notifMessage = (NotificationMessage)notif;
					String message = notifMessage.getMessage(getApplicationContext());
					Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
				}
				return false;
			}

		});
	}
	
	@Override
	protected void onStart() {
		if (!Swarm.isInitialized()) 
		{
			Swarm.init(this, 7867, "69134a0037a51acc2ba200f784cf61d9");
		}
		super.onStart();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void onListeCategories(View view) {
		Intent intent = new Intent(this, ListeCategoriesActivity.class);
		startActivity(intent);
	}
	
	public void onPoserQuestion(View view) {
	}
	
	public void onRepondreQuestion(View view) {
	}
	
	public void OnLireEvaluerReponse(View view) {
	}
	
	public void onAjouterCategorie(View view) {
	}

	public void OnSwarm(View view) {
		if (Swarm.isInitialized()) {
			Swarm.showDashboard();
		}
	}
	
	public void onTestMessages(View view) {
		Intent intent = new Intent(this, MessagesActivity.class);
		startActivity(intent);
	}
}
