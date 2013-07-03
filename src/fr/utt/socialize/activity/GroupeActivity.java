package fr.utt.socialize.activity;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import fr.utt.socialize.R;
import fr.utt.socialize.constants.NameBarConstants;
import fr.utt.socialize.controller.GroupsListController;
import fr.utt.socialize.controller.MainMenuController;
import fr.utt.socialize.database.GroupeManager;
import fr.utt.socialize.modele.Personne_groupe;

public class GroupeActivity extends FragmentActivity {
	
	private GroupeManager groupeManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_groupe);
		
		
		
		/* add new groupe*/
		
		final Button new_Groupe = (Button) findViewById(R.id.newGroupe);
		new_Groupe.setOnClickListener(new OnClickListener() {
					
		@Override
		public void onClick(View v) {
			
	        AlertDialog.Builder  alertDialog = new AlertDialog.Builder(GroupeActivity.this);
	        
	        alertDialog.setTitle("add the new group");
	        
		       final EditText editGroup=new EditText(GroupeActivity.this);
		                       alertDialog.setView(editGroup);
		                       
		                  // final String Groupename=   editGroup.getText().toString();

		                       alertDialog.setPositiveButton("add", new DialogInterface.OnClickListener()
			       		        {

			       		        	@Override
			    					public void onClick(DialogInterface arg0, int position) {
			       		        		
	    		        		

			       		        		 groupeManager= new GroupeManager(getApplicationContext());
			       		            	
			       		            	
			       		            	groupeManager.createGroupe(editGroup.getText().toString());
			       		            	
			       		            	Intent intent = new Intent(GroupeActivity.this, GroupeActivity.class);
			       						
			       						startActivity(intent);
			       		        		
			       		        		}
			       		        });
		                       
		                       alertDialog.setNegativeButton("cancel", new DialogInterface.OnClickListener(){

			       					@Override
			       					public void onClick(DialogInterface dialog, int arg1) {
			       						// TODO Auto-generated method stub
			       						dialog.cancel();
			       					}
		                       });
		                       
		                       alertDialog.show();
		                         
			    						
		                               } });
			                       
		
		/*************   delete groupe**********/
		
		
		
		
		
		
			
		/************Set the screen title***********************/
		((TextView) this.findViewById(R.id.name_bar)).setText(NameBarConstants.GROUPS);
					
		// Ask the corresponding list controller the data
		GroupsListController groupsListController = (GroupsListController) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
		groupsListController.getGroups();
		
		
		
	}
}
