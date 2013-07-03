package fr.utt.socialize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import fr.utt.socialize.R;
import fr.utt.socialize.database.PersonneManager;


public class newcontactsActivity extends FragmentActivity {
	
	Button btnaddContact;
	 
    // Input text
    EditText Editname;
    //EditText EditSurname;
    String contactname;
   // String contactSurame;
    PersonneManager personneManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_contact);
		
		
		// add button
        btnaddContact = (Button) findViewById(R.id.btnAddContact);
 
        // new label input field
        Editname = (EditText) findViewById(R.id.nameContact);
       // EditSurname = (EditText) findViewById(R.id.surnameConact);

                
        
        
		
        btnaddContact.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View arg0) {
            	
            	contactname = Editname.getText().toString();
              //  contactSurame = EditSurname.getText().toString();
               
                personneManager= new PersonneManager(getApplicationContext());
       		 
       		  //  personneManager.createPersonne(contactname, contactSurame);
       		    
					Intent intent = new Intent(newcontactsActivity.this, MainActivity.class);
					startActivity(intent);
					}
				});


 
              
        }
		
		
	
	
}