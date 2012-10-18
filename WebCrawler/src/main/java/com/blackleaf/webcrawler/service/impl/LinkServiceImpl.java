package com.blackleaf.webcrawler.service.impl;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.UncategorizedSQLException;

import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.dao.LinkDao;
import com.blackleaf.webcrawler.domain.LinkRelation;
import com.blackleaf.webcrawler.service.LinkService;

public class LinkServiceImpl implements LinkService {

	private LinkDao linkDao;

	public List<Link> getCrawlableLink(int size) {
		return linkDao.getCrawlableLink(size);
	}

	/*
	 * 插入链接，链接url有唯一索引，并发情况下会抛唯一索引冲突，若抛错，则直接设置连接id为已存在链接id
	 * 
	 * @see
	 * com.blackleaf.webcrawler.service.LinkService#insertLink(com.blackleaf
	 * .webcrawler.core.Link)
	 */
	public void insertLink(Link link) {
		try {
			linkDao.insertLink(link);
		} catch (DataIntegrityViolationException e) {
			Link existLink = linkDao.getLinkByUrl(link.getUrl());
			link.setId(existLink.getId());
		}
	}

	public void insertLinkRelation(LinkRelation relation) {
		linkDao.insertLinkRelation(relation);
	}

	public void lockLink(long linkId) throws CrawlerException {
		int result = linkDao.updateLockFlag(linkId, Link.LINK_UNLOCK, Link.LINK_LOCKED);
		if (result == 0)
			throw new CrawlerException("lock link failed, link_id=" + linkId);
	}

	public void lockLinks(List<Long> linkIds) throws CrawlerException {
		int result = linkDao.updateLockFlags(linkIds, Link.LINK_UNLOCK, Link.LINK_LOCKED);
		if (result == 0)
			throw new CrawlerException("lock links failed, link size=" + linkIds.size() + ", link ids=(" + linkIds.get(0).longValue() + "..."
					+ linkIds.get(linkIds.size() - 1).longValue() + ")");
	}

	public void unlockLink(long linkId) throws CrawlerException {
		int result = linkDao.updateLockFlag(linkId, Link.LINK_LOCKED, Link.LINK_UNLOCK);
		if (result == 0)
			throw new CrawlerException("unlock link failed, link_id=" + linkId);
	}

	public void unlockLinks(List<Long> linkIds) throws CrawlerException {
		if (linkIds.size() == 0)
			return;
		int result = linkDao.updateLockFlags(linkIds, Link.LINK_LOCKED, Link.LINK_UNLOCK);
		if (result == 0)
			throw new CrawlerException("unlock links failed, link size=" + linkIds.size() + ", link ids=(" + linkIds.get(0).longValue() + "..."
					+ linkIds.get(linkIds.size() - 1).longValue() + ")");
	}

	public int updateLink(Link link) {
		return linkDao.updateLink(link);
	}

	public LinkDao getLinkDao() {
		return linkDao;
	}

	public void setLinkDao(LinkDao linkDao) {
		this.linkDao = linkDao;
	}

	public void insertLinks(List<Link> links) {
		for (Link link : links) {
			try {
				linkDao.insertLink(link);
			} catch (DataIntegrityViolationException e) {
				Link existLink = linkDao.getLinkByUrl(link.getUrl());
				if (existLink != null)
					link.setId(existLink.getId());
			} catch (UncategorizedSQLException se) {
				se.printStackTrace();
			}
		}
	}

	public int updateLinks(List<Link> links) {
		int result = 0;
		for (Link link : links)
			result += linkDao.updateLink(link);
		return result;
	}

	public void insertLinkRelations(List<LinkRelation> relations) {
		if (relations.size() != 0)
			linkDao.insertLinkRelations(relations);
	}

}
