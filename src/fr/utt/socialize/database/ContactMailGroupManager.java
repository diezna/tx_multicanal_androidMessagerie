package fr.utt.socialize.database;

import java.util.ArrayList;
import java.util.List;

import fr.utt.socialize.modele.Personne;
import fr.utt.socialize.modele.Personne_groupe;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class ContactMailGroupManager {
	

	//private OpenDbHelper dbHelper;
	private MaBaseSQLite mabasesqlite;

	public ContactMailGroupManager (Context context) {
		mabasesqlite = new MaBaseSQLite(context);
	}

	/**
	 * Insert a Stock-value into database
	 * @param stockId id of the stock
	 * @param stockDesc description of the stock
	 * @return success or fail
	 */
	
	public void addMember(String nameGroupe, String namePersonne, String mail) {
	    
		ContentValues Values = new ContentValues();
		
		//Personne personne=new Personne(nom,prenom);
		SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();
		
		 

		

		String[] allColumns = { MaBaseSQLite.IDMAILGROUP,   MaBaseSQLite.NOMGROUPMAIL, MaBaseSQLite.NAMECONTACTMAIL, MaBaseSQLite.MAILCONTACT};

		Values.put( MaBaseSQLite.NOMGROUPMAIL,nameGroupe);
		Values.put( MaBaseSQLite.NAMECONTACTMAIL,namePersonne);
		Values.put( MaBaseSQLite.MAILCONTACT,mail);

         
		
	    long insertId = sqlite.insert(MaBaseSQLite.GROUPEMAILCONTACT, null, Values);
	    Cursor cursor = sqlite.query(MaBaseSQLite.GROUPEMAILCONTACT,allColumns , MaBaseSQLite.IDMAILGROUP + " = " + insertId, null,null, null, null);
	    cursor.moveToFirst();
	    cursor.close();
	    sqlite.close();
	  }
	
/* all contact membre for the selected groupe*/
	
	
	
	public List<Personne_groupe> getAllMembreOf(String nomgroupe) {
	    List<Personne_groupe> listMembres = new ArrayList<Personne_groupe>();
		SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();
		
		String[] args={nomgroupe};
		
        Cursor cursor = sqlite.rawQuery("SELECT * FROM groupmailcontact WHERE nomgroupmail=?", args);      

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) {
	    	
	      Personne_groupe personne_groupe=cursorToMembre(cursor);
	      listMembres.add(personne_groupe);
	      cursor.moveToNext();
	    }
	    // Make sure to close the cursor
	    cursor.close();
	    return listMembres;
	  }

	  public Personne_groupe cursorToMembre(Cursor cursor) {
		  Personne_groupe personne_groupe = new Personne_groupe();
		    personne_groupe.setId_personne_groupe(cursor.getString(0));
		    personne_groupe.setName_groupe(cursor.getString(1));
		    personne_groupe.setName_personne(cursor.getString(2));
		    personne_groupe.setmedia_contact(cursor.getString(3));
	    
	    return personne_groupe;
	  }

 /* fin a personne with namecontact and groupe name*/
	  
	   public int countMember(String nameGroupe, String mail) {
		  List< Personne_groupe> listMembres = new ArrayList< Personne_groupe>();
			SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();
			

			
			String[] args={nameGroupe,mail};					
            Cursor cursor = sqlite.rawQuery("SELECT * FROM groupmailcontact WHERE nomgroupmail=? and mailcontact=?", args);
		
		       
			cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		    	
		      Personne_groupe personne_groupe=cursorToMembre(cursor);
		      
		      listMembres.add(personne_groupe);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return listMembres.size();
		  }
	   
   public void deleteMember(String idcontact){
		   
		   try {

				SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();
				
				sqlite.delete(MaBaseSQLite.GROUPEMAILCONTACT,  MaBaseSQLite.IDMAILGROUP +"="+ idcontact, null);
				
				sqlite.close();
				
			} catch (SQLException sqlerror) {

				Log.v("delete member from table error", sqlerror.getMessage());

			}	
		   
	   }
	  
	  
	public void clear() {

		try {

			SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

			
			sqlite.close();
			
		} catch (SQLException sqlerror) {

			Log.v("delete from table error", sqlerror.getMessage());

		}	
		
	}

}
