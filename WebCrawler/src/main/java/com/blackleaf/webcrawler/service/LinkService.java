package com.blackleaf.webcrawler.service;

import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.LinkRelation;

public interface LinkService {

	public Link getCrawlableLink();

	public void insertLink(Link link);
	
	public int updateLink(Link link);

	public void insertLinkRelation(LinkRelation relation);

	public void lockLink(long linkId) throws CrawlerException;
	
	public void unlockLink(long linkId) throws CrawlerException;
}
