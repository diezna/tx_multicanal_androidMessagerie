package fr.utt.socialize.activity;

import java.util.List;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import fr.utt.socialize.R;
import fr.utt.socialize.adapter.MessageListAdapter;
import fr.utt.socialize.constants.NameBarConstants;
import fr.utt.socialize.controller.MainMenuController;
import fr.utt.socialize.controller.MessageListController;
import fr.utt.socialize.database.HistoriqueMesssageManager;
import fr.utt.socialize.modele.Message;
import fr.utt.socialize.modele.Personne_groupe;

public class MessageActivity extends FragmentActivity {
	
	private HistoriqueMesssageManager historiqueMessage;
    private ListView listView;
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_message);
			
			// Set active main menu tab
			MainMenuController mainMenu = (MainMenuController) getSupportFragmentManager().findFragmentById(R.id.main_menu_fragment);
			mainMenu.setActive(MainMenuController.MESSAGES);
			
			// Set the screen title
			((TextView) this.findViewById(R.id.name_bar)).setText(NameBarConstants.MESSAGE);
			
			
			
			
			MessageListController messageListController = (MessageListController) getSupportFragmentManager().findFragmentById(R.id.list_fragment);
		//	messageListController.getInbox();
		
			
			
			/**************************** get all message*************************/
			
			historiqueMessage= new HistoriqueMesssageManager(this.getApplicationContext());
			
		      List<Message> items= historiqueMessage.getAllMessage();
							
				final MessageListAdapter adapter = new MessageListAdapter(this, R.layout.alluser_row, items);  
				listView=(ListView)findViewById(R.id.inbox);

				
				 listView.setAdapter(adapter); 
		        
		        
		        
	}

	}

