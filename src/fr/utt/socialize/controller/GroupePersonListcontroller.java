package fr.utt.socialize.controller;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import fr.utt.socialize.R;
import fr.utt.socialize.activity.ContactOfGroupeActivity;
import fr.utt.socialize.activity.NewMemberGroupeActivity;
import fr.utt.socialize.adapter.ListeMemberAdapter;
import fr.utt.socialize.adapter.PhoneContactAdapter;
import fr.utt.socialize.database.GroupeManager;
import fr.utt.socialize.database.PersonneGroupeManager;
import fr.utt.socialize.database.PersonneManager;
import fr.utt.socialize.modele.Groupe;
import fr.utt.socialize.modele.Personne;
import fr.utt.socialize.modele.Personne_groupe;

public class GroupePersonListcontroller extends Fragment implements OnItemClickListener{
	private PersonneGroupeManager personneGroupeManager;
	  private  PersonneManager personneManager;
	  private ListView listView;
	  private Button SmsButton;
	  private Button FacebookButton;
	  private Button MailButton;
	  private Button TwitterButton;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.listmember_groupe, container, false);
		return view;
		
		
	}
	

   public void getMember(final String nomgroupe){
	   
	   personneGroupeManager =new PersonneGroupeManager(getActivity().getApplicationContext());
	   personneManager=new PersonneManager(getActivity().getApplicationContext());
	   
	   
	   final List<Personne_groupe> items= personneGroupeManager.getAllMembreOf(nomgroupe);
	  
		if(this.getActivity() == null)
		{
		return;
    	}
		
		
		listView=(ListView)this.getView().findViewById(R.id.listmember_groupe);
		ArrayAdapter<Personne_groupe> objAdapter = new ArrayAdapter<Personne_groupe>(this.getActivity(), android.R.layout.simple_list_item_multiple_choice, items);
		
		 listView.setAdapter(objAdapter); 		
		 listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
       
      
   
   
 /* delete member from group*/
		 
	//	final Button delete = (Button) this.getView().findViewById(R.id.delete_selected);
		 
		 /* add member in  personne_groupe table on click*/
			
			
			
			 
			   }
   
   






@Override
public void onItemClick(AdapterView<?> liste, View v, int position, long id) {
	// TODO Auto-generated method stub
	
}

       
     
       
   
  
    }	


   	   
	   
	   
	   
