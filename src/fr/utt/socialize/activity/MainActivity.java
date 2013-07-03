package fr.utt.socialize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import fr.utt.socialize.R;
import fr.utt.socialize.constants.NameBarConstants;
import fr.utt.socialize.controller.ContactsListController;
import fr.utt.socialize.controller.MailcontactlistController;
import fr.utt.socialize.controller.MainMenuController;
import fr.utt.socialize.database.PersonneManager;
import fr.utt.socialize.facebook.FriendsList;

public class MainActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Set active main menu tab
		MainMenuController mainMenu = (MainMenuController) getSupportFragmentManager().findFragmentById(R.id.main_menu_fragment);
		mainMenu.setActive(MainMenuController.CONTACTS);
		
		
				
		// Ask ContactsListController to populate the contacts list
		ContactsListController contactsListController = (ContactsListController) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
		
		 PersonneManager personneManager= new PersonneManager(this);
		 
		 if (personneManager.getAllPersonne().size()==0){
		contactsListController.getListContact();
		
		contactsListController.getListMailContact();

		 }
		 
		 else {
			 
			 contactsListController.getContacts();
		 }
		 
		 
		 /* get mail activity*/
		 
		
			final Button Mail = (Button) findViewById(R.id.Mail);
			Mail.setOnClickListener(new OnClickListener() {
							
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, MailContactActivity.class);
				startActivity(intent);
					}
				});

			 /* get facebook activity*/

			
			final Button Facebook = (Button) findViewById(R.id.Facebook);
			Facebook.setOnClickListener(new OnClickListener() {
							
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(MainActivity.this, PickerActivity.class);
				       startActivity(intent);
					}
				});

		
		
	
	
}

}