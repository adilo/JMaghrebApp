package com.technoee.jmaghrebsched.io;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.Lists;
import com.technoee.jmaghrebsched.model.GenericEntity;
import com.technoee.jmaghrebsched.model.GenericEntities;

public class ObjectHandler<Ts extends GenericEntities, T extends GenericEntity> {

	private final Class<Ts> type;

	public ObjectHandler(Context context, Class<Ts> type) {
		this.type = type;
	}

	@SuppressWarnings("unchecked")
	public List<T> parseString(String json) {
		JsonFactory jsonFactory = new AndroidJsonFactory();
		try {
			Ts talks = jsonFactory.fromString(json, type);
			return buildTalks(talks);
		} catch (IOException e) {
			// LOGE(TAG, "Error reading speakers from packaged data", e);
			return Lists.newArrayList();
		}
	}

	@SuppressWarnings("unchecked")
	private List<T> buildTalks(Ts talksList) {
		List<T> talks = new ArrayList<T>();
		if (talksList != null) {
			talks = (List<T>) talksList.getEntities();
		}
		return talks;
	}
}
