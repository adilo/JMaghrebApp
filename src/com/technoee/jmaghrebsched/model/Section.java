package com.technoee.jmaghrebsched.model;

import java.util.List;

import com.google.api.client.util.Key;

public class Section {

	@Key
	private String name;
	@Key
	private List<Sponsor> sponsors;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Sponsor> getSponsors() {
		return sponsors;
	}

	public void setSponsors(List<Sponsor> sponsors) {
		this.sponsors = sponsors;
	}

}
