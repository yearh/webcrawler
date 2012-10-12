package com.blackleaf.webcrawler.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.dao.LinkDao;
import com.blackleaf.webcrawler.domain.LinkRelation;

public class LinkDaoImpl extends SqlMapClientDaoSupport implements LinkDao {

	public Long insertLink(Link link) {
		return (Long) getSqlMapClientTemplate().insert("insertLink", link);
	}

	public Long insertLinkRelation(LinkRelation relation) {
		return (Long) getSqlMapClientTemplate().insert("insertLinkRelation", relation);
	}

	public Link getCrawlableLink() {
		return (Link) getSqlMapClientTemplate().insert("getCrawlableLink");
	}

	public int lockLink(long linkId) {
		return getSqlMapClientTemplate().update("lockLink");
	}

	public int updateLockFlag(long linkId, int preFlag, int currFlag) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("linkId", linkId);
		param.put("preFlag", preFlag);
		param.put("currFlag", currFlag);
		return getSqlMapClientTemplate().update("updateLockFlag", param);
	}

}
