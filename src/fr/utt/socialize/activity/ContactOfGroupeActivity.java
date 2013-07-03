package fr.utt.socialize.activity;

    import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
	import android.os.Bundle;
	import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.util.SparseBooleanArray;
	import android.view.View;
	import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
	import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
    import android.widget.TextView;
import android.widget.Toast;
import fr.utt.socialize.R;
import android.widget.ArrayAdapter;
import fr.utt.socialize.adapter.ListeMemberAdapter;
import fr.utt.socialize.controller.GroupePersonListcontroller;
import fr.utt.socialize.controller.MainMenuController;
import fr.utt.socialize.database.HistoriqueMesssageManager;
import fr.utt.socialize.database.PersonneGroupeManager;
import fr.utt.socialize.database.PersonneManager;
import fr.utt.socialize.modele.Personne;
import fr.utt.socialize.modele.Personne_groupe;

	public class ContactOfGroupeActivity extends FragmentActivity {
		
		private PersonneGroupeManager personneGroupeManager;
		  private  PersonneManager personneManager;
		  private  Personne_groupe contact;
		  private String groupeName;
		  private HistoriqueMesssageManager historique;

		  private ListView listView;  
		  private Button SmsButton;
		  private Button FacebookButton;
		  private Button MailButton;
		  private Button TwitterButton;


		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_contact_groupe);
			
			// Main menu
			MainMenuController mainMenu = (MainMenuController) getSupportFragmentManager().findFragmentById(R.id.main_menu_fragment);
			mainMenu.setActive(MainMenuController.GROUPS);
			
       /********************* title of this activity is the selected  item from group list **********************/		
			
			TextView ItemView=(TextView) findViewById(R.id.title_item);			
			Intent i = getIntent();    
			
			 // getting attached intent data
	         groupeName = i.getStringExtra("name");
	        // displaying selected product name
	                      
	        ItemView.setText(" list SMS contacts for the group "+ groupeName );
	        
	        
	       
	       
			/*******************add new member***************************************/
			
			final Button AddMember = (Button) findViewById(R.id.AddMember);
			
		
			AddMember.setOnClickListener(new OnClickListener() {
						
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ContactOfGroupeActivity.this, NewMemberGroupeActivity.class);
				
				intent.putExtra("name",groupeName);
				startActivity(intent);
				}
			});
			 
				 
		       
				 
			
			// Ask the corresponding list controller the data
			GroupePersonListcontroller groupePersonListcontroller = (GroupePersonListcontroller) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
	
			//groupePersonListcontroller.getMember(groupeName);
			
			
			/************************** group message ***************************/
			

			/* active button sms*/
					
				   SmsButton = (Button) this.findViewById(R.id.SmsSelect);
				   
				   personneGroupeManager =new PersonneGroupeManager(this.getApplicationContext());
				   
				   
				   final List<Personne_groupe> listcontact= personneGroupeManager.getAllMembreOf(groupeName);
				   
				   

				   SmsButton.setOnClickListener(new View.OnClickListener() {
			           @Override
			           public void onClick(View v) {
			        	   
			        	   /*Création de l'AlertDialog*/
			        	   
			        	   
					        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ContactOfGroupeActivity.this);

					 
					      //give a title to the alertDialog
			        	   alertDialog.setTitle("send message to " +  groupeName + " group");
					        
					       final EditText smsContent=new EditText(ContactOfGroupeActivity.this);
					                       alertDialog.setView(smsContent);
					                       
					                       alertDialog.setPositiveButton("send", new DialogInterface.OnClickListener()
					       		        {

					       		        	@Override
					    					public void onClick(DialogInterface arg0, int position) {
					    		        		

					    		        		
					       		        		try {
					       		        			SmsManager sendSMS= SmsManager.getDefault();
					       		        			
					       		        		

					       		        			
						       		        		for (int i=0; i<listcontact.size(); i++){
					       							sendSMS.sendTextMessage(listcontact.get(i).getmedia_contact(), null, smsContent.getText().toString(), null, null);					       							
						       		        		}
						       		        		

                                                    Toast.makeText(ContactOfGroupeActivity.this, "SMS Sent!",Toast.LENGTH_LONG).show();
					       							historique.insertMessage(groupeName,smsContent.getText().toString());
					       							

					       							
					       							/* add message in the database*/
						       		        		
						       		        		} 
					       		        		catch (Exception e) {
					       						Toast.makeText(ContactOfGroupeActivity.this,"SMS faild, please try again later!",Toast.LENGTH_LONG).show();
					       						e.printStackTrace();
					       					  }
					       		        		
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

			                                     }
				                                         });	   
		
				   
				   /* go to mail group activity*/
	
				   MailButton = (Button) findViewById(R.id.Mail);
					
					
				   MailButton.setOnClickListener(new OnClickListener() {
								
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(ContactOfGroupeActivity.this, MailOfGroupActivity.class);						

						intent.putExtra("name",groupeName);
							startActivity(intent);
						
						}
					});
					
         

				   /************************* display all member of the group*************************/

							personneGroupeManager =new PersonneGroupeManager(this.getApplicationContext());
							   personneManager=new PersonneManager(this.getApplicationContext());
							   
							   
							   final List<Personne_groupe> items= personneGroupeManager.getAllMembreOf(groupeName);
							  
								
								listView=(ListView)findViewById(R.id.listmember_groupe);
								 final ArrayAdapter<Personne_groupe> objAdapter = new ArrayAdapter<Personne_groupe>(this, android.R.layout.simple_list_item_multiple_choice, items);
								
								 listView.setAdapter(objAdapter); 		
								 listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
								 
								 
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
													 List<Personne_groupe> listeMember= personneGroupeManager.getAllMembreOf(groupeName);
                              
												for (int j=0; j<listeMember.size(); j++) {
											        					           
												
											            personneGroupeManager.deleteMember(listeMember.get(j).getId_personne_groupe());
											           // listtodelete.add(contact);
											         }
															
												Intent intent = new Intent(ContactOfGroupeActivity.this, ContactOfGroupeActivity.class);						

												intent.putExtra("name",groupeName);
													startActivity(intent);
																											
														Toast.makeText(getApplicationContext(), "all membre of"+ groupeName+ "are deleted", Toast.LENGTH_LONG).show();
																								
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
									
								 
	 /************************************************************** delete selected item**********************************************************/
								 
									final Button delete = (Button) this.findViewById(R.id.delete_selected);
								
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
										 //   for (int k=0;k<listtodelete.size(); k++){
										    	
                              
												for (int j=0; j<tableau.size(); j++) {
											         if (tableau.valueAt(j)) {
											            contact = (Personne_groupe) objAdapter.getItem(tableau.keyAt(j));							           
												
											            personneGroupeManager.deleteMember(contact.getId_personne_groupe());
											         }}
															
												Intent intent = new Intent(ContactOfGroupeActivity.this, ContactOfGroupeActivity.class);						

												intent.putExtra("name",groupeName);
													startActivity(intent);
																											
														Toast.makeText(getApplicationContext(), "delete", Toast.LENGTH_LONG).show();
																						
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
									
									}
								}
				
			
			
		
	
