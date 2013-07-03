package fr.utt.socialize.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class  MaBaseSQLite extends SQLiteOpenHelper {
 
	/* ---------------- debut declaration de la table personne----------------*/
	
	public static final String PERSONNE = "personne";
	public static final  String _ID="colId";
	public static final  String COLNOM="nom";
	public static final  String COLPHONE="phone";
	
	
/* ---------------- debut declaration de la table personne----------------*/
	
	public static final String CONTACTMAIL = "contactmail";
	public static final  String IDCONTACTMAIL="idcontactmail";
	public static final  String COLNAMEMAIL="name_mail";
	public static final  String COLMAIL="mail";
	
	
	/* fin déclaration table personne*/
	
	/*-----------------------debut declaration de la table groupe----------------------------*/
 

	public static final String GROUPE = "groupe";
	public static final String ID_GROUPE = "id_groupe";
	public static final String NOM_GROUPE = "nom_groupe";
//	private static final String CANAL_PRIVILEGIE = "canal_groupe";
//			
//	
//	private static final String TABLE_GROUPE = "create table "
//	+ GROUPE + " (" + ID_GROUPE
//	+ " integer primary key autoincrement, " + NOM_GROUPE
//	+ " text not null, " + CANAL_PRIVILEGIE + " integer default null " 
//    +" REFERENCES"+ "CANAL(id_canal));";
//	
//	/* fin declaration table groupe*/
//	
//	/*--------------------------------  debut declaration table canal--------------------------------------*/
//	
//	
//	
	public static final String CANAL= "canal";
	public static final String ID_CANAL = "id_canal";
	public static final String NOM_CANAL = "nom_canal";	
	
//	
// 	/* fin declaration table canal*/
//	
///*--------------------------------  debut declaration table personne_groupe--------------------------------------*/

	public static String GROUPEMEMBRE = "groupmembre";
	public static final  String _IDMEMBRE="colIdmembre";
	public static final  String COLNOMGROUPE="nomgroupe";
	public static final  String COLNAMEMBRE="namemembre";
	public static final  String COLPHONEMEMBER="phonemember";

	///*--------------------------------  debut declaration table personne_groupeMaile--------------------------------------*/

		public static String GROUPEMAILCONTACT= "groupmailcontact";
		public static final  String IDMAILGROUP="idmailgroup";
		public static final  String NOMGROUPMAIL="nomgroupmail";
		public static final  String NAMECONTACTMAIL="namecontactmail";
		public static final  String MAILCONTACT="mailcontact";

	
	

//	private static final String TABLE_PERS_GROUPE = "create table "
//	+ PERS_GROUPE + "(" + ID_PERS_GROUPE
//	+ " integer primary key autoincrement, "
//    + ID_PGROUPE + "integer default null" 
//    + "REFERENCES" + "GROUPE(ID_GROUPE);"
//    + ID_GPERS + "integer default null" 
//    + "REFERENCES" + "PERSONNE(ID_PERS));";
//	
//	
//	/*--------------------------------  debut declaration table virtual_identity--------------------------------------*/
//	
//	
//	
//	private static final String VIRTUAL= "virtual";
//	private static final String ID_VIRTUAL = "id_virtual";
//	private static final String ID_VIRTUAL_CANAL = "id_canal";	
//	private static final String ID_VIRTUAL_PERS = "id_pers";	
//
//	private static final String TABLE_VIRTUAL = "create table "
//	+ VIRTUAL + " (" + ID_VIRTUAL
//	+ " integer primary key autoincrement, "
//	+ ID_VIRTUAL_CANAL+ "integer not null"
//    +" REFERENCES"+ "CANAL(ID_CANAL)" 
//	+ ID_VIRTUAL_PERS+ "integer not null"
//    +" REFERENCES"+ "PERSONNE(ID_PERS));";
//	
// 	/* fin declaration table VIRTUAL*/
//	
//	/*--------------------------------  debut declaration table message--------------------------------------*/

	
	public static final String HISTORIQUE_MESSAGE= "historique_message";
	public static final String ID_MESSAGE = "id_message";
	public static final String DATE = "date";	
	public static final String CONTENU = "contenu";
	public static final String DESTINATAIRE= "destinataire";

	//public static final String PHONECONTACT= "contact_number";
	//public static final String NAME_PERS= "nameContact";

//	private static final String HISTORIQUE_MESSAGE = "create table "
//	+ HISTORIQUE_MESSAGE + "(" + ID_MESSAGE
//	+ " integer primary key autoincrement, "
//	+ DATE + "datetime not null,"
//    + DESCRIPTION + "text default null,"
//    + ID_MESS_GROUPE + "integer default null" 
//    + "REFERENCES" + "GROUPE(ID_GROUPE);"
//    + ID_MESS_PERS + "integer default null" 
//    + "REFERENCES" + "PERSONNE(ID_PERS));";
	
 	/*---------------  fin declaration table message -----------*/
	// ------------ declaration des parametres de la base
	private static final int DATABASE_VERSION = 2; // version pour la mise à jour de l'application
	private static final String DATABASE_NAME= "messenger_db";
	
	public MaBaseSQLite(Context context) {
	
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
		
	}

	
	/* -------------------- creation des table---------------------*/
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		/* methode creation table personne*/
		db.execSQL("create table "
				+ PERSONNE + " (" + _ID
				+ " integer primary key autoincrement, " + COLNOM
				+ " text, " + COLPHONE + " text);");
		
		/* methode creation table contactMail*/
		db.execSQL("create table "
				+ CONTACTMAIL + " (" + IDCONTACTMAIL
				+ " integer primary key autoincrement, " + COLNAMEMAIL
				+ " text, " + COLMAIL + " text);");
		
		
		
		/* methode creation table groupe*/
		db.execSQL(  "create table "
		+ GROUPE + " (" + ID_GROUPE
		+ " integer primary key autoincrement, " + NOM_GROUPE
		+ " text not null);");
		
		/* create table personne_groupe*/
		
		db.execSQL("create table "
				+ GROUPEMEMBRE + " (" + _IDMEMBRE
				+ " integer primary key autoincrement, "
				+ COLNOMGROUPE+ " text, " 
				+ COLNAMEMBRE + " text, "
				+ COLPHONEMEMBER + " text);");
		
/* create table personne_groupe mail*/
		
		db.execSQL("create table "
				+ GROUPEMAILCONTACT + " (" + IDMAILGROUP
				+ " integer primary key autoincrement, "
				+ NOMGROUPMAIL+ " text, " 
				+ NAMECONTACTMAIL + " text, "
				+ MAILCONTACT + " text);");
		
		
		/* creation de la table canal*/
		db.execSQL("create table "
				+ CANAL + " (" + ID_CANAL
				+ " integer primary key autoincrement, " + NOM_CANAL
				+ " text not null);");
		
		/* creation table virtual*/
		//db.execSQL(TABLE_VIRTUAL);
		
		/* creation table message*/
//		db.execSQL("create table "
//		+ HISTORIQUE_MESSAGE
//		+ "("+ ID_MESSAGE + " integer primary key autoincrement, "
//		+ DATE + "datetime NOT NULL default '0000-00-00 00:00:00',"
//	    + CONTENU + "text default null,"
//	    + ID_CONTACT + "interger " +"REFERENCES " + "PERSONNE(ID_PERS));");	
		

		db.execSQL("create table "
				+ HISTORIQUE_MESSAGE
				+ "("+ ID_MESSAGE + " integer primary key autoincrement, "
			    + CONTENU + " text,"
			    + DESTINATAIRE + " text);");		
				
			/* add foreign key*/
		
		 db.execSQL("PRAGMA foreign_keys = ON;");
		
				
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
		//On peut fait ce qu'on veut ici moi j'ai décidé de supprimer la table et de la recréer
				//comme ça lorsque je change la version les id repartent de 0
				db.execSQL("DROP TABLE " + PERSONNE + ";");
				onCreate(db);
				
				db.execSQL("DROP TABLE " + GROUPE + ";");
				onCreate(db);
				
								
//				db.execSQL("DROP TABLE " + TABLE_VIRTUAL + ";");
//				onCreate(db);
				
				db.execSQL("DROP TABLE " + HISTORIQUE_MESSAGE + ";");
				onCreate(db);
				
				db.execSQL("DROP TABLE " + GROUPEMEMBRE + ";");
				onCreate(db);
				
				db.execSQL("DROP TABLE " + GROUPEMAILCONTACT + ";");
				onCreate(db);
				
				
				
		
	}


	public static String getTablePersonne() {
		return PERSONNE;
	}


	

	public static String getTableGroupe() {
		return GROUPE;
	}


	public static String getTableCanal() {
		return CANAL;
	}


	

//	public static String getTableVirtual() {
//		return TABLE_VIRTUAL;
	//}

	
	

	public static String getTableMessage() {
		return HISTORIQUE_MESSAGE;
	}


	public static String getTableGROUPEMEMBRE() {
		return GROUPEMEMBRE;
	}
	
	public static String getTableGROUPEMAILCONTACT() {
		return GROUPEMEMBRE;
	}

	
	
}

