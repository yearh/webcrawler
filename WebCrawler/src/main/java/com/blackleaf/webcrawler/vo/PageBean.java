package com.blackleaf.webcrawler.vo;

import java.util.ArrayList;
import java.util.List;

import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.Page;

public class PageBean {
	private Link pageLink;
	private Page page;
	private List<Link> childLink = new ArrayList<Link>();

	public PageBean(Link link) {
		pageLink = link;
	}

	public Link getPageLink() {
		return pageLink;
	}

	public void setPageLink(Link pageLink) {
		this.pageLink = pageLink;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<Link> getChildLink() {
		return childLink;
	}

	public void setChildLink(List<Link> childLink) {
		this.childLink = childLink;
	}

}
