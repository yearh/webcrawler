package com.blackleaf.webcrawler.domain;

public class LinkRecord {
	private long linkId;
	private long inLinkId;
	private String link;
	private String inLink;
	private boolean isWsdl;

	public long getLinkId() {
		return linkId;
	}

	public void setLinkId(long linkId) {
		this.linkId = linkId;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public long getInLinkId() {
		return inLinkId;
	}

	public void setInLinkId(long inLinkId) {
		this.inLinkId = inLinkId;
	}

	public String getInLink() {
		return inLink;
	}

	public void setInLink(String inLink) {
		this.inLink = inLink;
	}

	public boolean isWsdl() {
		return isWsdl;
	}

	public void setWsdl(boolean isWsdl) {
		this.isWsdl = isWsdl;
	}

}
