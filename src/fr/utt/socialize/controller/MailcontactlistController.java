package fr.utt.socialize.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import fr.utt.socialize.R;
import fr.utt.socialize.adapter.PhoneContactAdapter;
import fr.utt.socialize.database.ContactMailManager;
import fr.utt.socialize.database.HistoriqueMesssageManager;
import fr.utt.socialize.database.PersonneManager;
import fr.utt.socialize.modele.Personne;
import fr.utt.socialize.phoneContact.ContactBean;
import fr.utt.socialize.phoneContact.ContanctAdapter;

public class MailcontactlistController extends Fragment implements OnItemClickListener {
	
	private ContactMailManager  contactMailManager;
	private  HistoriqueMesssageManager 	historique;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.list, container, false);		
		return view;
	}
	
	
	
	/* get contact mail from database*/
	
	public void getContactsMail()
	{
		contactMailManager= new ContactMailManager(this.getActivity().getApplicationContext());
	 
	   final List<Personne> items= contactMailManager.getAllMailContact();
	   
	   
      
    	
		if(this.getActivity() == null)
		{
		return;
     	}
		
		

		
		PhoneContactAdapter objAdapter = new PhoneContactAdapter(getActivity(), R.layout.alluser_row, items);
        ((ListView) this.getView().findViewById(R.id.list)).setAdapter(objAdapter);
        

        
        final ListView listView = (ListView)getView().findViewById(R.id.list);
        listView.setOnItemClickListener(this);
        
	       /* dialogue send sms*/
        
        /*  get all message histroique*/
		 historique=new HistoriqueMesssageManager(this.getActivity().getApplicationContext());

    	
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				
				// TODO Auto-generated method stub
				
				final Personne contact=  (Personne) listView.getItemAtPosition(position);
		        LayoutInflater factory = LayoutInflater.from(getActivity());
		        final View alertDialogView = factory.inflate(R.layout.dialogu_mail, null);
				//Création de l'AlertDialog for sending mail
		        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
		 
		      //give a title to the alertDialog
		        adb.setTitle("send mail to " +  contact.getNom());
		    
		       
		        final EditText Subject = (EditText) alertDialogView.findViewById(R.id.Subject);
		        final EditText Message = (EditText) alertDialogView.findViewById(R.id.Message);
		        final String to= contact.getmedia();
                adb.setView(alertDialogView);
		        adb.setPositiveButton("send", new DialogInterface.OnClickListener()
		        {

					@Override
					public void onClick(DialogInterface arg0, int position) {
						// TODO Auto-generated method stub
						/* send sms*/
						

						try {
							
							  Intent email = new Intent(android.content.Intent.ACTION_SEND);
							  							  
							  email.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] {to});							  
							  
							  email.putExtra(android.content.Intent.EXTRA_SUBJECT, Subject.getText().toString());
							  email.putExtra(android.content.Intent.EXTRA_TEXT, Message.getText().toString());

								 //email.setType("message/rfc822") ; // use from live device
								 
								 email.setType ("plain/text"); 
             
									historique.insertMessage(contact.getNom(), Message.getText().toString());
							  
					                startActivity(email);

						Toast.makeText(getActivity(), "mail Sent!",Toast.LENGTH_LONG).show();
						
						/* add message in the database*/
						
				  } catch (Exception e) {
					Toast.makeText(getActivity(),"mail faild, please try again later!",Toast.LENGTH_LONG).show();
					e.printStackTrace();
				  }
					
					}

					
		        });		        
		        
		                    adb.setNegativeButton("cancel", new DialogInterface.OnClickListener(){

					           @Override
					       public void onClick(DialogInterface arg0, int arg1) {
					   	// TODO Auto-generated method stub
						
					}	                	
		        	
		        });		        
		                    adb.show();
		      }			
        });
        
		
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}

	

}
