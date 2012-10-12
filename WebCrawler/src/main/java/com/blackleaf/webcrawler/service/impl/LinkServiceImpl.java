package com.blackleaf.webcrawler.service.impl;

import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.dao.LinkDao;
import com.blackleaf.webcrawler.domain.LinkRelation;
import com.blackleaf.webcrawler.service.LinkService;

public class LinkServiceImpl implements LinkService {

	private LinkDao linkDao;

	public Link getCrawlableLink() {
		return linkDao.getCrawlableLink();
	}

	public void insertLink(Link link) {
		linkDao.insertLink(link);
	}

	public void insertLinkRelation(LinkRelation relation) {
		linkDao.insertLinkRelation(relation);
	}

	public LinkDao getLinkDao() {
		return linkDao;
	}

	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}

	public void lockLink(long linkId) throws CrawlerException {
		int result = linkDao.updateLockFlag(linkId, Link.LINK_UNLOCK, Link.LINK_LOCKED);
		if (result == 0)
			throw new CrawlerException("lock link failed, link_id=" + linkId);
	}

	public void unlockLink(long linkId) throws CrawlerException {
		int result = linkDao.updateLockFlag(linkId, Link.LINK_LOCKED, Link.LINK_UNLOCK);
		if (result == 0)
			throw new CrawlerException("unlock link failed, link_id=" + linkId);
	}

}
