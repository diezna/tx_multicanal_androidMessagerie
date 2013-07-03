package fr.utt.socialize.activity;

    import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import fr.utt.socialize.R;
import fr.utt.socialize.controller.GroupeMailListController;
import fr.utt.socialize.controller.MainMenuController;
import fr.utt.socialize.database.ContactMailGroupManager;
import fr.utt.socialize.database.ContactMailManager;
import fr.utt.socialize.database.HistoriqueMesssageManager;
import fr.utt.socialize.database.PersonneGroupeManager;
import fr.utt.socialize.database.PersonneManager;
import fr.utt.socialize.modele.Personne_groupe;

	public class MailOfGroupActivity extends FragmentActivity {
		
		private ContactMailGroupManager contactMailGroupManager;
		private ContactMailManager contactMailManager;
		private PersonneGroupeManager personneGroupeManager;
        private HistoriqueMesssageManager historique;


		  private ListView listView;  
		  
		  
		  private Button SmsButton;
		  private Button FacebookButton;
		  private Button MailButton;
		  private Button TwitterButton;


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_contact_email_group);
			
			
			
       /* ******************** title of this activity is the selected  item from group list **********************/		
			
			TextView ItemView=(TextView) findViewById(R.id.title_item);			
			Intent i = getIntent();    
			
			 // getting attached intent data
	        final String groupeName = i.getStringExtra("name");
	        // displaying selected product name*
	        
	        System.out.println(groupeName);
	                      
	        ItemView.setText(" list mail contacts for the group "+ groupeName);
	        
	       
			/* ******************add new groupe***************************************/
			
			final Button AddMember = (Button) findViewById(R.id.AddMember);
			
		
			AddMember.setOnClickListener(new OnClickListener() {
						
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MailOfGroupActivity.this, NewMemberGroupeMailActivity.class);
				
				intent.putExtra("name",groupeName);
				startActivity(intent);
				}
			});
			
			
			// Ask the corresponding list controller the data
			GroupeMailListController groupeMailListController = (GroupeMailListController) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
	
			//groupeMailListController.getMember(groupeName);
			
			
			/************************** group message ***************************/
			

			/* active button MailButton*/
					
		          	MailButton = (Button) this.findViewById(R.id.Mail);
				   
		          	contactMailGroupManager =new ContactMailGroupManager(this.getApplicationContext());
		          	
		          	final List<Personne_groupe> listcontact= contactMailGroupManager.getAllMembreOf(groupeName)	;
		          	
				   MailButton.setOnClickListener(new View.OnClickListener() {
			           @Override
			           public void onClick(View v) {
			        	   
			        	   /*Création de l'AlertDialog*/
			        	   
			        	   
					        AlertDialog.Builder adb = new AlertDialog.Builder(MailOfGroupActivity.this);

					        LayoutInflater factory = LayoutInflater.from(MailOfGroupActivity.this);
					        final View alertDialogLayout  = factory.inflate(R.layout.dialogu_mail, null);
					        

					      //give a title to the alertDialog
					        adb.setTitle("send mail to " +  groupeName+ " group");
			        	   
			        	   final EditText Subject = (EditText) alertDialogLayout.findViewById(R.id.Subject);
					        final EditText Message = (EditText) alertDialogLayout.findViewById(R.id.Message);
					        
					                      adb.setView(alertDialogLayout);
					                       
					                      adb.setPositiveButton("send", new DialogInterface.OnClickListener()
					       		        {

					       		        	@Override
					    					public void onClick(DialogInterface arg0, int position) {
					       		        		try {
					       		        			
						       		        		for (int i=0; i<listcontact.size(); i++){
						      							  Intent email = new Intent(android.content.Intent.ACTION_SEND);
						      							  email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {listcontact.get(i).getmedia_contact()});
						      							  email.putExtra(android.content.Intent.EXTRA_SUBJECT, Subject.getText().toString());
						      							  email.putExtra(android.content.Intent.EXTRA_TEXT, Message.getText().toString());

						      								 //email.setType("message/rfc822") ; // use from live device
						      								 
						      								 email.setType ("plain/text"); 
						      					                startActivity(email);
						      						Toast.makeText(MailOfGroupActivity.this, "mail Sent!",Toast.LENGTH_LONG).show();
						      						
					       							//historique.insertMessage(groupeName, Message.getText().toString());
						      				  }
					       		        		}
						       		        		catch (Exception e) {
						      					Toast.makeText(MailOfGroupActivity.this,"mail faild, please try again later!",Toast.LENGTH_LONG).show();
						      					e.printStackTrace();
						      				                            }
					       		        		}
					       		        });
					       		        
			        	   
					                      adb.setNegativeButton("cancel", new DialogInterface.OnClickListener(){

												@Override
												public void onClick(DialogInterface dialog, int arg1) {
													// TODO Auto-generated method stub
													dialog.cancel();
												}
									        });					       
					       
					                adb.show();

			                           }
				                         });	   
		
	
				   
				   /*****************go to sms activity**************************************/
	
				   SmsButton = (Button) findViewById(R.id.SmsSelect);
					
					
				   SmsButton.setOnClickListener(new OnClickListener() {
								
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MailOfGroupActivity.this, ContactOfGroupeActivity.class);
						
						intent.putExtra("name",groupeName);
						
						startActivity(intent);
						}
					});
				   
				   

				   /************************* display all member of the group*************************/

			   contactMailGroupManager =new ContactMailGroupManager(this.getApplicationContext());							   
							   
			      final List<Personne_groupe> items= contactMailGroupManager.getAllMembreOf(groupeName);
			      
								
				 listView=(ListView)findViewById(R.id.listmail_groupe);
				 final ArrayAdapter<Personne_groupe> objAdapter = new ArrayAdapter<Personne_groupe>(this, android.R.layout.simple_list_item_multiple_choice, items);
								
				 listView.setAdapter(objAdapter); 		
				 listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
				 
				 

					   
				 /************************************************************** delete selected item**********************************************************/
				 
					final Button delete = (Button) this.findViewById(R.id.delete_selected);
					final List<Personne_groupe> listtodelete=new ArrayList<Personne_groupe>();
					
			/*		Personne_groupe contact;
				      SparseBooleanArray tableau = listView.getCheckedItemPositions();*/
				      
				      
						
					    final AlertDialog.Builder adb = new AlertDialog.Builder(this);

					
					    delete.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View v) {
					       adb.setTitle("Are you sure you want to delete ?");	

						      
						    adb.setPositiveButton("yes", new DialogInterface.OnClickListener(){

									@Override
						public void onClick(DialogInterface arg0, int position) {														

										Personne_groupe contact;
									      SparseBooleanArray tableau = listView.getCheckedItemPositions();				    	
           
								         for (int j=0; j<tableau.size(); j++) {
							               if (tableau.valueAt(j)) {
							                  contact = (Personne_groupe) objAdapter.getItem(tableau.keyAt(j));							           
								
							                  contactMailGroupManager.deleteMember(contact.getId_personne_groupe());
							                //personneGroupeManager.deleteMember("1");

							         }}
											
								          Intent intent = new Intent(MailOfGroupActivity.this, MailOfGroupActivity.class);						

								          intent.putExtra("name",groupeName);
									      startActivity(intent);
																					
									}

									
						        });
						        
						        adb.setNegativeButton("no", new DialogInterface.OnClickListener(){

									@Override
									public void onClick(DialogInterface dialog, int arg1) {
										// TODO Auto-generated method stub
										dialog.cancel();
									}
						        });
						        
						        adb.show();	 
				       	 
				         }
						});
					
					  
					    /****************************************delete all**********************************/
						 
						final Button delete_all = (Button) this.findViewById(R.id.cheked_all);
						
             final AlertDialog.Builder adbdialogue = new AlertDialog.Builder(this);

						
                            delete_all.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
							adbdialogue.setTitle("Are you sure you want to delete all member of the groupe ?");	

							      
								adbdialogue.setPositiveButton("yes", new DialogInterface.OnClickListener(){

										@Override
							public void onClick(DialogInterface arg0, int position) {														

											Personne_groupe contact;
										 List<Personne_groupe> listeMember= contactMailGroupManager.getAllMembreOf(groupeName);
                  
									for (int j=0; j<listeMember.size(); j++) {
								        					           
									
										contactMailGroupManager.deleteMember(listeMember.get(j).getId_personne_groupe());
								           // listtodelete.add(contact);
								         }
												
									Intent intent = new Intent(MailOfGroupActivity.this, MailOfGroupActivity.class);						

									intent.putExtra("name",groupeName);
										startActivity(intent);
																								
											Toast.makeText(getApplicationContext(), "all members of "+ groupeName+ " are deleted", Toast.LENGTH_LONG).show();
																					
										}

							        });
							        
								adbdialogue.setNegativeButton("no", new DialogInterface.OnClickListener(){

										@Override
										public void onClick(DialogInterface dialog, int arg1) {
											// TODO Auto-generated method stub
											dialog.cancel();
										}
							        });
							        
								adbdialogue.show();	 
					       	 
					         }
							});
						
						
					

		}
	
}
	
