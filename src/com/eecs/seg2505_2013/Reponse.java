package com.eecs.seg2505_2013;

import java.util.Date;

public class Reponse implements Comparable<Reponse> {
	
	private String text;
	private Date date;
	private int qualite;
	private Utilisateur utilisateur;
	private Question question;
	
	public Reponse(String text, Question question){
		this.text = text;
		this.question = question;
	}
	
	public Reponse(String text, Date date, int qualite, Question question){
		this.text = text;
		this.date = date;
		this.qualite = qualite;
		this.question = question;
	}
	
	public Reponse(Reponse reponse){
		this.text = reponse.getText();
		this.date = reponse.getDate();
		this.qualite = reponse.getQualite();
		this.question = reponse.getQuestion();
	}

	public String getText() {
		return text;
	}
	
	public Date getDate(){
		return date;
	}
	
	public int getQualite(){
		return qualite;
	}
	
	public void setQualite(int newQualite){
		qualite = newQualite;
	}
	
	public Utilisateur getUtilisateur(){
		return utilisateur;
	}
	
	public Question getQuestion(){
		return question;
	}
	
	public Reponse deepCopy(){
		return new Reponse(this);
	}
	
	@Override
	public String toString(){
		String result = "Text: " +getText() + "\n";
		result += "Date: " +getDate() + "\n";
		result += "Utilisateur: " +getUtilisateur() + "\n";
		result += "Question: " +getQuestion() + "\n";
		result += "Qualite: " +getQualite() + "\n";
		return result; 
	}
	@Override
	public int compareTo(Reponse other) {
		return toString().compareTo(other.toString());
	}

}
