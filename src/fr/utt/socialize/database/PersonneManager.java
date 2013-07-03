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
public class PersonneManager {
	

	//private OpenDbHelper dbHelper;
	private MaBaseSQLite mabasesqlite;

	public PersonneManager (Context context) {
		mabasesqlite = new MaBaseSQLite(context);
	}

	/**
	 * Insert a Stock-value into database
	 * @param stockId id of the stock
	 * @param stockDesc description of the stock
	 * @return success or fail
	 */
	
	/* step 1 phone manager*/
	
	public void createPersonne(String nom, String phone) {
	    
		ContentValues Values = new ContentValues();
		
		//Personne personne=new Personne(nom,prenom);
		SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

		String[] allColumns = { MaBaseSQLite._ID,   MaBaseSQLite. COLNOM, MaBaseSQLite.COLPHONE};

		Values.put( MaBaseSQLite. COLNOM,nom);
		Values.put( MaBaseSQLite.COLPHONE,phone);

		
		//Values.put(MaBaseSQLite.COLPRENOM, prenom);
		
	    long insertId = sqlite.insert(MaBaseSQLite.PERSONNE,null, Values);
	    Cursor cursor = sqlite.query(MaBaseSQLite.PERSONNE,allColumns , MaBaseSQLite._ID + " = " + insertId, null,null, null, null);
	    cursor.moveToFirst();
	    cursor.close();
	    sqlite.close();
	  }
	
	/********  --------------------------------- *****/
	
	public List<Personne> getAllPersonne() {
	    List<Personne> Personnes = new ArrayList<Personne>();
		SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

		String[] allColumns = { MaBaseSQLite._ID,   MaBaseSQLite. COLNOM, MaBaseSQLite.COLPHONE};
		
	    Cursor cursor = sqlite.query("personne",allColumns, null, null, MaBaseSQLite. COLNOM, null, null);
	    

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Personne Personne = cursorToPersonne(cursor);
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

		String[] allColumns = { MaBaseSQLite._ID,   MaBaseSQLite. COLNOM, MaBaseSQLite.COLPHONE};
		
	    Cursor cursor = sqlite.query("personne",allColumns, null, null, null, null, MaBaseSQLite. COLNOM);
	    

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Personne personne = cursorToPersonne(cursor);
	      contactNames.add(personne.getNom());
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return contactNames;
	  }

	  public Personne cursorToPersonne(Cursor cursor) {
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

			String[] allColumns = { MaBaseSQLite._ID,   MaBaseSQLite. COLNOM, MaBaseSQLite.COLPHONE};
			
		    Cursor cursor = sqlite.query("personne",allColumns, null, null, MaBaseSQLite._ID + " = " + "id", null, null);
		    

		      personne = cursorToPersonne(cursor);
		   		    // Make sure to close the cursor
		    cursor.close();
		    return personne;
		  }
	 
	  
	  /*FIND member with name*/
	  
	  public Personne getPersonneWith(String name) {
		   Personne personne= new Personne();
			SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

			String[] allColumns = { MaBaseSQLite._ID,   MaBaseSQLite. COLNOM, MaBaseSQLite.COLPHONE};
			
		   // Cursor cursor = sqlite.query("personne",allColumns,MaBaseSQLite. COLNOM + " = " + "name", null, null, null,null);
		    
		    String[] args={name};
			
            Cursor cursor = sqlite.rawQuery("SELECT * FROM personne WHERE nom=?", args);

		    

		      personne = cursorToPersonne(cursor);
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

			sqlite.delete(MaBaseSQLite.PERSONNE, "", null);
			
			sqlite.close();
			
		} catch (SQLException sqlerror) {

			Log.v("delete from table error", sqlerror.getMessage());

		}	
		
	}
	
	


	
}




