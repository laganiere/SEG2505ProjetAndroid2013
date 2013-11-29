package com.eecs.seg2505_2013;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Julien Mageau, Nicholas Horton, Nicholas Seguin, Catherine Maathuis
 * Date: 21/11/13
 * Time: 7:55 PM
 */
public class Domaine implements Comparable<Domaine> {
    private String nom;
    private List<Domaine> sousDomaines;

    /**
     * Un domaine ou sous-domaine pour catégoriser les questions
     *
     * @param nom Le nom du domaine
     */
    public Domaine(String nom) {
        this.nom = nom;
        sousDomaines = new ArrayList<Domaine>();
    }

    /**
     * Ajouter un sous domaine à un super-domaine
     *
     * @param d Le domaine à ajouter
     */
    public void ajouterSousDomaine(Domaine d) {
        this.sousDomaines.add(d);
        Collections.sort(sousDomaines);
    }

    /**
     * Retirer un sous domaine d'un super-domaine
     *
     * @param d Le domaine à enlever
     */
    public void removeSousDomaine(Domaine d) {
        sousDomaines.remove(d);
    }

    public String getNom() {
        return nom;
    }

    public List<Domaine> getSousDomaines() {
        return sousDomaines;
    }

    /**
     * Utilise pour arranger les domaines en ordre alphabetique
     *
     * @param d
     * @return
     */
    @Override
    public int compareTo(Domaine d) {
        return this.nom.compareTo(d.nom);
    }
}
