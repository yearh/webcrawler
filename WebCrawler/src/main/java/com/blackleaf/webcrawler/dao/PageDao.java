package com.blackleaf.webcrawler.dao;

import java.util.List;

import com.blackleaf.webcrawler.domain.Page;

public interface PageDao {
	public long insertPage(Page page);

	public long insertPages(List<Page> pages);
}
