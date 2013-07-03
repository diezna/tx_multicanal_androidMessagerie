package fr.utt.socialize.controller;
/**
 * Controlls main menu actions
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import fr.utt.socialize.R;

import fr.utt.socialize.activity.GroupeActivity;
import fr.utt.socialize.activity.MainActivity;
import fr.utt.socialize.activity.MediaActivity;
import fr.utt.socialize.activity.MessageActivity;

public class MainMenuController extends Fragment implements OnClickListener
{
	public static final int CONTACTS= 0;
	public static final int GROUPS = 1;
	public static final int MEDIA = 2;
	public static final int MESSAGES = 3;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.main_menu_bar, container, false);
		//Set click event listeners
		view.findViewById(R.id.news).setOnClickListener(this);
		view.findViewById(R.id.about).setOnClickListener(this);
		view.findViewById(R.id.media).setOnClickListener(this);
		view.findViewById(R.id.hobby).setOnClickListener(this);
		return view;
	}
	
	public void setActive(int tab)
	{
		this.getView().findViewById(R.id.news).setBackgroundResource(R.drawable.main_menu_button);
		this.getView().findViewById(R.id.about).setBackgroundResource(R.drawable.main_menu_button);
		this.getView().findViewById(R.id.media).setBackgroundResource(R.drawable.main_menu_button);
		this.getView().findViewById(R.id.hobby).setBackgroundResource(R.drawable.main_menu_button);
		switch(tab)
		{
			case CONTACTS:
				this.getView().findViewById(R.id.news).setBackgroundResource(R.drawable.main_menu_button_active);
			break;
			case GROUPS:
				this.getView().findViewById(R.id.about).setBackgroundResource(R.drawable.main_menu_button_active);
			break;
			case MEDIA:
				this.getView().findViewById(R.id.media).setBackgroundResource(R.drawable.main_menu_button_active);
			break;
			case MESSAGES:
				this.getView().findViewById(R.id.hobby).setBackgroundResource(R.drawable.main_menu_button_active);
			break;
		}
	}
	
	/** enable the tab*/
	
	@Override
	public void onClick(View view)
	{
		int tab = -1;
		Intent intent = null;
		
		int id = view.getId();
		if (id == R.id.news) {
			if(!(this.getActivity() instanceof MainActivity))
			{
				tab = CONTACTS;
				intent = new Intent(this.getActivity(), MainActivity.class);
			}
		 } 
		else if (id == R.id.about) {
			if(!(this.getActivity() instanceof GroupeActivity))
			{
				tab = GROUPS;
				intent = new Intent(this.getActivity(), GroupeActivity.class);
			}
		 } 
		else if (id == R.id.media) {
			if(!(this.getActivity() instanceof MediaActivity))
			{
				tab = MEDIA;
			intent = new Intent(this.getActivity(), MediaActivity.class);

			}
		}
		
		else if (id== R.id.hobby)	{
			if(!(this.getActivity() instanceof MessageActivity))
			{
				tab = MESSAGES;
			intent = new Intent(this.getActivity(), MessageActivity.class);

			}			
			
		}
		
		if(intent != null && tab != -1)
		{
			this.setActive(tab);
			this.startActivity(intent);
			// No animation
			this.getActivity().overridePendingTransition(0, 0);
			this.getActivity().finish();
		}
	}
}