package com.blackleaf.webcrawler.dao;

import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.LinkRelation;

public interface LinkDao {
	public Long insertLink(Link link);

	public Long insertLinkRelation(LinkRelation relation);

	public Link getCrawlableLink();

	public int updateLockFlag(long linkId, int preFlag, int currFlag);
}
