package com.technoee.jmaghrebsched.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Lists;
import com.technoee.jmaghrebsched.model.Speaker;
import com.technoee.jmaghrebsched.model.Speakers;

public class SpeakersHandler {

	private static final String TAG = "";

	public SpeakersHandler(Context context) {
	}

	public List<Speaker> parseString(String json) {
		JsonFactory jsonFactory = new AndroidJsonFactory();
		try {
			Speakers presenters = jsonFactory.fromString(json, Speakers.class);
			return buildSpeakers(presenters);
		} catch (IOException e) {
			// LOGE(TAG, "Error reading speakers from packaged data", e);
			return Lists.newArrayList();
		}
	}

	private List<Speaker> buildSpeakers(Speakers speakersList) {
		List<Speaker> speakers = new ArrayList<Speaker>();
		if (speakersList != null) {
			speakers = speakersList.getPresenters();
		}
		return speakers;
	}
}
