package com.technoee.jmaghrebsched.ui.fragment;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.technoee.jmaghrebsched.R;
import com.technoee.jmaghrebsched.model.Sponsor;
import com.technoee.jmaghrebsched.ui.MainActivity;
import com.technoee.jmaghrebsched.ui.adapter.SimpleSectionedListAdapter;
import com.technoee.jmaghrebsched.ui.adapter.SimpleSectionedListAdapter.Section;

public class SponsorsFragment extends ListFragment {

	private MyAdapter mScheduleAdapter;
	private SimpleSectionedListAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		List<Sponsor> sponsors = new ArrayList<Sponsor>();
		Section[] sections = new Section[MainActivity.sponsors.size()];
		int count = 0;
		for (com.technoee.jmaghrebsched.model.Section section : MainActivity.sponsors) {
			sections[count] = new Section(sponsors.size(), section.getName());
			sponsors.addAll(section.getSponsors());
			count++;
		}

		mScheduleAdapter = new MyAdapter(getActivity(), sponsors);
		mAdapter = new SimpleSectionedListAdapter(getActivity(),
				R.layout.list_item_schedule_header, mScheduleAdapter);
		setListAdapter(mAdapter);

		mAdapter.setSections(sections);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_list_sponsors, container,
				false);
		root.setBackgroundColor(Color.WHITE);
		ListView listView = (ListView) root.findViewById(android.R.id.list);
		listView.setItemsCanFocus(true);
		listView.setCacheColorHint(Color.WHITE);
		listView.setSelector(android.R.color.transparent);
		return root;
	}

	class MyAdapter extends ArrayAdapter<Sponsor> {

		private List<Sponsor> sponsors;
		private Context context;

		public MyAdapter(Context context, List<Sponsor> objects) {
			super(context, android.R.layout.simple_list_item_1, objects);
			this.sponsors = objects;
			this.context = context;
		}

		class ViewHolder {
			private ImageView img;
			private View container;

			public ViewHolder(View view) {
				img = (ImageView) view.findViewById(R.id.img_sponsor);
				container = view.findViewById(R.id.container);

			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.list_item_sponsor_block, parent, false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Sponsor sponsor = sponsors.get(position);
			holder.img.setImageResource(getResourceByName(sponsor.getImgRes()));

			holder.container.setTag(sponsor);
			holder.container.setBackgroundResource(R.drawable.btn_action);
			holder.container.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					Sponsor sponsor = (Sponsor) v.getTag();
					String url = sponsor.getLink();
					Intent i = new Intent(Intent.ACTION_VIEW);
					i.setData(Uri.parse(url));
					context.startActivity(i);
				}
			});

			return convertView;
		}

		private int getResourceByName(String imgRes) {
			return context.getResources().getIdentifier(imgRes, "drawable",
					context.getPackageName());
		}
	}

}
