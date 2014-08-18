package com.technoee.jmaghrebsched.model;

import java.util.List;

import com.google.api.client.util.Key;

public class Speakers {

	@Key
	private List<Speaker> presenters;

	public List<Speaker> getPresenters() {
		return presenters;
	}
}
