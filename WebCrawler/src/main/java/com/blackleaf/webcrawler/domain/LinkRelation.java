package com.blackleaf.webcrawler.domain;

import java.util.Date;

public class LinkRelation {
	private long id;
	private long fromLinkId;
	private long toLinkId;
	private Date createTime;

	public LinkRelation(long fromLinkId, long toLinkId) {
		this.fromLinkId = fromLinkId;
		this.toLinkId = toLinkId;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getFromLinkId() {
		return fromLinkId;
	}

	public void setFromLinkId(long fromLinkId) {
		this.fromLinkId = fromLinkId;
	}

	public long getToLinkId() {
		return toLinkId;
	}

	public void setToLinkId(long toLinkId) {
		this.toLinkId = toLinkId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
