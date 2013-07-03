package fr.utt.socialize.activity;	

    import java.util.List;
    import android.app.ListActivity;
    import android.content.Intent;
	import android.os.Bundle;
import android.util.Log;
    import android.util.SparseBooleanArray;
    import android.view.View;
    import android.view.View.OnClickListener;
	import android.widget.ArrayAdapter;
    import android.widget.Button;
    import android.widget.ListView;
    import android.widget.TextView;
import android.widget.Toast;
	import fr.utt.socialize.R;
import fr.utt.socialize.database.PersonneGroupeManager;
    import fr.utt.socialize.database.PersonneManager;
import fr.utt.socialize.modele.Personne;

	public class NewMemberGroupeActivity extends ListActivity {

		   private TextView selection;
		   private PersonneManager personneManager;
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
			setContentView(R.layout.activity_new_member);
			
			
	        
	        /*  get all personne in the database*/
			
			
			 personneManager= new PersonneManager(getApplicationContext());
		 List<Personne> items= personneManager.getAllPersonne();
			   
			  // System.out.println(items);

			
			setListAdapter(new ArrayAdapter<Personne>(this, android.R.layout.simple_list_item_multiple_choice, items));

		        getListView().setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		        selection = (TextView) findViewById(R.id.selection);
		        
		        
		        
		        /* return to contactOfGroupActivy while valide is clicking*/
				
				final Button valide = (Button) findViewById(R.id.valide_new_member);
				
			
				valide.setOnClickListener(new OnClickListener() {
							
				@Override
				public void onClick(View v) {
					Intent intent = new Intent( NewMemberGroupeActivity.this, ContactOfGroupeActivity.class);
					
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
		            
		            PersonneGroupeManager  personneGroupeManager= new PersonneGroupeManager(getApplicationContext());
		            
		            int count= personneGroupeManager.countMember(getGroupeName(),contact.getmedia());
		            
		            
		            
		          if (count==0){

		       
				        //  System.out.println( personneGroupeManager.countMember(getGroupeName(), contact.getNom()));
				           personneGroupeManager.addMember(getGroupeName(), contact.getNom(), contact.getmedia());
				           
				           
				           
				           
		           } 


		        
		         }
		      }
		   }

}
