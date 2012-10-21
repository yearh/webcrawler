package com.blackleaf.webcrawler.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.blackleaf.webcrawler.dao.PageDao;
import com.blackleaf.webcrawler.domain.Page;

public class PageDaoImpl extends SqlMapClientDaoSupport implements PageDao {
	@Override
	public long insertPage(Page page) {
		return (Long) getSqlMapClientTemplate().insert("insertPage", page);
	}

	@Override
	public long insertPages(List<Page> pages) {
		return (Long) getSqlMapClientTemplate().insert("insertPages", pages);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Page> getPagesByType(int type, int maxSize) {
		Map<String, Integer> paramMap = new HashMap<String, Integer>();
		paramMap.put("type", type);
		paramMap.put("maxSize", maxSize);
		return (List<Page>) getSqlMapClientTemplate().queryForList("getPagesByType", paramMap);
	}

	@Override
	public Page getPageByLinkId(long linkId) {
		return (Page) getSqlMapClientTemplate().queryForObject("getPageByLinkId", linkId);
	}
}
