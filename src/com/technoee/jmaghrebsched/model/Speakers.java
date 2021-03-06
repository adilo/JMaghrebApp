package com.technoee.jmaghrebsched.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.util.Key;

public class Speakers extends GenericEntities {

	@Key
	private List<Speaker> speakers;

	@Override
	public List<GenericEntity> getEntities() {
		List<GenericEntity> entities = new ArrayList<GenericEntity>();
		for (Speaker s : speakers) {
			entities.add(s);
		}
		return entities;
	}
}
