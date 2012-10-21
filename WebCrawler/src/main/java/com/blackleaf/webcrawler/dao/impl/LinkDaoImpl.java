package com.blackleaf.webcrawler.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.dao.LinkDao;
import com.blackleaf.webcrawler.domain.LinkRelation;

public class LinkDaoImpl extends SqlMapClientDaoSupport implements LinkDao {

	public Long insertLink(Link link) {
		return (Long) getSqlMapClientTemplate().insert("insertLink", link);
	}

	public Long insertLinks(List<Link> links) {
		return (Long) getSqlMapClientTemplate().insert("insertLinks", links);
	}

	public Long insertLinkRelation(LinkRelation relation) {
		return (Long) getSqlMapClientTemplate().insert("insertLinkRelation", relation);
	}

	public Long insertLinkRelations(List<LinkRelation> relations) {
		return (Long) getSqlMapClientTemplate().insert("insertLinkRelations", relations);
	}

	@SuppressWarnings("unchecked")
	public List<Link> getCrawlableLink(int size) {
		return (List<Link>) getSqlMapClientTemplate().queryForList("getCrawlableLink", size);
	}

	public int updateLockFlag(long linkId, int preFlag, int currFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("linkId", linkId);
		param.put("preFlag", preFlag);
		param.put("currFlag", currFlag);
		return getSqlMapClientTemplate().update("updateLockFlag", param);
	}

	public int updateLockFlags(List<Long> linkIds, int preFlag, int currFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("linkIds", linkIds);
		param.put("preFlag", preFlag);
		param.put("currFlag", currFlag);
		return getSqlMapClientTemplate().update("updateLockFlags", param);
	}

	public Link getFromLinkByToLinkId(long toLinkId) {
		return (Link) getSqlMapClientTemplate().queryForObject("getFromLinkByToLinkId", toLinkId);
	}

	public int updateLink(Link link) {
		return getSqlMapClientTemplate().update("updateLink", link);
	}

	public Link getLinkByUrl(String url) {
		return (Link) getSqlMapClientTemplate().queryForObject("getLinkByUrl", url);
	}

}
