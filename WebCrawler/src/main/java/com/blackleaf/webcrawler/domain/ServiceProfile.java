package com.blackleaf.webcrawler.domain;

public class ServiceProfile {
	private int id;
	private String name;
	private String content;
	private float availability;
	private float rating;
	private int linkId;
	private int profileLinkId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public float getAvailability() {
		return availability;
	}

	public void setAvailability(float availability) {
		this.availability = availability;
	}

	public float getRating() {
		return rating;
	}

	public void setRating(float rating) {
		this.rating = rating;
	}

	public int getLinkId() {
		return linkId;
	}

	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}

	public int getProfileLinkId() {
		return profileLinkId;
	}

	public void setProfileLinkId(int profileLinkId) {
		this.profileLinkId = profileLinkId;
	}

}
