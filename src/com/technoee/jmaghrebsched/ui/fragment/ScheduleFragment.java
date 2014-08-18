package com.technoee.jmaghrebsched.ui.fragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.technoee.jmaghrebsched.R;
import com.technoee.jmaghrebsched.model.Day;
import com.technoee.jmaghrebsched.model.DaySlot;
import com.technoee.jmaghrebsched.ui.MainActivity;
import com.technoee.jmaghrebsched.ui.SessionsActivity;
import com.technoee.jmaghrebsched.ui.adapter.SimpleSectionedListAdapter;
import com.technoee.jmaghrebsched.ui.adapter.SimpleSectionedListAdapter.Section;

public class ScheduleFragment extends ListFragment implements
		LoaderManager.LoaderCallbacks<Cursor>, ActionMode.Callback {

	private ListAdapter mScheduleAdapter;
	private SimpleSectionedListAdapter mAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public void onResume() {
		super.onResume();
		List<Slot> savedSlots = getSlots();

		List[] s = new ArrayList[2];
		List<Slot> emptySlots = new ArrayList<Slot>();
		emptySlots.add(new Slot("11:00", "", "", "", SlotType.Session, 0));
		emptySlots.add(new Slot("12:00", "", "", "", SlotType.Session, 0));
		emptySlots.add(new Slot("14:00", "", "", "", SlotType.Session, 0));
		emptySlots.add(new Slot("15:00", "", "", "", SlotType.Session, 0));
		emptySlots.add(new Slot("16:30", "", "", "", SlotType.Session, 0));
		emptySlots.add(new Slot("17:30", "", "", "", SlotType.Session, 0));

		for (Slot slot : emptySlots) {
			for (Slot savedSlot : savedSlots) {
				if (savedSlot.getDayNum() == 0) {
					if (savedSlot.getStartTimeH() == slot.getStartTimeH()) {
						slot.setTitle(savedSlot.getTitle());
						slot.setSubTitle(savedSlot.getSubTitle());
						slot.setDayNum(0);
					}
				}
			}
		}

		List<Slot> emptySlots2 = new ArrayList<Slot>();
		emptySlots2.add(new Slot("10:00", "", "", "", SlotType.Session, 1));
		emptySlots2.add(new Slot("11:00", "", "", "", SlotType.Session, 1));
		emptySlots2.add(new Slot("14:00", "", "", "", SlotType.Session, 1));
		emptySlots2.add(new Slot("15:00", "", "", "", SlotType.Session, 1));
		emptySlots2.add(new Slot("16:30", "", "", "", SlotType.Session, 1));

		for (Slot slot : emptySlots2) {
			for (Slot savedSlot : savedSlots) {
				if (savedSlot.getDayNum() == 1) {
					if (savedSlot.getStartTimeH() == slot.getStartTimeH()) {
						slot.setTitle(savedSlot.getTitle());
						slot.setSubTitle(savedSlot.getSubTitle());
						slot.setDayNum(1);
					}
				}
			}
		}

		s[0] = emptySlots;
		s[1] = emptySlots2;

		List<Slot> slots = new ArrayList<Slot>();
		Section[] sections = new Section[2];

		int count = 0;
		for (Day day : MainActivity.days) {
			List<Slot> lslots = new ArrayList<Slot>();
			sections[count] = new Section(slots.size(), day.getDay());
			for (DaySlot slot : day.getSlots()) {
				Slot slot2 = new Slot(slot);
				slot2.setDayNum(count);
				lslots.add(slot2);
			}
			lslots.addAll(s[count]);
			Collections.sort(lslots, new Comparator<Slot>() {

				@Override
				public int compare(Slot lhs, Slot rhs) {
					return lhs.getStartTime().compareTo(rhs.getStartTime());
				}

			});
			slots.addAll(lslots);
			count++;
		}

		mScheduleAdapter = new MyAdapter(getActivity(), slots);
		mAdapter = new SimpleSectionedListAdapter(getActivity(),
				R.layout.list_item_schedule_header, mScheduleAdapter);
		setListAdapter(mAdapter);

		mAdapter.setSections(sections);
	}

	private List<Slot> getSlots() {
		return MainActivity.savedSlots;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_list_with_empty_container,
				container, false);
		root.setBackgroundColor(Color.WHITE);
		ListView listView = (ListView) root.findViewById(android.R.id.list);
		listView.setItemsCanFocus(true);
		listView.setCacheColorHint(Color.WHITE);
		listView.setSelector(android.R.color.transparent);

		return root;
	}

	class MyAdapter extends ArrayAdapter<Slot> {

		private List<Slot> objects;

		public MyAdapter(Context context, List<Slot> slots) {
			super(context, android.R.layout.simple_list_item_1, slots);
			this.objects = slots;
		}

		class ViewHolder {
			private TextView title;
			private TextView subTitle;
			private TextView endTime;
			private TextView time;
			private ImageButton btnExtra;
			private LinearLayout middleContainer;

			public ViewHolder(View view) {
				time = (TextView) view.findViewById(R.id.block_time);
				endTime = (TextView) view.findViewById(R.id.block_endtime);
				title = (TextView) view.findViewById(R.id.block_title);
				subTitle = (TextView) view.findViewById(R.id.block_subtitle);
				btnExtra = (ImageButton) view.findViewById(R.id.extra_button);
				middleContainer = (LinearLayout) view.findViewById(R.id.list_item_middle_container);
			}
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder = null;
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(
						R.layout.list_item_schedule_block, parent, false);
				holder = new ViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}

			Slot slot = objects.get(position);
			holder.time.setText(slot.getStartTime());
			holder.endTime.setText(slot.getEndTime());

			// title
			holder.title.setText(slot.getTitle());
			holder.subTitle.setText(slot.getSubTitle());
			holder.title.setTextColor(getResources().getColor(R.color.body_text_1));
			if (slot.getTitle().equals("")) {
				holder.title.setText("Browse sessions...");
				holder.subTitle.setText("Empty slot");

				holder.title.setTextColor(getResources().getColor(R.color.body_text_1_positive));
			}

			// extra
			holder.btnExtra.setTag(slot);
			if (!slot.getSlotType().equals(SlotType.Session)) {
				holder.btnExtra.setEnabled(false);
				holder.btnExtra.setImageResource(slot.getSlotType().getImageRes());

			} else {
				holder.btnExtra.setEnabled(true);
				holder.btnExtra.setOnClickListener(btn_extra_click_listener);
				holder.btnExtra.setImageResource(R.drawable.schedule_button_more);

			}

			return convertView;
		}

		private OnClickListener btn_extra_click_listener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				Slot slot = (Slot) v.getTag();
				Intent intent = new Intent(getActivity(), SessionsActivity.class);
				intent.putExtra(SessionsActivity.DAY_KEY, slot.getDayNum());
				intent.putExtra(SessionsActivity.START_KEY, slot.getStartTimeH());
				startActivity(intent);
			}
		};
	}

	@Override
	public boolean onActionItemClicked(ActionMode arg0, MenuItem arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onCreateActionMode(ActionMode arg0, Menu arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onDestroyActionMode(ActionMode arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Loader<Cursor> onCreateLoader(int arg0, Bundle arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onLoadFinished(Loader<Cursor> arg0, Cursor arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLoaderReset(Loader<Cursor> arg0) {
		// TODO Auto-generated method stub

	}
}
