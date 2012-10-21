package com.blackleaf.webcrawler.service;

import java.util.List;

import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.LinkRelation;

public interface LinkService {

	public List<Link> getCrawlableLink(int size);

	public void insertLink(Link link);
	
	public void insertLinks(List<Link> links);

	public int updateLink(Link link);

	public int updateLinks(List<Link> links);

	public void insertLinkRelation(LinkRelation relation);

	public void insertLinkRelations(List<LinkRelation> relations);

	public void lockLink(long linkId) throws CrawlerException;

	public void lockLinks(List<Long> linkIds) throws CrawlerException;

	public void unlockLink(long linkId) throws CrawlerException;

	public void unlockLinks(List<Long> linkIds) throws CrawlerException;
	
	public Link getFromLinkByToLinkId(long toLinkId);
}
