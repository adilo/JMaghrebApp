package com.technoee.jmaghrebsched.model;

import com.google.api.client.util.Key;

public class Speaker extends GenericEntity{

	@Key(value = "_id")
	private Id id;

	@Key
	private int order;

	@Key
	private String bio;

	@Key(value = "fname")
	private String fname;

	@Key(value = "lname")
	private String lname;

	@Key
	private String twitter;

	@Key
	private String title;

	@Key(value = "image")
	private String thumbnail;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getName() {
		return fname + " " + lname;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

}