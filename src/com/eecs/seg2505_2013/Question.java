package com.eecs.seg2505_2013;

import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: Julien Mageau, Nicholas Horton, Nicholas Seguin, Catherine Maathuis
 * Date: 21/11/13
 * Time: 8:03 PM
 */
public class Question {
    private String texte;
    private Date date;
    QuestionEtat etat;

    Domaine domaine;
    Reponse reponse;
    Utilisateur utilisateur;

    /**
     * Une question avec un etat par defaut de EN_ATTENTE
     *
     * @param utilisateur L'utilisateur qui a cree la question
     */
    public Question(Utilisateur utilisateur) {
        this.etat = QuestionEtat.EN_ATTENTE;
        this.utilisateur = utilisateur;
    }

    /**
     * Répondre à la question
     *
     * @param r La réponse
     */
    public void repondre(Reponse r) {
        this.reponse = r;
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

    public void setDate(Date date) {
        this.date = date;
    }

    public QuestionEtat getEtat() {
        return etat;
    }

    public Domaine getDomaine() {
        return domaine;
    }

    public void setDomaine(Domaine domaine) {
        this.domaine = domaine;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }
}
