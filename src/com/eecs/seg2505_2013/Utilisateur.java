package com.eecs.seg2505_2013;

import java.util.*;
import java.util.Collections;
import java.util.LinkedList;

/**
 * -- Utilisateur --
 * User: Julien Mageau, Nicholas Horton, Nicholas Seguin, Catherine Maathuis
 * Date: 21/11/13
 * Time: 7:56 PM
 */
public class Utilisateur {

    private String nom = null; //Nom de l'utilisateur
    private String username = null;
    private int reputationScore = 0; //Reputation de l'utilisateur

    private LinkedList<Domaine> domaineExpertiseList;

    /**
     * Cree une nouvelle instance d'un Utilisateurs
     * DEFAULTS:    reputationScore = 0
     * domaineExpertiseList = new LinkedList<Domaine>()
     *
     * @param username Le nome d'usager de l'utilisateur.
     */
    public Utilisateur(String username) {
        this.username = username;
        this.reputationScore = 0;
        this.domaineExpertiseList = new LinkedList<Domaine>();
    }

    /**
     * GETTERS
     */
    public String getNom() {
        return this.nom;
    }

    public int getReputationScore() {
        return this.reputationScore;
    }

    public LinkedList<Domaine> getDomaineExpertiseList() {
        return this.domaineExpertiseList;
    }

    /**
     * SETTERS
     */

    /**
     * Change le nom de l'utilisateur.
     *
     * @param n Le nouveau nom
     */
    public void setNom(String n) {
        this.nom = n;
    }

    /**
     * Ajoute un certain montant a la reputation de l'utilisateur. Un nombre negatif soustrait le montant.
     *
     * @param montant Le montant a soustraire.
     */
    public void ajouterReputation(int montant) {
        reputationScore += montant;
    }

    /**
     * Ajoute un Domaine a la liste des domaines d'expertise de l'utilisateur.
     *
     * @param d Le domaine d'expertise a ajouter.
     */
    public void ajouterDomaine(Domaine d) {
        domaineExpertiseList.add(d);
        Collections.sort(domaineExpertiseList);
    }

    /**
     * Enleve un Domaine d'expertise.
     *
     * @param d Le domaine a enlever.
     */
    public void removeDomaine(Domaine d) {
        domaineExpertiseList.remove(d);
    }

    public String getUsername() {
        return username;
    }
}
