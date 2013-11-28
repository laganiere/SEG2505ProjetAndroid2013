package com.eecs.seg2505_2013;

import java.util.Date;

<<<<<<< HEAD
public class Reponse implements Comparable<Reponse> {
	
	private String text;
	private Date date;
	private int qualite;
	private Utilisateur utilisateur;
	private Question question;
	
	
	public Reponse(Question question, Utilisateur utilisateur){
		this.question = question;
		this.utilisateur = utilisateur;
	}
	public Reponse(String text, Question question, Utilisateur utilisateur){
		this.text = text;
		this.question = question;
	}
	
	public Reponse(String text, Date date, int qualite, Question question, Utilisateur utilisateur){
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

=======
/**
 * Created with IntelliJ IDEA.
 * User: Julien Mageau, Nicholas Horton, Nicholas Seguin, Catherine Maathuis
 * Date: 21/11/13
 * Time: 8:18 PM
 */
public class Reponse {
    private String texte;
    private Date date;
    private int qualite;

    Question question;
    Utilisateur utilisateur;

    /**
     * Une reponse a une question, avec par defaut une qualite de 0
     *
     * @param question
     * @param utilisateur
     */
    public Reponse(Question question, Utilisateur utilisateur) {
        this.qualite = 0;
        this.question = question;
        this.utilisateur = utilisateur;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date d) {
        this.date = d;
    }

    public int getQualite() {
        return qualite;
    }

    public void setQualite(int amount){
        this.qualite = amount;
    }

    public Question getQuestion() {
        return question;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
>>>>>>> pr/3
}
