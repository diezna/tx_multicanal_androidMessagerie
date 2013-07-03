package fr.utt.socialize.adapter;


	import java.util.ArrayList;
import java.util.List;

import fr.utt.socialize.R;
import fr.utt.socialize.modele.Message;

	import android.content.Context;
	import android.view.LayoutInflater;
	import android.view.View;
	import android.view.ViewGroup;
	import android.widget.ArrayAdapter;
import android.widget.TextView;

	public class MessageListAdapter extends ArrayAdapter<Message> {
	    private Context ctx;
	    public List<Message> messageListArray;
	    public MessageListAdapter(Context context, int textViewResourceId, List<Message> messageListArray) {
	        super(context, textViewResourceId);
	        this.messageListArray = messageListArray;
	        this.ctx = context;
	    }

	    @Override
	    public View getView(int position, View convertView, ViewGroup parent) {
	        Holder holder;
	        View convertView1 = convertView;
	        if (convertView1 == null) {
	            holder = new Holder();
	            LayoutInflater vi = (LayoutInflater) ctx
	                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	            convertView1 = vi.inflate(R.layout.message_list_item, null);
	            holder.messageTo = (TextView) convertView1.findViewById(R.id.txt_msgTO);
	           // holder.messageDate = (TextView) convertView1.findViewById(R.id.txt_msgDate);
	            holder.messageContent = (TextView) convertView1.findViewById(R.id.txt_messageContent);
	            convertView1.setTag(holder);
	        } else {
	            holder = (Holder) convertView1.getTag();
	        }
	        Message message = getItem(position);
          
	        holder.messageTo.setText("to "+ message.getdestinataire());
	       // holder.messageDate.setText(message.getDate() +" : ");

	        holder.messageContent.setText(message.getContenu_message());

	        return convertView1;
	    }

	    @Override
	    public int getCount() {
	        return messageListArray.size();
	    }

	    @Override
	    public Message getItem(int position) {
	        return messageListArray.get(position);
	    }

	    public void setArrayList(ArrayList<Message> messageList) {
	        this.messageListArray = messageList;
	        notifyDataSetChanged();
	    }

	/*    private class Holder {
	        public TextView messageTo, messageContent,messageDate;
	    }*/
	    
	    private class Holder {
	        public TextView messageTo, messageContent;
	    }

	}