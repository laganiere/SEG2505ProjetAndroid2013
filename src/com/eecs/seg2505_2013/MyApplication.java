package com.eecs.seg2505_2013;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.swarmconnect.Swarm;
import com.swarmconnect.SwarmMessage;
import com.swarmconnect.SwarmMessageThread;
import com.swarmconnect.SwarmMessageThread.GotMessagesCB;
import com.swarmconnect.SwarmMessageThread.GotThreadsCB;
import com.swarmconnect.SwarmUser;
import com.swarmconnect.SwarmUser.GotUserCB;

public class MyApplication extends android.app.Application {
	
	public static String QUESTION_PREFIX = "Q:";
	public static String QUESTION_SEPARATOR = ":";
	
	List<String> superDomaines;
	Map<String, List<String>> sousDomaines;
	Map<String, List<String>> experts;
	
	@Override
	public void onCreate() {
		super.onCreate();
		
		superDomaines= Arrays.asList(getResources().getStringArray(R.array.super_categories));
		createSousDomaines();
		experts = new HashMap<String, List<String>>();
	}
	
	// envoie d'une question
	public void sendQuestion(Question q) {
		if (q != null) {
			Domaine domaine = q.getDomaine();
			if (domaine != null) {
				// chercher un expert
				List x = getExperts(domaine.getNom());
				if (x != null
						&& x.size() > 0) {
					String expert = (String)x.get(0);
					String questionPhrase = q.getTexte();
					if (questionPhrase != null) {
						sendMessage(QUESTION_PREFIX + domaine.getNom() + QUESTION_SEPARATOR + questionPhrase, expert);
					}
				}
			}
		}
	}
		
	// obtention automatique d'un expert en donnant le domaine d'expertise
	// retourne une liste d'expert qui peut etre vide s'il n y a pas d'experts correspondant
	private List<String> getExperts(String domaine) {
		if (this.experts.get(domaine) != null) {
			return this.experts.get(domaine);
		}
		
		List<String> experts_liste = new ArrayList<String>();
		int id = getResources().getIdentifier(domaine, "array", getPackageName());
		if (id != 0) {
			String[] experts = getResources().getStringArray(id);
			if (experts != null) {
				List<String> result = Arrays.asList(experts);
				experts_liste.addAll(result);
				this.experts.put(domaine, result);
			}
		}
		
		return experts_liste;
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
				final ArrayList<Question> questionsList = new ArrayList<Question>();
				for (SwarmMessageThread swarmMessageThread : threads) {
						swarmMessageThread.getMessages(new GotMessagesCB() {
							@Override
							public void gotMessages(List<SwarmMessage> messages) {
								final ArrayList<Question> questionsList = new ArrayList<Question>();
								for (SwarmMessage message : messages) {
									// on s'assure que la question n'est pas la notre
									if (!message.from.username.equals(Swarm.user.username)) {
										if (message.message.startsWith(QUESTION_PREFIX)) {
											// create question
											Question q = parseQuestion(message.message, message.from.username);
											questionsList.add(q);
										}
									}
								}
								requester.acceptAnswer(requestID, questionsList);
							}
						});
				}
				requester.acceptAnswer(requestID, questionsList);
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
    
    // Cree une question a partir d'une chaine de charactere formattee pour ce but la
    public Question parseQuestion(String question, String username) {
    	Question q = new Question(new Utilisateur(username));
		String domaineAndTexte = question.substring(QUESTION_PREFIX.length());
		int index = domaineAndTexte.indexOf(QUESTION_SEPARATOR);
		String domaine = domaineAndTexte.substring(0, index);
		String texte = domaineAndTexte.substring(index+1);
		
		q.setDomaine(new Domaine(domaine));
		q.setTexte(texte);
    	return q;
    }
}
