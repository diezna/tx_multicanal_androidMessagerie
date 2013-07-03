package fr.utt.socialize.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import fr.utt.socialize.R;
import fr.utt.socialize.database.GroupeManager;

public class NewGroupeActivity extends FragmentActivity {


	
	Button btnaddContact;
	 
    // Input text
    EditText Editname;
    String Groupename;

   
    GroupeManager groupeManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_groupe);
		
		
//		// add button
        btnaddContact = (Button) findViewById(R.id.btnAddGroupe);
 
        // new label input field
        Editname = (EditText) findViewById(R.id.nameGroupe);

                
        
        
		
        btnaddContact.setOnClickListener(new View.OnClickListener() {
        	 
            @Override
            public void onClick(View arg0) {
            	
            	Groupename = Editname.getText().toString();
               
            	groupeManager= new GroupeManager(getApplicationContext());
            	
            	
            	groupeManager.createGroupe(Groupename);
            	
       		   		    
					Intent intent = new Intent(NewGroupeActivity.this, GroupeActivity.class);
					startActivity(intent);
					
            	
					}
				});


 
              
        }
}
