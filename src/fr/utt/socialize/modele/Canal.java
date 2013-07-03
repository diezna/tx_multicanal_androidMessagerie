package fr.utt.socialize.modele;


public class Canal {
	
	private int id_canal;
	private String nom;
	
	public Canal(String nom) {
		this.nom = nom;
	}

	
	public Canal() {
		// TODO Auto-generated constructor stub
	}


	public int getId_canal() {
		return id_canal;
	}

	public void setId_canal(int id_canal) {
		this.id_canal = id_canal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Override
	public String toString() {
		return  nom ;
	}

	
}
