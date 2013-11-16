package com.eecs.seg2505_2013;

import java.util.ArrayList;
import java.util.List;

import com.swarmconnect.SwarmMessage;
import com.swarmconnect.SwarmMessageThread;
import com.swarmconnect.SwarmMessageThread.GotMessagesCB;
import com.swarmconnect.SwarmMessageThread.GotThreadsCB;
import com.swarmconnect.SwarmUser;
import com.swarmconnect.SwarmUser.GotUserCB;

public class MyApplication extends android.app.Application {

	public void sendMessage(final String message, String toUsername) {
		SwarmUser.getUser(toUsername, new GotUserCB() {
			@Override
			public void gotUser(SwarmUser user) {
				if (user != null) {
					SwarmMessage.sendMessage(user.userId, message, null);
				}
			}
		});
	}
	
	public void getMessagesFromUser(final String fromUsername, final Requester requester, final int requestID) {
		SwarmMessageThread.getAllThreads(new GotThreadsCB() {
			@Override
			public void gotThreads(List<SwarmMessageThread> threads) {
				for (SwarmMessageThread swarmMessageThread : threads) {
					if (swarmMessageThread.otherUser.username.equals(fromUsername)) {
						swarmMessageThread.getMessages(new GotMessagesCB() {
							@Override
							public void gotMessages(List<SwarmMessage> messages) {
								final ArrayList<String> messageList = new ArrayList<String>();
								for (SwarmMessage message : messages) {
									if (message.from.username.equals(fromUsername)) {
										messageList.add(message.message);
									}
								}
								requester.acceptAnswer(requestID, messageList);
							}
						});
					}
				}
			}
		});
	}
}
