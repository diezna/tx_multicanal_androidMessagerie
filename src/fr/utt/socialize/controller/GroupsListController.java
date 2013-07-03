package fr.utt.socialize.controller;

import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import fr.utt.socialize.R;
import fr.utt.socialize.activity.ContactOfGroupeActivity;
import fr.utt.socialize.activity.GroupeActivity;
import fr.utt.socialize.activity.NewGroupeActivity;
import fr.utt.socialize.database.GroupeManager;
import fr.utt.socialize.modele.Groupe;
import fr.utt.socialize.modele.Personne;

public class GroupsListController extends Fragment implements OnItemClickListener
{
	
	GroupeManager  groupeManager;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.list, container, false);
		return view;
	}
	

	public void getGroups()
	{
		
		groupeManager= new GroupeManager(getActivity().getApplicationContext());
		 
			
 
		//groupeManager.createGroupe("utt");
		
	      List<Groupe> items= groupeManager.getAllGroupe();
			
			if(this.getActivity() == null)
			{
			return;
	     	}
			
			
	     	ArrayAdapter<Groupe> adapter = new ArrayAdapter<Groupe>(this.getActivity().getApplicationContext(), R.layout.simple_row, items);      
	        ((ListView) this.getView().findViewById(R.id.list)).setAdapter(adapter);
			//  System.out.println("list");
	        
        final ListView listView = (ListView)getView().findViewById(R.id.list);
	        listView.setOnItemClickListener(this);
	        
	        
	        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			
		String groupe= ((TextView) view).getText().toString();
		
		System.out.println(groupe);
		
			
			//Toast.makeText(getActivity().getApplicationContext(),  ((TextView) view).getText(), Toast.LENGTH_SHORT).show();
		
			
			Intent intent = new Intent(getActivity(), ContactOfGroupeActivity.class);

			intent.putExtra("name",groupe);
				startActivity(intent);
	
	        
	}
			
        });
	        
	}
	
	
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id)
	{
		//Do smth		
		Toast.makeText(this.getActivity().getApplicationContext(), "Nice group click!", Toast.LENGTH_SHORT).show();
	}
}