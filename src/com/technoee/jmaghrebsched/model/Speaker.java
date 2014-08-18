package com.technoee.jmaghrebsched.model;

import com.google.api.client.util.Key;

public class Speaker {

	@Key
	private String bio;
	@Key
	private String name;
	@Key
	private String id;
	@Key
	private String thumbnailRes;

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getThumbnailRes() {
		return thumbnailRes;
	}

	public void setThumbnailRes(String thumbnailRes) {
		this.thumbnailRes = thumbnailRes;
	}

}