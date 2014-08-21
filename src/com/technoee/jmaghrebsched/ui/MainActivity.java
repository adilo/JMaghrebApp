package com.technoee.jmaghrebsched.ui;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.technoee.jmaghrebsched.R;
import com.technoee.jmaghrebsched.dao.DbHelper;
import com.technoee.jmaghrebsched.dao.SessionDAO;
import com.technoee.jmaghrebsched.io.CommonSlotsHandler;
import com.technoee.jmaghrebsched.io.JSONHandler;
import com.technoee.jmaghrebsched.io.ObjectHandler;
import com.technoee.jmaghrebsched.io.SponsorsHandler;
import com.technoee.jmaghrebsched.model.Day;
import com.technoee.jmaghrebsched.model.DaySlot;
import com.technoee.jmaghrebsched.model.Section;
import com.technoee.jmaghrebsched.model.Speaker;
import com.technoee.jmaghrebsched.model.Speakers;
import com.technoee.jmaghrebsched.model.Talk;
import com.technoee.jmaghrebsched.model.Talks;
import com.technoee.jmaghrebsched.ui.fragment.ScheduleFragment;
import com.technoee.jmaghrebsched.ui.fragment.Slot;
import com.technoee.jmaghrebsched.ui.fragment.SpeakersFragment;
import com.technoee.jmaghrebsched.ui.fragment.SponsorsFragment;

public class MainActivity extends BaseActivity implements ActionBar.TabListener,
		ViewPager.OnPageChangeListener {

	private ViewPager mViewPager;
	private List<Talk> talks;
	private static SQLiteDatabase db;
	private static DbHelper helper;
	public static List<Section> sponsors;
	public static List<Speaker> speakers;
	public static List<Day> days;
	public static List<Day> sessionsDays;
	public static List<Slot> savedSlots = new ArrayList<Slot>();

	public static Speaker getSpeakerById(String id) {
		for (Speaker _speaker : speakers) {
			if (_speaker.getId().equals(id + "")) {
				return _speaker;
			}
		}
		return null;
	}

	public static boolean savedSessionsContains(String id) {
		for (Slot slot : savedSlots) {
			if (slot.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	public static void removeSavedSessionById(String id) {
		Slot savedSessionById = getSavedSessionById(id);
		if (savedSessionById != null) {
			savedSlots.remove(savedSessionById);
			SessionDAO.remove(id, db);
		}
	}

	public static void addSessionById(Slot slot) {
		savedSlots.add(slot);
		SessionDAO.add(slot.getId(), db);
	}

	public static List<Slot> getSavedSlots() {
		List<Slot> savedSlots = new ArrayList<Slot>();
		List<String> slotsIds = SessionDAO.getSaveSlots(db);

		for (String string : slotsIds) {
			int count = 0;
			for (Day day : sessionsDays) {
				for (DaySlot daySlot : day.getSlots()) {
					if (string.equals(daySlot.getId()))
						savedSlots.add(new Slot(daySlot, count));
				}
				count++;
			}
		}
		return savedSlots;
	}

	public static Slot getSavedSessionById(String id) {
		for (Slot slot : savedSlots) {
			if (slot.getId().equals(id)) {
				return slot;
			}
		}
		return null;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		helper = new DbHelper(this);
		db = helper.getWritableDatabase();

		mViewPager = (ViewPager) findViewById(R.id.pager);
		mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
		mViewPager.setOnPageChangeListener(this);

		ActionBar bar = getSupportActionBar();
		bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		bar.addTab(bar.newTab().setText(R.string.title_schedule).setTabListener(this));
		bar.addTab(bar.newTab().setText(R.string.title_speakers).setTabListener(this));
		bar.addTab(bar.newTab().setText(R.string.title_sponsors).setTabListener(this));
		// bar.addTab(bar.newTab().setText(R.string.title_stream).setTabListener(this));

		try {
			String parseResource = JSONHandler.parseResource(this, R.raw.common_slots);
			CommonSlotsHandler commonSlotsHandler = new CommonSlotsHandler(this);
			days = commonSlotsHandler.parseString(parseResource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			String parseResource = JSONHandler.parseResource(this, R.raw.sessions);
			CommonSlotsHandler commonSlotsHandler = new CommonSlotsHandler(this);
			sessionsDays = commonSlotsHandler.parseString(parseResource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			String parseResource = JSONHandler.parseResource(this, R.raw.sponsors_sections);
			SponsorsHandler sponsorsHandler = new SponsorsHandler(this);
			sponsors = sponsorsHandler.parseString(parseResource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			String parseResource = JSONHandler.parseResource(this, R.raw.accepted_talks);
			ObjectHandler<Talks, Talk> sponsorsHandler = new ObjectHandler<Talks, Talk>(this,
					Talks.class);
			talks = sponsorsHandler.parseString(parseResource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			String parseResource = JSONHandler.parseResource(this, R.raw.accepted_talks);
			ObjectHandler<Speakers, Speaker> sponsorsHandler = new ObjectHandler<Speakers, Speaker>(
					this, Speakers.class);
			speakers = sponsorsHandler.parseString(parseResource);
		} catch (IOException e) {
			e.printStackTrace();
		}

		savedSlots = getSavedSlots();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		helper.close();
		db.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_register:
			Register.showRegistrationView(this);
			break;
		case R.id.action_about:
			Dialog dialog = new Dialog(this);
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
			dialog.setContentView(R.layout.activity_about);
			dialog.show();
			WebView view = new WebView(this);
			view.setVerticalScrollBarEnabled(false);

			((LinearLayout) dialog.findViewById(R.id.container1)).addView(view);

			view.loadData(getString(R.string.about_html), "text/html", "utf-8");
			break;
		default:
			break;
		}
		return true;
	}

	private class MainPagerAdapter extends FragmentPagerAdapter {

		public MainPagerAdapter(FragmentManager sfm) {
			super(sfm);
		}

		@Override
		public Fragment getItem(int position) {
			switch (position) {
			case 0:
				return new ScheduleFragment();

			case 1:
				return new SpeakersFragment();

			case 2:
				return new SponsorsFragment();

			}
			return null;
		}

		@Override
		public int getCount() {
			return 3;
		}

	}

	@Override
	public void onPageScrollStateChanged(int state) {
	}

	@Override
	public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
	}

	@Override
	public void onPageSelected(int position) {
		getSupportActionBar().setSelectedNavigationItem(position);
	}

	@Override
	public void onTabReselected(Tab arg0, FragmentTransaction arg1) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		mViewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}
