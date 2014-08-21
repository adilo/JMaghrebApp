package com.technoee.jmaghrebsched.model;

import java.util.ArrayList;
import java.util.List;

import com.google.api.client.util.Key;

public class Talks extends GenericEntities {

	@Key
	private List<Talk> talks;

	@Override
	public List<GenericEntity> getEntities() {
		List<GenericEntity> entities = new ArrayList<GenericEntity>();
		for (Talk t : talks) {
			entities.add(t);
		}
		return entities;
	}
}
