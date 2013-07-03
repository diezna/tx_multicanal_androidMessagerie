	package fr.utt.socialize.activity;
	import android.content.Intent;
	import android.os.Bundle;
	import android.support.v4.app.FragmentActivity;
	import android.view.View;
	import android.view.View.OnClickListener;
	import android.widget.Button;
import android.widget.Toast;
	import fr.utt.socialize.R;
	import fr.utt.socialize.constants.NameBarConstants;
    import fr.utt.socialize.controller.MailcontactlistController;
import fr.utt.socialize.controller.MainMenuController;
import fr.utt.socialize.database.ContactMailManager;

	public class MailContactActivity extends FragmentActivity {

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_contact_email);
			
			
			
			
					
			// Ask ContactsListController to populate the contacts list
			MailcontactlistController mailcontactlistController = (MailcontactlistController) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
			
			 ContactMailManager contactMailManager= new ContactMailManager(this);
			 
			 if (contactMailManager.getAllMailContact().size()!=0){
	     		mailcontactlistController.getContactsMail();
			

			 }
			 
			 else {
				 
					Toast.makeText(this.getApplicationContext(), "there are any mail in your phone", Toast.LENGTH_SHORT).show();
			 }
			 
			 
			 /* get facebook activity*/
			 
			
			/*	final Button Facebook = (Button) findViewById(R.id.Facebook);
				Facebook.setOnClickListener(new OnClickListener() {
								
					@Override
					public void onClick(View v) {
					//	Intent intent = new Intent(MainActivity.this, FacebookActivity.class);
					//	startActivity(intent);
						}
					});

			*/
			 
			 
			 final Button SMS = (Button) findViewById(R.id.SMS);
				SMS.setOnClickListener(new OnClickListener() {
								
					@Override
					public void onClick(View v) {
						Intent intent = new Intent(MailContactActivity.this, MainActivity.class);
					startActivity(intent);
						}
					});
			
		
		
	}

	}


