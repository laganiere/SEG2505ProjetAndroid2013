package com.eecs.seg2505_2013;

import java.util.*;

public class Domaine implements Comparable<Domaine> {
	
	private String nom;
	private Domaine superDomaine;
	private List<Domaine> sousdomaines;

     // Construction d'un sous-domaine
	public Domaine(String nom, Domaine superDomaine)
	{
		this.nom = nom;
		this.superDomaine = superDomaine;
		this.sousdomaines = null;
		superDomaine.getSousDomaine().add(this);
	}

     // Construction d'un domaine
	public Domaine(String nom)
	{

		this.nom = nom;
		this.superDomaine = null;
		this.sousdomaines = new ArrayList<Domaine>(3);
	}

     // Ajout d'un sous-domaine
	public boolean addSousDomaine(String nom)
	{
		if (isSousDomaine()) return false;

		sousdomaines.add(new Domaine(nom, this));
		return true;
	}
	
      
     // Ajout d'un sous-domaine
	public boolean addSousDomaine(Domaine sousDomaineToAdd) {

		if (isSousDomaine()) return false;

		sousdomaines.add(sousDomaineToAdd);
		return true;
	}

     // Retourne le super-domaine 
     // (null si c'est un super-domaine)		
	public Domaine getSuperDomaine(){

		return superDomaine;
	}

     // retourne le nom d'un domaine	
	public String getNom(){

		return nom;
	}
	
     // donne un nom au domaine	
	public boolean setNom(String nom){
		
		this.nom = nom;	
		return true;
	}
		
     // retire un sous-domaine
	public boolean remove(Domaine domaine){

		if (isSousDomaine()) return false;

		return sousdomaines.remove(domaine);
	}

     // conversion a une chaine de caracteres
	public String toString() {
		if(isSousDomaine()) {
			return this.superDomaine.getNom() 
                            + " :\n" + nom;
		} else {
			return nom;
		}
	}

     // obtenir la liste des sous-domaines
	public List<Domaine> getSousDomaine()
	{
		return sousdomaines;
	}

     // egalite entre domaines
	public boolean equals(Object o){

		return toString().equals(o.toString());
	}
	
     // est-ce un sous-domaine
	public boolean isSousDomaine() {
		return superDomaine != null;
	}

     // comparaison entre domaines
	public int compareTo(Domaine other) {
		return toString().compareTo(other.toString());
	}
}
