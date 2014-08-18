package com.technoee.jmaghrebsched.ui.fragment;

import java.util.List;

import com.technoee.jmaghrebsched.model.DaySlot;

public class Slot {
	private int startTimeH;
	private int startTimeM;
	private String startTime;
	private String endTime;
	private String title;
	private String subTitle;
	private SlotType slotType;
	private int dayNum;
	private String id;
	private String location;
	private List<String> speakersId;

	public Slot(String startTime, String endTime, String title, String subTitle, SlotType slotType,
			int dayNum) {
		super();
		this.setStartTime(startTime);
		String[] split = this.getStartTime().split(":");
		this.setStartTimeH(Integer.parseInt(split[0]));
		this.setStartTimeM(Integer.parseInt(split[1]));
		this.setEndTime(endTime);
		this.setTitle(title);
		this.setSubTitle(subTitle);
		this.setSlotType(slotType);
		this.dayNum = dayNum;
	}

	public Slot(DaySlot slot) {
		this.startTime = slot.getStart();
		this.endTime = slot.getEnd();
		this.title = slot.getTitle();
		this.subTitle = slot.getSubTitle();
		String[] split = this.getStartTime().split(":");
		this.setStartTimeH(Integer.parseInt(split[0]));
		this.setStartTimeM(Integer.parseInt(split[1]));
		this.setId(slot.getId());
		if (slot.getMeta() != null) this.slotType = SlotType.getSlotType(slot.getMeta());
		this.setLocation(slot.getLocation());
		this.setSpeakersId(slot.getSpeakersId());
	}

	public Slot(DaySlot daySlot, int count) {
		this(daySlot);
		this.dayNum = count;
	}

	public int getStartTimeH() {
		return startTimeH;
	}

	public void setStartTimeH(int startTimeH) {
		this.startTimeH = startTimeH;
	}

	public int getStartTimeM() {
		return startTimeM;
	}

	public void setStartTimeM(int startTimeM) {
		this.startTimeM = startTimeM;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubTitle() {
		return subTitle;
	}

	public void setSubTitle(String subTitle) {
		this.subTitle = subTitle;
	}

	public SlotType getSlotType() {
		return slotType;
	}

	public void setSlotType(SlotType slotType) {
		this.slotType = slotType;
	}

	public int getDayNum() {
		return dayNum;
	}

	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public List<String> getSpeakersId() {
		return speakersId;
	}

	public void setSpeakersId(List<String> speakersId) {
		this.speakersId = speakersId;
	}

}