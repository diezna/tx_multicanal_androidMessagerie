package fr.utt.socialize.activity;	

    import java.util.List;
    import android.app.ListActivity;
    import android.content.Intent;
	import android.os.Bundle;
    import android.util.SparseBooleanArray;
    import android.view.View;
    import android.view.View.OnClickListener;
	import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.ListView;
    import android.widget.TextView;
	import fr.utt.socialize.R;
    import fr.utt.socialize.database.ContactMailGroupManager;
    import fr.utt.socialize.database.ContactMailManager;
    import fr.utt.socialize.modele.Personne;

	public class NewMemberGroupeMailActivity extends ListActivity {

		   private TextView selection;
		   private ContactMailManager contactMailManager;
		   private String groupeName;
       //   private  Personne_groupeManager personne_groupeManager;

        public String getGroupeName(){

			/* get name of the selected group*/

			Intent i = getIntent();    
			
			 // getting attached intent data
	        groupeName = i.getStringExtra("name");
	        
	        return groupeName;
        }
  

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_new_mail);
	        /*  get all personne in the database*/			
			
			 contactMailManager= new ContactMailManager(getApplicationContext());
		 List<Personne> items= contactMailManager.getAllMailContact();
			   	
			setListAdapter(new ArrayAdapter<Personne>(this, android.R.layout.simple_list_item_multiple_choice, items));

		        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		        selection = (TextView) findViewById(R.id.selection);
		        
		        /* return to contactOfGroupActivy while valide is clicking*/
				
				final Button valide = (Button) findViewById(R.id.valide_new_member);
				
			
				valide.setOnClickListener(new OnClickListener() {
							
				@Override
				public void onClick(View v) {
					Intent intent = new Intent( NewMemberGroupeMailActivity.this, MailOfGroupActivity.class);
					
					intent.putExtra("name",getGroupeName());
					startActivity(intent);
					}
				     });
		                }

		/* add member in  personne_groupe table on click*/
		  @Override
		   protected void onListItemClick(ListView liste, View v, int position, long id) {
		      Personne contact;
		      SparseBooleanArray tableau = liste.getCheckedItemPositions();
		      for (int i=0; i<tableau.size(); i++) {
		         if (tableau.valueAt(i)) {
		            contact = (Personne) getListAdapter().getItem(tableau.keyAt(i));
		            
		        		            /* add checked item into database*/
		            
		             ContactMailGroupManager contactMailgroupManager= new  ContactMailGroupManager(getApplicationContext());
		            
		            int count= contactMailgroupManager.countMember(getGroupeName(),contact.getmedia());
		            
		          if (count==0){
		       
		        	  contactMailgroupManager.addMember(getGroupeName(), contact.getNom(), contact.getmedia());
		                      } 
		                        }
		                         }
		                            }
		  
		  
		  
                                     }
