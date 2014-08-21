package com.technoee.jmaghrebsched.ui.fragment;

import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.technoee.jmaghrebsched.R;
import com.technoee.jmaghrebsched.model.Speaker;
import com.technoee.jmaghrebsched.ui.MainActivity;

public class SpeakersFragment extends ListFragment {

	private MyAdapter mSpeakersAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mSpeakersAdapter = new MyAdapter(getActivity(), MainActivity.speakers);
		setListAdapter(mSpeakersAdapter);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.copy_fragment_list_sponsors,
				container, false);
		root.setBackgroundColor(Color.WHITE);
		ListView listView = (ListView) root.findViewById(android.R.id.list);
		listView.setItemsCanFocus(true);
		listView.setCacheColorHint(Color.WHITE);
		listView.setSelector(android.R.color.transparent);

		return root;
	}

	class MyAdapter extends ArrayAdapter<Speaker> {

		private List<Speaker> speakers;
		private Context context;

		public MyAdapter(Context context, List<Speaker> speakers) {
			super(context, android.R.layout.simple_list_item_1, speakers);
			this.speakers = speakers;
			this.context = context;
		}

		class ViewHolder {
			private ImageView img;
			private TextView txt;
			private View container;

			public ViewHolder(View view) {
				img = (ImageView) view.findViewById(R.id.img_sponsor);
				txt = (TextView) view.findViewById(R.id.block_title);
				container = view.findViewById(R.id.container);

			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.list_item_spaekers, parent, false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Speaker speaker = speakers.get(position);
			holder.txt.setText(speaker.getName());
			holder.img.setImageResource(getResourceByName(speaker.getThumbnail()));

			holder.container.setTag(speaker);
			holder.container.setBackgroundResource(R.drawable.btn_action);
			holder.container.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Speaker speaker = (Speaker) v.getTag();

					Dialog dialog = new Dialog(getContext());
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
					dialog.setContentView(R.layout.list_item_popup_spaekers);

					// title
					TextView textView = (TextView) dialog.findViewById(R.id.block_title);
					textView.setText(speaker.getName());

					// img
					ImageView imageView = (ImageView) dialog.findViewById(R.id.img_sponsor);
					imageView.setImageResource(getResourceByName(speaker.getThumbnail()));

					// Bio
					WebView view = new WebView(getContext());
					view.setVerticalScrollBarEnabled(false);
					((LinearLayout) dialog.findViewById(R.id.view1)).addView(view);
					// view.loadData(speaker.getBio(), "text/html", "utf-8");

					WebSettings settings = view.getSettings();
					settings.setDefaultTextEncodingName("utf-8");
					view.loadDataWithBaseURL(null, speaker.getBio(), "text/html", "utf-8", null);

					// show
					dialog.show();
				}
			});
			convertView.setBackgroundResource(R.drawable.btn_action);
			// convertView.setOnClickListener(new OnClickListener() {
			//
			// @Override
			// public void onClick(View v) {
			// holder.txt.performClick();
			// }
			// });

			return convertView;
		}

		private int getResourceByName(String imgRes) {
			return context.getResources().getIdentifier(imgRes, "drawable",
					context.getPackageName());
		}
	}
}
