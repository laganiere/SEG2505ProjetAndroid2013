package com.eecs.seg2505_2013;

import java.util.Date;

public class Question implements Comparable<Question> {

	public enum State {
		NEW,		//When just created
		ASKED, 		//When sent to the server
		ANSWERED, 	//Has an answer
		RATED;		//User has rated answer
	}

	private String text;
	private Date date;
	private State etat;
	private Domaine domaine;
	private Reponse reponse;
	private Utilisateur utilisateur;

	public Question(Utilisateur utilisateur){
		this.utilisateur = utilisateur;
		etat = State.NEW;
	}
	
	/**
	 * Creates a new Question with the provided text.
	 * Uses the current time and sets the State to "new"
	 * @param text
	 */
	public Question(String text, Domaine domaine, Utilisateur utilisateur) {
		this.text = text;
		date = new Date();
		etat = State.NEW;
		this.domaine = domaine;
		this.utilisateur = utilisateur;
	}
	
	public Question(String text, Date date, State etat, Domaine domaine,  Utilisateur utilisateur){
		this.text = text;
		this.date = date;
		this.etat = etat;
		this.domaine = domaine;
		this.utilisateur = utilisateur;
	}
	
	/**
	 * Creates a new Question from an existing one
	 */
	private Question(Question question){
		this.text = question.getText();
		this.date = question.getDate();
		etat = question.getState();
		this.domaine = question.getDomaine();
		this.utilisateur = question.getUtilisateur();
	}

	public String getText() {
		return text;
	}

	public void updateText(String updatedText) {
		text = updatedText;
	}
	
	public void updateState(State updatedState){
		etat = updatedState;
	}
	
	public State getState(){
		return etat;
	}
	
	public Date getDate(){
		return date;
	}
	
	/**
	 * Sets this Response date to the current one
	 */
	public void setDate(){
		this.date = new Date();
	}
	
	/**
	 * Set this response date to the provided one
	 * @param date
	 */
	public void setDate(Date date){
		this.date = date;
	}
	
	public Reponse getReponse(){
		return reponse;
	}
	
	public boolean hasReponse(){
		return null!=reponse;
	}
	
	public void changeDomaine(Domaine newDomaine){
		domaine = newDomaine;
	}
	
	public Domaine getDomaine(){
		return domaine;
	}
	
	public Utilisateur getUtilisateur(){
		return utilisateur;
	}
	
	/**
	 * Returns a copy of this question
	 * @return
	 */
	public Question deepCopy(){
		return new Question(this);
	}
	
	@Override
	public String toString(){
		String result = "Text: " +getText() + "\n";
		result += "Date: " +getDate() + "\n";
		result += "Utilisateur: " +getUtilisateur() + "\n";
		result += "Domaine: " +getDomaine() + "\n";
		result += "Etat: " +getState() + "\n";
		if(null!=getReponse()){
			result += "Reponse: " +getReponse() + "\n";
		}else{
			result += "Reponse: " + "\n";
		}
		return result; 
	}

	@Override
	public int compareTo(Question other) {
		return toString().compareTo(other.toString());
	}
}