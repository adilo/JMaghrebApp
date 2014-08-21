package com.technoee.jmaghrebsched.model;

import com.google.api.client.util.Key;

public class Id {

	@Key(value = "$oid")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
