package fr.utt.socialize.controller;


import fr.utt.socialize.R;
import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;



public class MediaListController extends Fragment  {
	//private static final String URL_PREFIX_FRIENDS = "https://graph.facebook.com/me/friends?access_token=";

//  private Session.StatusCallback statusCallback= new SessionStatusCallback();
    private Button FacebookLogin;
    private Button TwitterLogin;
    private Bundle savedInstanceState;

    public 	View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    
   {
        super.onCreate(savedInstanceState);

		View view = inflater.inflate(R.layout.list_media, container, false);	
		 
		return view;
   }

	
		public void getMedia()
		{
		    
			FacebookLogin = (Button) this.getView().findViewById(R.id.Facebook);
			FacebookLogin.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
					//On instancie notre layout en tant que View
					LayoutInflater factory = LayoutInflater.from(getActivity());
			        final View alertDialogView = factory.inflate(R.layout.splash, null);
					
				//	open an alertDialog onItemClick
					
	        		AlertDialog.Builder adb = new AlertDialog.Builder(getActivity());
	        		
	                adb.setView(alertDialogView);

	        		
	        		// titre de la boite de dialogue avec le nom de l'items selectionner
	        		adb.setTitle("login facebook" );       		       		        		
	        			        		       		
	        		//on indique que l'on veut le bouton ok à notre boite de dialogue
	        		//adb.setPositiveButton("Ok", null);
	        		//on affiche la boite de dialogue
	        		adb.show();  } });
			
			
	        
			
		}
		
}
	
