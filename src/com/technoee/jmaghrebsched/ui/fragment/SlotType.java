package com.technoee.jmaghrebsched.ui.fragment;

import com.technoee.jmaghrebsched.R;

public enum SlotType {
	Welcome(R.drawable.ic_tarbouch), Lunch(R.drawable.lunch), Coffee(R.drawable.coffee), Session(0), GoodBye(
			R.drawable.ic_tarbouch);
	private int imageRes;

	private SlotType(int imageRes) {
		this.imageRes = imageRes;
	}

	public int getImageRes() {
		return imageRes;
	};

	public static SlotType getSlotType(String meta) {
		if (meta.equalsIgnoreCase(Welcome.toString())) {
			return Welcome;
		} else if (meta.equalsIgnoreCase(Lunch.toString())) {
			return Lunch;
		} else if (meta.equalsIgnoreCase(Coffee.toString())) {
			return Coffee;
		} else if (meta.equalsIgnoreCase(Session.toString())) {
			return Session;
		} else if (meta.equalsIgnoreCase(GoodBye.toString())) {
			return GoodBye;
		} else {
			return Session;
		}
	}

}
