package com.blackleaf.webcrawler.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.blackleaf.webcrawler.dao.PageDao;
import com.blackleaf.webcrawler.domain.Page;

public class PageDaoImpl extends SqlMapClientDaoSupport implements PageDao {
	public long insertPage(Page page) {
		return (Long) getSqlMapClientTemplate().insert("insertPage", page);
	}

	public long insertPages(List<Page> pages) {
		return (Long) getSqlMapClientTemplate().insert("insertPages", pages);
	}
}
