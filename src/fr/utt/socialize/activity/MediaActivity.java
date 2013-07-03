package fr.utt.socialize.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;
import fr.utt.socialize.R;
import fr.utt.socialize.constants.NameBarConstants;
import fr.utt.socialize.controller.MainMenuController;
import fr.utt.socialize.controller.MediaListController;

public class MediaActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.media_activity);
		
		
		// Main menu
		MainMenuController mainMenu = (MainMenuController) getSupportFragmentManager().findFragmentById(R.id.main_menu_fragment);
		mainMenu.setActive(MainMenuController.MEDIA);
		
		// Set the screen title
		((TextView) this.findViewById(R.id.name_bar)).setText(NameBarConstants.MEDIA);
					
		// Ask the corresponding list controller the data
		MediaListController mediaListController = (MediaListController) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
          
		mediaListController.getMedia();
		
		



		
		
	}
}



