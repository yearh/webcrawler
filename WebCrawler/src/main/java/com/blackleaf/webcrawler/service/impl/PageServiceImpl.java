package com.blackleaf.webcrawler.service.impl;

import java.util.List;

import com.blackleaf.webcrawler.dao.PageDao;
import com.blackleaf.webcrawler.domain.Page;
import com.blackleaf.webcrawler.service.PageService;

public class PageServiceImpl implements PageService {

	private PageDao pageDao;

	public long insertPage(Page page) {
		return pageDao.insertPage(page);
	}

	public long insertPages(List<Page> pages) {
		return pageDao.insertPages(pages);
	}
	
	public PageDao getPageDao() {
		return pageDao;
	}

	public void setPageDao(PageDao pageDao) {
		this.pageDao = pageDao;
	}

}
