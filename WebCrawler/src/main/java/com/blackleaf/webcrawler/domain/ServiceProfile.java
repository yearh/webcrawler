package com.blackleaf.webcrawler.domain;

public class ServiceProfile {
	private int id;
	private String name;
	private String description;
	private float availability;
	private float rating;
	private long linkId;
	private long profileLinkId;
	private String provider;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public long getLinkId() {
		return linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public long getProfileLinkId() {
		return profileLinkId;
	}

	public void setProfileLinkId(long profileLinkId) {
		this.profileLinkId = profileLinkId;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

}
