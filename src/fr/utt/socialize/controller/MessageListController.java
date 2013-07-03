package fr.utt.socialize.controller;

	import java.util.ArrayList;
import java.util.List;
	import android.os.Bundle;
	import android.support.v4.app.Fragment;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.ArrayAdapter;
	import android.widget.ListView;
	import fr.utt.socialize.R;	
import fr.utt.socialize.adapter.MessageListAdapter;
    import fr.utt.socialize.database.HistoriqueMesssageManager;
import fr.utt.socialize.modele.Message;

	public class MessageListController extends Fragment  {
		
		private HistoriqueMesssageManager historiqueMessage;

		
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
		{
			View view = inflater.inflate(R.layout.list, container, false);
			return view;
		}
		

		public void getInbox()
		{
			
			historiqueMessage= new HistoriqueMesssageManager(this.getActivity().getApplicationContext());
		
		      List<Message> items= historiqueMessage.getAllMessage();
				
				if(this.getActivity() == null)
				{
				return;
		     	}
								
				MessageListAdapter adapter = new MessageListAdapter(getActivity(), R.layout.alluser_row, items);      
		        ((ListView) this.getView().findViewById(R.id.list)).setAdapter(adapter);
		        
		     
		        
		}
		
		
		
	}


