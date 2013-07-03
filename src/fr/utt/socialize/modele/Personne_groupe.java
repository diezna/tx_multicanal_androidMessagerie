package fr.utt.socialize.modele;

public class Personne_groupe {

	
	private String name_groupe;
	private String name_personne;
	private String media_contact;

	private String id_personne_groupe;
	
	
	public Personne_groupe(){
		
	}
	
public Personne_groupe(String nom_groupe,String name_personne, String media_contact){
	
	this.name_groupe=nom_groupe;
	this.name_personne=name_personne;
	this.media_contact=media_contact;
		
	}


	public String getName_groupe() {
		return name_groupe;
	}


	public String getname_personne() {
		return name_personne;
	}


	public void setName_groupe(String nom_groupe) {
		this.name_groupe = nom_groupe;
	}


	public void setName_personne(String name_personne) {
		this.name_personne = name_personne;
	}


	public String getId_personne_groupe() {
		return id_personne_groupe;
	}


	public void setId_personne_groupe(String id_personne_groupe) {
		this.id_personne_groupe = id_personne_groupe;
	}


	
	public String getmedia_contact() {
		return media_contact;
	}

	public void setmedia_contact(String media_contact) {
		this.media_contact = media_contact;
	}

/*	@Override
	public String getString() {
		return  name_groupe +  name_personne + media_contact;
	}*/
	
	@Override
	public String toString() {
		return  name_personne + "\n"+ media_contact;

	}

	
	
}
