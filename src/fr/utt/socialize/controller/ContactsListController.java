package fr.utt.socialize.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import fr.utt.socialize.R;
import fr.utt.socialize.adapter.PhoneContactAdapter;
import fr.utt.socialize.database.ContactMailManager;
import fr.utt.socialize.database.HistoriqueMesssageManager;
import fr.utt.socialize.database.PersonneManager;
import fr.utt.socialize.modele.Groupe;
import fr.utt.socialize.modele.Personne;
import fr.utt.socialize.phoneContact.ContactBean;
import fr.utt.socialize.phoneContact.ContanctAdapter;

public class ContactsListController extends Fragment implements OnItemClickListener
{
	
	private PersonneManager personneManager;
	private ContactMailManager  contactMailManager;
	private HistoriqueMesssageManager historique;
	
	private boolean message_send=false;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.list, container, false);		
		return view;
	}
	

	/** get contact from database*/
	
	public void getContacts()
	{
	 personneManager= new PersonneManager(this.getActivity().getApplicationContext());
	 
	   final List<Personne> items= personneManager.getAllPersonne();
	   
	   
      
    	
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
				
				
				//Création de l'AlertDialog
		        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
		 
		      //give a title to the alertDialog
		        adb.setTitle("send message to " +  contact.getNom());
		        
		        final EditText smsContent=new EditText(getActivity());

		        adb.setView(smsContent);
		        adb.setPositiveButton("send", new DialogInterface.OnClickListener()
		        {

					@Override
					public void onClick(DialogInterface arg0, int position) {
						// TODO Auto-generated method stub
						/* send sms*/
						
						try {
						SmsManager sendSMS= SmsManager.getDefault();
												
						sendSMS.sendTextMessage(contact.getmedia(), null, smsContent.getText().toString(), null, null);

						Toast.makeText(getActivity(), "SMS Sent!",
								Toast.LENGTH_LONG).show();
						
						/* add message in the database*/
						historique.insertMessage(contact.getNom(), smsContent.getText().toString());
						
				  } catch (Exception e) {
					Toast.makeText(getActivity(),
						"SMS faild, please try again later!",
						Toast.LENGTH_LONG).show();
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
        
	}
				

		/** get all contacts in the phone*/
		
	public void getListContact(){
		 List<ContactBean> list = new ArrayList<ContactBean>();

		
		Cursor phones = getActivity().getContentResolver().query(
				ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null,
				null, null);
		while (phones.moveToNext()) {

			String name = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String phoneNumber = phones
					.getString(phones
							.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

			ContactBean objContact = new ContactBean();
			objContact.setName(name);
			objContact.setPhoneNo(phoneNumber);
			list.add(objContact);
			
			//personneManager.createPersonne(name,phoneNumber);
			
			this.personneManager = new PersonneManager(getActivity().getApplicationContext());
			personneManager.createPersonne(name,phoneNumber);			
			
		}
		phones.close();

		ContanctAdapter objAdapter = new ContanctAdapter(getActivity(), R.layout.alluser_row, list);
        ((ListView) this.getView().findViewById(R.id.list)).setAdapter(objAdapter);
	}
	
	
	/* get all mail contact*/
	
	public void getListMailContact(){
		 List<Personne> list = new ArrayList<Personne>();

		
		Cursor emails = getActivity().getContentResolver().query(
				ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, null,
				null, null);
		while (emails.moveToNext()) {

			String name = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));

			String mail = emails.getString(emails.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));

			Personne objContact = new Personne();
			objContact.setNom(name);
			objContact.setmedia(mail);
			list.add(objContact);
			
			//personneManager.createPersonne(name,phoneNumber);
			
			this.contactMailManager = new ContactMailManager(getActivity().getApplicationContext());
			contactMailManager.createMailContact(name,mail);
			
		//	System.out.println("zzzzzzzzzzzzzzzz "+name);
			
			
			
		}
		emails.close();

	//	PhoneContactAdapter objAdapter = new PhoneContactAdapter(getActivity(), R.layout.alluser_row, list);
     // ((ListView) this.getView().findViewById(R.id.list)).setAdapter(objAdapter);
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		//Do smth		
		Toast.makeText(getActivity().getApplicationContext(), "Nice click!", Toast.LENGTH_SHORT).show();
	}

	
	

}