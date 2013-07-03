 package fr.utt.socialize.modele;


public class Personne {
	
	private String id_personne;
	private String nom;
	private String media;
 
 
	public Personne(String nom,	String media){
		this.nom = nom;
		
		this.media=media;
	}
	public Personne(String nom){
		this.nom = nom;
		
		//this.media=media;
	}
	

	public Personne() {
		
		
		// TODO Auto-generated constructor stub
	}

	public String getId_personne() {
		return id_personne;
	}

	public void setId_personne(String id_personne) {
		this.id_personne = id_personne;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public String getmedia() {
		return media;
	}


	public void setmedia(String media) {
		this.media = media;
	}


	@Override
	public String toString() {
		return  nom +"\n" + media;
	}
 
	
 
}

