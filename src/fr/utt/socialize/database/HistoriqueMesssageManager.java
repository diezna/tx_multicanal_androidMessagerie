package fr.utt.socialize.database;

import java.util.ArrayList;
import java.util.List;

import fr.utt.socialize.modele.Message;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class HistoriqueMesssageManager {

		//private OpenDbHelper dbHelper;
		private MaBaseSQLite mabasesqlite;

		public HistoriqueMesssageManager (Context context) {
			mabasesqlite = new MaBaseSQLite(context);
		}

		
		public void insertMessage(String destinataire, String contenu) {
		    
			ContentValues Values = new ContentValues();
			
			SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

			//String[] allColumns = { MaBaseSQLite.ID_MESSAGE,   MaBaseSQLite. DATE , MaBaseSQLite.CONTENU, MaBaseSQLite.ID_CONTACT};
			String[] allColumns = { MaBaseSQLite.ID_MESSAGE, MaBaseSQLite.CONTENU, MaBaseSQLite.DESTINATAIRE};


			//Values.put( MaBaseSQLite. DATE,date);
			Values.put( MaBaseSQLite.CONTENU,contenu);
			Values.put(MaBaseSQLite.DESTINATAIRE,destinataire);
		    long insertId = sqlite.insert(MaBaseSQLite.HISTORIQUE_MESSAGE,null, Values);
		    Cursor cursor = sqlite.query(MaBaseSQLite.HISTORIQUE_MESSAGE,allColumns , MaBaseSQLite.ID_MESSAGE + " = " + insertId, null,null, null, null);
		    cursor.moveToFirst();
		    cursor.close();
		    sqlite.close();
		  }
		
		/**********---------------------------------------*/
		
		  public Message cursorToHistorique(Cursor cursor) {
		    Message historique = new Message();
		    historique.setId_message(cursor.getInt(0));
		  //  historique.setDate(cursor.getString(1));
		    historique.setContenu_message(cursor.getString(1));
		    historique.setdestinataire(cursor.getString(2));
		    return historique;
		  }
		  
		  
		  
		  /* get all message of id_contact*/
		  
		 	public List<Message> getAllMessage(int id_contact) {
		    List<Message> historique = new ArrayList<Message>();
			SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

			String[] allColumns = { MaBaseSQLite.ID_MESSAGE, MaBaseSQLite.CONTENU, MaBaseSQLite.DESTINATAIRE};
			
		    Cursor cursor = sqlite.query("historique_message",allColumns, MaBaseSQLite.DESTINATAIRE + " = " + id_contact, null, null, null, null);
		    

		    cursor.moveToFirst();
		    while (!cursor.isAfterLast()) {
		      Message message = cursorToHistorique(cursor);
		      historique.add(message);
		      cursor.moveToNext();
		    }
		    // Make sure to close the cursor
		    cursor.close();
		    return historique;
		  }
		 	
			public List<Message> getAllMessage() {
			    List<Message> historique = new ArrayList<Message>();
				SQLiteDatabase sqlite = mabasesqlite.getReadableDatabase();

				String[] allColumns = { MaBaseSQLite.ID_MESSAGE,   MaBaseSQLite.CONTENU, MaBaseSQLite.DESTINATAIRE};
				
			    Cursor cursor = sqlite.query("historique_message",allColumns, null, null, null,null, null);
			    

			    cursor.moveToFirst();
			    while (!cursor.isAfterLast()) {
			      Message message = cursorToHistorique(cursor);
			      historique.add(message);
			      cursor.moveToNext();
			    }
			    // Make sure to close the cursor
			    cursor.close();
			    return historique;
			  }
		 	
		
			
		/**
		 * Clear the table
		 */
		public void clear() {

			try {

				SQLiteDatabase sqlite = mabasesqlite.getWritableDatabase();

				sqlite.delete(MaBaseSQLite.HISTORIQUE_MESSAGE, "", null);
				
				sqlite.close();
				
			} catch (SQLException sqlerror) {

				Log.v("delete from table error", sqlerror.getMessage());

			}	
			
		}
		

	}




