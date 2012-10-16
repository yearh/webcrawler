package com.blackleaf.webcrawler.core;

import java.util.Date;

public class Link {
	public final static int LINK_LOCKED = 1;
	public final static int LINK_UNLOCK = 0;
	public final static int LINK_STATUS_INIT = 0;
	public final static int LINK_STATUS_CRAWLED = 1;
	public final static int LINK_STATUS_ERROR = 2;

	private long id;
	private long pageId = -1;
	private String url;
	private int lockFlag = LINK_UNLOCK;
	private int status;
	private Date updateTime;
	private Date createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getLockFlag() {
		return lockFlag;
	}

	public void setLockFlag(int lockFlag) {
		this.lockFlag = lockFlag;
	}

	public long getPageId() {
		return pageId;
	}

	public void setPageId(long pageId) {
		this.pageId = pageId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String toString() {
		return getUrl();
	}

}
