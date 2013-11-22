package com.eecs.seg2505_2013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.os.Bundle;

import com.swarmconnect.SwarmMessage;
import com.swarmconnect.SwarmMessageThread;
import com.swarmconnect.SwarmMessageThread.GotMessagesCB;
import com.swarmconnect.SwarmMessageThread.GotThreadsCB;
import com.swarmconnect.SwarmUser;
import com.swarmconnect.SwarmUser.GotUserCB;

public class MyApplication extends android.app.Application {
	
	List<String> superDomaines;
	Map<String, List<String>> sousDomaines;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		superDomaines= Arrays.asList(getResources().getStringArray(R.array.super_categories));
		createSousDomaines();
	}
	
	// envoie d'une question
	public void sendQuestion(/* Question q*/) {

		// chercher un expert et creer le message
		sendMessage("Q: Quelle heure est-il?","swarm@laganiere.name");
	}
		
	// obtention de la liste des categories
	public List<String> getSuperDomaines() {
        return superDomaines;
    }	

	// obtention de la liste des sous- categories
	public Map<String, List<String>> getSousDomaines() {
        return sousDomaines;
    }	

	// envoie d'un message avec Swarm
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
	
	// reception de messages avec Swarm
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

	// reception de messages avec Swarm
	public void getQuestionMessages(final Requester requester, final int requestID) {
		SwarmMessageThread.getAllThreads(new GotThreadsCB() {
			@Override
			public void gotThreads(List<SwarmMessageThread> threads) {
				for (SwarmMessageThread swarmMessageThread : threads) {
						swarmMessageThread.getMessages(new GotMessagesCB() {
							@Override
							public void gotMessages(List<SwarmMessage> messages) {
								final ArrayList<String> messageList = new ArrayList<String>();
								for (SwarmMessage message : messages) {
									if (message.message.startsWith("Q:")) {
										// create question
									}
								}
								requester.acceptAnswer(requestID, "question ici");
							}
						});
				}
			}
		});
	}

	// creation des sous-domaines
    private void createSousDomaines() {
        String[] sousCat1 = getResources().getStringArray(R.array.sous_categories_1);
        String[] sousCat2 = getResources().getStringArray(R.array.sous_categories_2);
        String[] sousCat3 = getResources().getStringArray(R.array.sous_categories_3);
 
        sousDomaines = new LinkedHashMap<String, List<String>>();
        
        for (String string : superDomaines) {
        	if (string.equals(superDomaines.get(0))) {
        		sousDomaines.put(string, Arrays.asList(sousCat1));
        	} else if (string.equals(superDomaines.get(1))) {
        		sousDomaines.put(string, Arrays.asList(sousCat2));
        	} else if (string.equals(superDomaines.get(2))) {
        		sousDomaines.put(string, Arrays.asList(sousCat3));
        	}
		}
    }

}
