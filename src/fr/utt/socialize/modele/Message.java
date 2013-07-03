package fr.utt.socialize.modele;


public class Message {

	
	private int id_message;
	private String destinataire;
	private String contenu_message;
	private String date;
	
	
	public Message(String destinataire, String date){
		
		this.destinataire=destinataire;
		
		this.date=date;
		
		
	}

	
      public Message(){
	}

	public int getId_message() {
		return id_message;
	}




	public String getdestinataire() {
		return destinataire;
	}


	public String getContenu_message() {
		return contenu_message;
	}


	public String getDate() {
		return date;
	}


	public void setId_message(int id_message) {
		this.id_message = id_message;
	}


	public void setdestinataire(String destinataire) {
		this.destinataire = destinataire;
	}




	public void setContenu_message(String contenu_message) {
		this.contenu_message = contenu_message;
	}


	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return  /*id_message 
				+" "+*/
				destinataire 
				+" "+ contenu_message;
	}
	
	
	
	
}
