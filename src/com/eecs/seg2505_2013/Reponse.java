package com.eecs.seg2505_2013;

import java.util.Date;


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

}
