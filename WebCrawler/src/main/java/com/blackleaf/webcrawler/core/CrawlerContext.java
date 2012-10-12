package com.blackleaf.webcrawler.core;

import com.blackleaf.webcrawler.domain.Page;

public class CrawlerContext {
	private Page currPage;
	private Link currLink;
	private CrawlerError error;
	private boolean linkLockFlag = false;

	public Page getCurrPage() {
		return currPage;
	}

	public void setCurrPage(Page currPage) {
		this.currPage = currPage;
	}

	public CrawlerError getError() {
		return error;
	}

	public void setError(CrawlerError error) {
		this.error = error;
	}

	public Link getCurrLink() {
		return currLink;
	}

	public void setCurrLink(Link currLink) {
		this.currLink = currLink;
	}

	public boolean isLinkLockFlag() {
		return linkLockFlag;
	}

	public void setLinkLockFlag(boolean linkLockFlag) {
		this.linkLockFlag = linkLockFlag;
	}
}
