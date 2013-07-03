/**
 * Copyright 2010-present Facebook.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package fr.utt.socialize.facebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.facebook.internal.Utility;

import fr.utt.socialize.R;

public class FriendsList extends Activity implements OnItemClickListener {
    private Handler mHandler;

    protected ListView friendsList;
    protected static JSONArray jsonArray;
    protected String graph_or_fql;

    /*
     * Layout the friends' list
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mHandler = new Handler();
        setContentView(R.layout.friends_list);

        Bundle extras = getIntent().getExtras();
        String apiResponse = extras.getString("API_RESPONSE");
        graph_or_fql = extras.getString("METHOD");
     //   try {
            if (graph_or_fql.equals("graph")) {
                try {
					jsonArray = new JSONObject(apiResponse).getJSONArray("data");
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            } else {
                try {
					jsonArray = new JSONArray(apiResponse);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
      //  } catch (JSONException e)
           /* {
           // showToast(getString("Error: " + e.getMessage()));
           // return;
        }*/
        friendsList = (ListView) findViewById(R.id.friends_list);
        friendsList.setOnItemClickListener(this);
        friendsList.setAdapter(new FriendListAdapter(this));

      // showToast(getString(R.string.can_post_on_wall));
    }

   

    /**
     * Definition of the list adapter
     */
    public class FriendListAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        FriendsList friendsList;

        public FriendListAdapter(FriendsList friendsList) {
            this.friendsList = friendsList;
       /*     if (Utility.model == null) {
                Utility.model = new FriendsGetProfilePics();
            }*/
           // Utility.model.setListener(this);
            mInflater = LayoutInflater.from(friendsList.getBaseContext());
        }

        @Override
        public int getCount() {
            return jsonArray.length();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            JSONObject jsonObject = null;
            try {
                jsonObject = jsonArray.getJSONObject(position);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
            View hView = convertView;
            if (convertView == null) {
                hView = mInflater.inflate(R.layout.friend_item, null);
                ViewHolder holder = new ViewHolder();
                holder.profile_pic = (ImageView) hView.findViewById(R.id.profile_pic);
                holder.name = (TextView) hView.findViewById(R.id.name);
                holder.info = (TextView) hView.findViewById(R.id.info);
                hView.setTag(holder);
            }

            ViewHolder holder = (ViewHolder) hView.getTag();
          /*  try {
                if (graph_or_fql.equals("graph")) {
                    holder.profile_pic.setImageBitmap(Utility.model.getImage(
                            jsonObject.getString("id"), jsonObject.getString("picture")));
                } else {
                    holder.profile_pic.setImageBitmap(Utility.model.getImage(
                            jsonObject.getString("uid"), jsonObject.getString("pic_square")));
                }
            } catch (JSONException e) {
                holder.name.setText("");
            }*/
            try {
                holder.name.setText(jsonObject.getString("name"));
            } catch (JSONException e) {
                holder.name.setText("");
            }
            try {
                if (graph_or_fql.equals("graph")) {
                    holder.info.setText(jsonObject.getJSONObject("location").getString("name"));
                } else {
                    JSONObject location = jsonObject.getJSONObject("current_location");
                    holder.info.setText(location.getString("city") + ", "
                            + location.getString("state"));
                }

            } catch (JSONException e) {
                holder.info.setText("");
            }
            return hView;
        }

    }

    class ViewHolder {
        ImageView profile_pic;
        TextView name;
        TextView info;
    }

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
}
