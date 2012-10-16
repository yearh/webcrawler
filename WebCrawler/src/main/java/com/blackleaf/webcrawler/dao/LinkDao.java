package com.blackleaf.webcrawler.dao;

import java.util.List;

import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.LinkRelation;

public interface LinkDao {
	public Long insertLink(Link link);

	public Long insertLinks(List<Link> links);

	public Long insertLinkRelation(LinkRelation relation);

	public Long insertLinkRelations(List<LinkRelation> relations);

	public List<Link> getCrawlableLink(int size);

	/**
	 * 更新链接锁定标记
	 * 
	 * @param linkId
	 * @param preFlag
	 * @param currFlag
	 * @return
	 */
	public int updateLockFlag(long linkId, int preFlag, int currFlag);

	/**
	 * 批量更新链接锁定标记
	 * 
	 * @param linkIds
	 * @param preFlag
	 * @param currFlag
	 * @return
	 */
	public int updateLockFlags(List<Long> linkIds, int preFlag, int currFlag);

	public int updateLink(Link link);

	public Link getLinkByUrl(String url);
}
