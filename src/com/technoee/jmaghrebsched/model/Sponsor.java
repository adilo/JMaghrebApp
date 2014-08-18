package com.technoee.jmaghrebsched.model;

import com.google.api.client.util.Key;

public class Sponsor {

	@Key
	private String name;
	@Key
	private String img;
	@Key
	private String link;
	@Key
	private String imgRes;
	
	private String section;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public String getImgRes() {
		return imgRes;
	}

	public void setImgRes(String imgRes) {
		this.imgRes = imgRes;
	}

}
