package com.blackleaf.webcrawler.service;

import java.util.List;

import com.blackleaf.webcrawler.domain.Page;

public interface PageService {
	public long insertPage(Page page);

	public long insertPages(List<Page> pages);
}
