package com.blackleaf.webcrawler.core;

import java.util.ArrayList;
import java.util.List;

import com.blackleaf.webcrawler.vo.PageBean;

public class CrawlerContext {
	private CrawlerError error;
	private List<PageBean> pageBeanList = new ArrayList<PageBean>();
	private List<Link> errorLinks = new ArrayList<Link>();
	private boolean linkLockFlag = false;

	public CrawlerContext() {
		super();
	}

	public CrawlerContext(List<Link> crawlLink) {
		for (Link link : crawlLink)
			pageBeanList.add(new PageBean(link));
	}

	public void addCrawlLinks(List<Link> linkList) {
		for (Link link : linkList)
			pageBeanList.add(new PageBean(link));
	}

	public CrawlerError getError() {
		return error;
	}

	public void setError(CrawlerError error) {
		this.error = error;
	}

	public boolean isLinkLockFlag() {
		return linkLockFlag;
	}

	public void setLinkLockFlag(boolean linkLockFlag) {
		this.linkLockFlag = linkLockFlag;
	}

	public List<PageBean> getPageBeanList() {
		return pageBeanList;
	}

	public List<Link> getErrorLinks() {
		return errorLinks;
	}

}
