package fr.utt.socialize.modele;

public class Groupe {
	
	private String id_Groupe;
	private String nomGroupe;

	public Groupe (String nom){
		
		this.nomGroupe=nom;
	}

	
public Groupe (){
		
	}

	public String getId_Groupe() {
		return id_Groupe;
	}
	
	public String getNomGroupe() {
		return nomGroupe;
	}

	public void setId_Groupe(String id_Groupe) {
		this.id_Groupe = id_Groupe;
	}

	public void setNomGroupe(String nomGroupe) {
		this.nomGroupe = nomGroupe;
	}
	
	
	@Override
	public String toString() {
		return  nomGroupe;
	}


}
