package com.technoee.jmaghrebsched.model;

import java.util.List;

import com.google.api.client.util.Key;

public class DaySlot {

	@Key
	private String start;
	@Key
	private String end;
	@Key
	private String title;
	@Key
	private String subTitle;
	@Key
	private String meta;
	@Key
	private List<String> speakersId;
	@Key
	private String location;
	@Key
	private String id;

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public String getTitle() {
		return title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public String getMeta() {
		return meta;
	}

	public void setStart(String start) {
		this.start = start;
	}

	public void setEnd(String end) {
		this.end = end;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public void setMeta(String meta) {
		this.meta = meta;
	}

	public List<String> getSpeakersId() {
		return speakersId;
	}

	public void setSpeakersId(List<String> speakersId) {
		this.speakersId = speakersId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
