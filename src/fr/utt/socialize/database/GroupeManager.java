package fr.utt.socialize.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.utt.socialize.modele.Groupe;
import fr.utt.socialize.modele.Personne;

public class GroupeManager {
	

	//private OpenDbHelper dbHelper;
	private MaBaseSQLite mabasesqlite;

	public GroupeManager (Context context) {
		mabasesqlite = new MaBaseSQLite(context);
	}

	/**
	 * Insert a Stock-value into database
	 * @param stockId id of the stock
	 * @param stockDesc description of the stock
	 * @return success or fail
	 */
	
	public void createGroupe(String nom) {
	    
		ContentValues Values = new ContentValues();
		
		//Personne personne=new Personne(nom,prenom);
		SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

		String[] allColumns = { MaBaseSQLite.ID_GROUPE,   MaBaseSQLite.NOM_GROUPE};

		Values.put(  MaBaseSQLite.NOM_GROUPE,nom);
			
	
	    long insertId = sqlite.insert(MaBaseSQLite.GROUPE,null, Values);
	    Cursor cursor = sqlite.query(MaBaseSQLite.GROUPE,allColumns , MaBaseSQLite.ID_GROUPE + " = " + insertId, null,null, null, null);
	    cursor.moveToFirst();
	    cursor.close();
	    sqlite.close();
	    
	  }
	
	
	public int getAllSameName(String name) {
	    List<Groupe> groupeliste = new ArrayList<Groupe>();
		SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

		String[] allColumns = { MaBaseSQLite.ID_GROUPE,   MaBaseSQLite.NOM_GROUPE};

		
		
	    Cursor cursor = sqlite.query("groupe",allColumns, null, null, MaBaseSQLite.NOM_GROUPE+"="+"name", null, null);
	    

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Groupe groupe = cursorToGroupe(cursor);
	      groupeliste.add(groupe);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return groupeliste.size();
	  }
	
	/**********---------------------------------------*/
	
	public List<Groupe> getAllGroupe() {
	    List<Groupe> groupeliste = new ArrayList<Groupe>();
		SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

		String[] allColumns = { MaBaseSQLite.ID_GROUPE,   MaBaseSQLite.NOM_GROUPE};

		
		
	    Cursor cursor = sqlite.query("groupe",allColumns, null, null, MaBaseSQLite.NOM_GROUPE, null, null);
	    

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	      Groupe groupe = cursorToGroupe(cursor);
	      groupeliste.add(groupe);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return groupeliste;
	  }

	  private Groupe cursorToGroupe(Cursor cursor) {
	    Groupe groupe = new Groupe();
	   groupe.setId_Groupe(cursor.getString(0));
	    groupe.setNomGroupe(cursor.getString(1));

	    return groupe;
	  }
	  
	  
	  public List<String> getAllGroupeName() {
		   // List<Groupe> groupeliste = new ArrayList<Groupe>();
		    List<String> groupeliste = new ArrayList<String>();

			SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

			String[] allColumns = { MaBaseSQLite.ID_GROUPE,   MaBaseSQLite.NOM_GROUPE};

			
			
		    Cursor cursor = sqlite.query("groupe",allColumns, null, null, MaBaseSQLite.NOM_GROUPE, null, null);
		    

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Groupe groupe = cursorToGroupe(cursor);
		      groupeliste.add(groupe.getNomGroupe());
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return groupeliste;
		  }

	/**
	 * Clear the table
	 */
	public void clear() {

		try {

			SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

			sqlite.delete(MaBaseSQLite.GROUPE, "", null);
			
			sqlite.close();
			
		} catch (SQLException sqlerror) {

			Log.v("delete from table error", sqlerror.getMessage());

		}	
		
	}
	
	/***************************ajout personne dans groupe*********************************/
	

}
