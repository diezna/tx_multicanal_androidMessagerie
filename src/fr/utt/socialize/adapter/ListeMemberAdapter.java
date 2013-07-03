package fr.utt.socialize.adapter;

import java.util.List;

import fr.utt.socialize.R;
import fr.utt.socialize.modele.Personne_groupe;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListeMemberAdapter extends ArrayAdapter<Personne_groupe> {
	
		private Activity activity;
		private List<Personne_groupe> items;
		private int row;
		private Personne_groupe objBean;

		public ListeMemberAdapter(Activity act, int row, List<Personne_groupe> items) {
			super(act, row, items);

			this.activity = act;
			this.row = row;
			this.items = items;

		}

		

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			View view = convertView;
			ViewHolder holder;
			if (view == null) {
				LayoutInflater inflater = (LayoutInflater) activity
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				view = inflater.inflate(row, null);

				holder = new ViewHolder();
				view.setTag(holder);
			} else {
				holder = (ViewHolder) view.getTag();
			}

			if ((items == null) || ((position + 1) > items.size()))
				return view;

			objBean = items.get(position);

			holder.tvname = (TextView) view.findViewById(R.id.name);
			holder.tvPhoneNo = (TextView) view.findViewById(R.id.phoneNumber);

			if (holder.tvname != null && null != objBean.getname_personne()
					&& objBean.getname_personne().trim().length() > 0) {
				holder.tvname.setText(Html.fromHtml(objBean.getname_personne()));
			}
			if (holder.tvPhoneNo != null && null != objBean.getmedia_contact()
					&& objBean.getmedia_contact().trim().length() > 0) {
				holder.tvPhoneNo.setText(Html.fromHtml(objBean.getmedia_contact()));
			}
			
			
			return view;
		}

		public class ViewHolder {
			public TextView tvname, tvPhoneNo;
		}

	}



