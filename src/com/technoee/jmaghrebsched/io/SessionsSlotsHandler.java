package com.technoee.jmaghrebsched.io;

import java.io.IOException;
import java.util.List;

import android.content.Context;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Lists;
import com.technoee.jmaghrebsched.model.Day;
import com.technoee.jmaghrebsched.model.Days;

public class SessionsSlotsHandler {

	private static final String TAG = "";

	public SessionsSlotsHandler(Context context) {
	}

	public List<Day> parseString(String json) {
		JsonFactory jsonFactory = new AndroidJsonFactory();
		try {
			Days days = jsonFactory.fromString(json, Days.class);
			return buildSlots(days);
		} catch (IOException e) {
			// LOGE(TAG, "Error reading speakers from packaged data", e);
			return Lists.newArrayList();
		}
	}

	private List<Day> buildSlots(Days days) {
		return days.getDays();
	}
}
