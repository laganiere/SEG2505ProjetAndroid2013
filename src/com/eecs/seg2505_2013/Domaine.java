package com.eecs.seg2505_2013;

import java.util.*;

public class Domaine implements Comparable<Domaine> {
	
	private String nom;
	private Domaine superDomaine;
	private List<Domaine> sousdomaines;

	public Domaine(String nom, Domaine superDomaine)
	{
		this.nom = nom;
		this.superDomaine = superDomaine;
		this.sousdomaines = null;
		superDomaine.getSousDomaine().add(this);
	}

	public Domaine(String nom)
	{

		this.nom = nom;
		this.superDomaine = null;
		this.sousdomaines = new ArrayList<Domaine>(3);
	}

	public boolean addSousDomaine(String nom)
	{
		sousdomaines.add(new Domaine(nom, this));
		return true;
	}
	
	public boolean addSousDomaine(Domaine sousDomaineToAdd) {
		sousdomaines.add(sousDomaineToAdd);
		return true;
	}
		
	public Domaine getSuperDomaine(){

		return superDomaine;
	}
	
	public String getNom(){

		return nom;
	}
	
	public boolean setNom(String nom){
		
		this.nom = nom;	
		return true;
	}
		
	public boolean remove(Domaine domaine){

		return sousdomaines.remove(domaine);
	}

	public String toString() {
		if(isSousDomaine()) {
			return this.superDomaine.getNom() + " :\n" + nom;
		} else {
			return nom;
		}
	}

	public List<Domaine> getSousDomaine()
	{
		return sousdomaines;
	}

	public boolean equals(Object o){

		return toString().equals(o.toString());
	}
	
	public boolean isSousDomaine() {
		return superDomaine != null;
	}

	public int compareTo(Domaine other) {
		return toString().compareTo(other.toString());
	}
}
