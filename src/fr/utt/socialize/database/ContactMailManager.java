	package fr.utt.socialize.database;

	import java.util.ArrayList;
	import java.util.List;
	import android.content.ContentValues;
	import android.content.Context;
	import android.database.Cursor;
	import android.database.SQLException;
	import android.database.sqlite.SQLiteDatabase;
	import android.util.Log;
import fr.utt.socialize.modele.Personne;

	/* IUD classe pouvant faire les operations insert, update and delete d'une table*/
	public class ContactMailManager {
		

		//private OpenDbHelper dbHelper;
		private MaBaseSQLite mabasesqlite;

		public ContactMailManager (Context context) {
			mabasesqlite = new MaBaseSQLite(context);
		}

		
		/* step 2 mail manager*/
		
	public void createMailContact(String nom, String mail) {
		    
			ContentValues Values = new ContentValues();
			
			//Personne personne=new Personne(nom,prenom);
			SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

			String[] allColumns = { MaBaseSQLite.IDCONTACTMAIL,   MaBaseSQLite.COLNAMEMAIL, MaBaseSQLite.COLMAIL};

			Values.put(MaBaseSQLite.COLNAMEMAIL,nom);
			Values.put( MaBaseSQLite.COLMAIL,mail);
			
			//Values.put(MaBaseSQLite.COLPRENOM, prenom);
			
		    long insertId = sqlite.insert(MaBaseSQLite.CONTACTMAIL,null, Values);
		    Cursor cursor = sqlite.query(MaBaseSQLite.CONTACTMAIL,allColumns , MaBaseSQLite.IDCONTACTMAIL + " = " + insertId, null,null, null, null);
		    cursor.moveToFirst();
		    cursor.close();
		    sqlite.close();
		  }
		

	
	/********  --------------------------------- *****/
	
	public List<Personne> getAllMailContact() {
	    List<Personne> Personnes = new ArrayList<Personne>();
		SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

		String[] allColumns = { MaBaseSQLite.IDCONTACTMAIL,   MaBaseSQLite.COLNAMEMAIL, MaBaseSQLite.COLMAIL};
		
	    Cursor cursor = sqlite.query("contactmail",allColumns, null, null, MaBaseSQLite.COLNAMEMAIL, null, null);
	    

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Personne Personne = cursorToMailConctact(cursor);
	      Personnes.add(Personne);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return Personnes;
	  }
	
	/* all contact name*/
	
	public List<String> getAllName() {
	    List<String> contactNames = new ArrayList<String>();
		SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

		String[] allColumns = { MaBaseSQLite.IDCONTACTMAIL,   MaBaseSQLite.COLNAMEMAIL, MaBaseSQLite.COLMAIL};
		
	    Cursor cursor = sqlite.query("contactmail",allColumns, null, null, null, null, MaBaseSQLite.COLNAMEMAIL);
	    

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Personne personne = cursorToMailConctact(cursor);
	      contactNames.add(personne.getNom());
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return contactNames;
	  }

	  public Personne cursorToMailConctact(Cursor cursor) {
	    Personne Personne = new Personne();
	    Personne.setId_personne(cursor.getString(0));
	    Personne.setNom(cursor.getString(1));
	    Personne.setmedia(cursor.getString(2));

	    return Personne;
	  }
	  
	  
	  /* fin a personne with id*/
	  
	  public Personne getPersonne(String id) {
		   Personne personne= new Personne();
			SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

			String[] allColumns = { MaBaseSQLite.IDCONTACTMAIL,   MaBaseSQLite.COLNAMEMAIL, MaBaseSQLite.COLMAIL};
			
		    Cursor cursor = sqlite.query("contactmail",allColumns, null, null, MaBaseSQLite.IDCONTACTMAIL + " = " + "id", null, null);
		    

		      personne = cursorToMailConctact(cursor);
		   		    // Make sure to close the cursor
		    cursor.close();
		    return personne;
		  }
	 
	  
	  /*FIND member with name*/
	  
	  public Personne getPersonneWith(String name) {
		   Personne personne= new Personne();
			SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

			String[] allColumns = { MaBaseSQLite.IDCONTACTMAIL,   MaBaseSQLite.COLNAMEMAIL, MaBaseSQLite.COLMAIL};
			
		   // Cursor cursor = sqlite.query("personne",allColumns,MaBaseSQLite. COLNOM + " = " + "name", null, null, null,null);
		    
		    String[] args={name};
			
            Cursor cursor = sqlite.rawQuery("SELECT * FROM contactmail WHERE name_mail=?", args);
		      personne = cursorToMailConctact(cursor);
		   		    // Make sure to close the cursor
		    cursor.close();
		    return personne;
		  }
	  

	  
	

	/**
	 * Clear the table
	 */
	public void clear() {

		try {

			SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

			sqlite.delete(MaBaseSQLite.CONTACTMAIL, "", null);
			
			sqlite.close();
			
		} catch (SQLException sqlerror) {

			Log.v("delete from table error", sqlerror.getMessage());

		}	
		
	}



	}




