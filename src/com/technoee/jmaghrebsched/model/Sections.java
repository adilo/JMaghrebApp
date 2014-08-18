package com.technoee.jmaghrebsched.model;

import java.util.List;

import com.google.api.client.util.Key;

public class Sections {

	@Key
	private List<Section> sections;

	public List<Section> getSections() {
		return sections;
	}
}
