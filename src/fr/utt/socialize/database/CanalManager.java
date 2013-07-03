package fr.utt.socialize.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import fr.utt.socialize.modele.Canal;

public class CanalManager {
	
	
		 
		private MaBaseSQLite maBaseSQLite;
	 
		public CanalManager (Context context){
			//On créer la BDD et sa table
			maBaseSQLite = new MaBaseSQLite(context);
		}
	 


		
		
		/* ---------------- modification de la table canal------------*/
		
		public  void createCanal(String nomCanal){
			
			
			ContentValues Values = new ContentValues();
			
			SQLiteDatabase sqlite = maBaseSQLite.getWritableDatabase();

			String[] allColumns = { MaBaseSQLite.ID_CANAL,   MaBaseSQLite.NOM_CANAL};

			Values.put( MaBaseSQLite.NOM_CANAL,nomCanal);
			
	
		    long insertId = sqlite.insert(MaBaseSQLite.CANAL,null, Values);
		    Cursor cursor = sqlite.query(MaBaseSQLite.CANAL,allColumns , MaBaseSQLite.ID_CANAL + " = " + insertId, null,null, null, null);
		    cursor.moveToFirst();
		    cursor.close();
		    sqlite.close();
		}
	 
		
	 
	/*  recuperer tous les noms de canaux de la bases*/
		public List<Canal> getAllCANAL() {
		    List<Canal> listCanal = new ArrayList<Canal>();
			SQLiteDatabase sqlite = maBaseSQLite.getReadableDatabase();

			String[] allColumns = { MaBaseSQLite.ID_CANAL,   MaBaseSQLite.NOM_CANAL};

			
			
		    Cursor cursor = sqlite.query("canal",allColumns, null, null, null, null, null);
		    

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Canal canal = cursorToCanal(cursor);
		      listCanal.add(canal);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return listCanal;
		  }

		  private Canal cursorToCanal(Cursor cursor) {
		   Canal canal = new Canal();
		    canal.setId_canal(cursor.getInt(0));
		    canal.setNom(cursor.getString(1));

		    return canal;
		  }

		/**
		 * Clear the table
		 */
		public void clear() {

			try {

				SQLiteDatabase sqlite = maBaseSQLite.getWritableDatabase();

				sqlite.delete(MaBaseSQLite.CANAL, "", null);
				
				sqlite.close();
				
			} catch (SQLException sqlerror) {

				Log.v("delete from table error", sqlerror.getMessage());

			}	
			
		}
		
	}





