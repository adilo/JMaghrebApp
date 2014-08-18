package com.technoee.jmaghrebsched.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.technoee.jmaghrebsched.R;
import com.technoee.jmaghrebsched.model.Day;
import com.technoee.jmaghrebsched.model.DaySlot;
import com.technoee.jmaghrebsched.model.Speaker;
import com.technoee.jmaghrebsched.ui.fragment.Slot;
import com.technoee.jmaghrebsched.ui.fragment.SlotType;

public class SessionsActivity extends BaseActivity {

	public static final String START_KEY = "START";
	public static final String DAY_KEY = "DAY";
	private int day;
	private int start;
	private CharSequence dayName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_sessions_activity);

		ListView listView = (ListView) findViewById(android.R.id.list);
		listView.setItemsCanFocus(true);
		listView.setCacheColorHint(Color.WHITE);
		listView.setSelector(android.R.color.transparent);

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			day = extras.getInt(DAY_KEY);
			start = extras.getInt(START_KEY);
		}

		Day day2 = MainActivity.sessionsDays.get(day);
		getSupportActionBar().setTitle(dayName);
		List<DaySlot> slots = new ArrayList<DaySlot>();
		for (DaySlot daySlot : day2.getSlots()) {
			if (daySlot.getStart().startsWith(start + "")) {
				daySlot.setMeta("Session");
				slots.add(daySlot);
			}
		}
		SessionAdapter adapter = new SessionAdapter(this, slots);
		listView.setAdapter(adapter);
	}

	class SessionAdapter extends ArrayAdapter<DaySlot> {

		private List<DaySlot> objects;

		public SessionAdapter(Context context, List<DaySlot> objects) {
			super(context, android.R.layout.simple_list_item_1, objects);
			this.objects = objects;
		}

		class ViewHolder {
			private TextView title;
			private TextView endTime;
			private TextView time;
			private ImageButton btnExtra;
			private LinearLayout speakersView;
			private TextView location;

			public ViewHolder(View view) {
				time = (TextView) view.findViewById(R.id.block_time);
				endTime = (TextView) view.findViewById(R.id.block_endtime);
				title = (TextView) view.findViewById(R.id.block_title);
				location = (TextView) view.findViewById(R.id.location);
				btnExtra = (ImageButton) view.findViewById(R.id.extra_button);
				speakersView = (LinearLayout) view.findViewById(R.id.speakers_view);

			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.list_item_sessions, parent, false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			DaySlot slot = objects.get(position);
			holder.time.setText(slot.getStart());
			holder.endTime.setText(slot.getEnd());
			holder.title.setText(slot.getTitle());
			holder.location.setText(slot.getLocation());

			if (MainActivity.savedSessionsContains(slot.getId())) {
				holder.btnExtra.setImageResource(R.drawable.ic_discard);
			} else {
				holder.btnExtra.setImageResource(R.drawable.ic_add);
			}

			holder.btnExtra.setTag(slot);
			holder.btnExtra.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					ImageButton btn = (ImageButton) v;
					Slot slot = new Slot((DaySlot) v.getTag());
					slot.setSlotType(SlotType.Session);
					slot.setDayNum(day);

					if (MainActivity.savedSessionsContains(slot.getId())) {
						MainActivity.removeSavedSessionById(slot.getId());
						btn.setImageResource(R.drawable.ic_add);
					} else {
						MainActivity.addSessionById(slot);
						btn.setImageResource(R.drawable.ic_discard);
					}

				}
			});

			holder.speakersView.removeViews(1, holder.speakersView.getChildCount()-1);
			List<String> speakersId = slot.getSpeakersId();
			for (String id : speakersId) {
				View inflate = LayoutInflater.from(getContext()).inflate(
						R.layout.list_item_spaekers, holder.speakersView, false);
				ImageView img = (ImageView) inflate.findViewById(R.id.img_sponsor);
				TextView txt = (TextView) inflate.findViewById(R.id.block_title);
				View container = inflate.findViewById(R.id.container);

				Speaker speaker = MainActivity.getSpeakerById(id);
				txt.setText(speaker.getName());
				img.setImageResource(getResourceByName(speaker.getThumbnailRes()));
				holder.speakersView.addView(inflate);

			}

			return convertView;
		}

		private int getResourceByName(String imgRes) {
			return getResources().getIdentifier(imgRes, "drawable", getPackageName());
		}

	}
}
