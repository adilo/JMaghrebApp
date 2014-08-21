package com.technoee.jmaghrebsched.model;

import com.google.api.client.util.Key;

public class Talk extends GenericEntity {

	@Key(value = "_id")
	private Id id;

	@Key(value = "abstract")
	private String theAbstract;

	@Key(value = "created")
	private TheDate date;

	@Key
	private Experience experience;

	@Key
	private Language language;

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getTheAbstract() {
		return theAbstract;
	}

	public void setTheAbstract(String theAbstract) {
		this.theAbstract = theAbstract;
	}

	public TheDate getDate() {
		return date;
	}

	public void setDate(TheDate date) {
		this.date = date;
	}

	public Experience getExperience() {
		return experience;
	}

	public void setExperience(Experience experience) {
		this.experience = experience;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}

}
