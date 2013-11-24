package com.eecs.seg2505_2013;

import java.util.*;

public class Utilisateur {
	private String nom;
	private int reputation;
	private LinkedList<Domaine> domaines;

	
	public Utilisateur(){
		domaines = new LinkedList<Domaine>(); 
	}
	
	public Utilisateur(String nom){
		this();
		this.nom = nom;
	}
	
	//Access methods pour Domaine
	
	public void addDomaine(Domaine domaine){ this.<Domaine>addElement(domaine); }
	
	public void addDomaine(String nom){this.addDomaine(new Domaine(nom)); }
	
	public String[] getDomaines(){
		Domaine[] temp = this.<Domaine>getElements(new Domaine(null));
		String[] ret = new String[temp.length];
		for(int i=0;i<temp.length && temp[i] != null;i++){
			ret[i] = temp[i].getNom();
		}
		return ret;
	}
	
	public String getDomaineName(int index) throws IllegalArgumentException { return this.<Domaine>getElement(index,new Domaine(null)).getNom(); }
	
	public boolean hasDomaine(Domaine domaine){ return this.<Domaine>hasElement(domaine); }
	
	public int getIndexOfDomaine(Domaine domaine){ return this.<Domaine>getIndexOf(domaine); }
	
	public void removeDomaine(Domaine domaine){ this.<Domaine>removeElement(domaine); }
	
	//Access methods pour Nom
	
	public void setNom(String name){ this.nom = name; }
	
	public String getNom(){ return this.nom; }
	
	//Access methods pour Reputation
	
	public void setReputation(int reputation){ this.reputation = reputation; }	
	
	public int getReputation(){ return this.reputation; }
	
	public void addToReputation(int add){ reputation += add; }
	
	//Equals, for this we assume two users can't have the same case insensitive name, hence the simplistic comparison
	
	public boolean equals(Utilisateur user){ return this.getNom().equalsIgnoreCase(user.getNom());}
	
	//To string
	
	public String toString(){
		String ret  = "Nom: "+ this.getNom() + "\n";
		ret = ret + "Domaines: " + domaines.get(0).getNom();
		
		for(int i=1;i<domaines.size();i++){
			ret += ", " + domaines.get(i).getNom();
		}
		
		return ret;
	}
	
	//Private Generic Methods
	
	private <E> void removeElement(E element){
		LinkedList<E> temp = null;
		if(element instanceof Domaine){
			temp = (LinkedList<E>)domaines;
		}
		if(temp != null){
			temp.remove(element);
		}
	}
	
	private <E> int getIndexOf(E element){
		LinkedList<E> temp = null;
		if(element instanceof Domaine){
			temp = (LinkedList<E>)domaines;
		}
		if(temp !=null){
			temp.indexOf(element);
		}
		return -1;
		
	}
	
	private <E> boolean hasElement(E element){
		LinkedList<E> temp = null;
		if(element instanceof Domaine){
			temp = (LinkedList<E>)domaines;
		}
		if(temp !=null){
			temp.contains(element);
		}
		
		return false;
	}
	
	private <E> E getElement(int index,E test){
		LinkedList<E> temp = null;
		if(test instanceof Domaine){
			temp = (LinkedList<E>)domaines;
		}
		if(temp != null){
			if(index>0 && index< temp.size()-1){
				return temp.get(index);
			}else{
				throw new IllegalArgumentException();
			}
		}else{
			return null;
		}
		
	}
	
	private <E>void addElement(E element){
		LinkedList<E> temp = null;
		if(element instanceof Domaine){
			temp = (LinkedList<E>)domaines;
		}
		if(temp !=null){
			temp.add(element);
			return;
		}
	}
	
	private <E> E[] getElements(E test){
		LinkedList<E> temp = null;
		if(test instanceof Domaine){
			temp = (LinkedList<E>)domaines;
		}
		if(temp !=null){
			Object[] ret = new Object[temp.size()];
			for(int i=0;i<temp.size();i++){
				ret[i] = temp.get(i);
			}
			
			return (E[])ret;
		}
		return null;
	}
	
}
