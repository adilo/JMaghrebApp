package com.technoee.jmaghrebsched.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Lists;
import com.technoee.jmaghrebsched.model.Section;
import com.technoee.jmaghrebsched.model.Sections;
import com.technoee.jmaghrebsched.model.Sponsor;

public class SponsorsHandler {

	private static final String TAG = "";

	public SponsorsHandler(Context context) {
	}

	public List<Section> parseString(String json) {
		JsonFactory jsonFactory = new AndroidJsonFactory();
		try {
			Sections sections = jsonFactory.fromString(json, Sections.class);
			return buildSponsors(sections);
		} catch (IOException e) {
			// LOGE(TAG, "Error reading speakers from packaged data", e);
			return Lists.newArrayList();
		}
	}

	private List<Section> buildSponsors(Sections sectionsList) {
		List<Section> sections = new ArrayList<Section>();
		if (sectionsList != null) {
			sections = sectionsList.getSections();
		}
		return sections;
	}
}
