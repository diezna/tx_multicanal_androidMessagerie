package fr.utt.socialize.controller;

import java.util.List;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import fr.utt.socialize.R;
import fr.utt.socialize.adapter.ListeMemberAdapter;
import fr.utt.socialize.database.ContactMailGroupManager;
import fr.utt.socialize.database.ContactMailManager;

import fr.utt.socialize.modele.Personne_groupe;

public class GroupeMailListController extends Fragment implements OnItemClickListener
{
	private ContactMailGroupManager contactMailGroupManager;
	  
	  
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.list, container, false);
		return view;
		
		
	}

   public void getMember(String nomgroupe){
	   
	   contactMailGroupManager =new ContactMailGroupManager(getActivity().getApplicationContext());
	   
	   
	   final List<Personne_groupe> items= contactMailGroupManager.getAllMembreOf(nomgroupe);
	  
		if(this.getActivity() == null)
		{
		return;
    	}
		
		

		ListeMemberAdapter objAdapter =  new ListeMemberAdapter(getActivity(), R.layout.alluser_row, items);
       ((ListView) this.getView().findViewById(R.id.list)).setAdapter(objAdapter);
       
     //  System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+items.get(1).toString());
      
   }
   
 

@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method =stub
	
}
}
   	   
	   
	   
	   
