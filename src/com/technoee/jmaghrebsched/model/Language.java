package com.technoee.jmaghrebsched.model;

import com.google.api.client.util.Key;

public class Language {
	@Key
	private String label;
	@Key
	private String value;
	@Key
	private String order;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

}
