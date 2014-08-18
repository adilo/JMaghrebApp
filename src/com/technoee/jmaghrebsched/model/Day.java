package com.technoee.jmaghrebsched.model;

import java.util.List;

import com.google.api.client.util.Key;

public class Day {

	@Key
	private String day;
	@Key
	private List<DaySlot> slot;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public List<DaySlot> getSlots() {
		return slot;
	}

	public void setSlots(List<DaySlot> slots) {
		this.slot = slots;
	}

}
