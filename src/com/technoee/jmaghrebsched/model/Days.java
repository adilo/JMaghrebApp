package com.technoee.jmaghrebsched.model;

import java.util.List;

import com.google.api.client.util.Key;

public class Days {

	@Key
	private List<Day> days;

	public List<Day> getDays() {
		return days;
	}
}
