package com.blackleaf.webcrawler.dao.impl;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.blackleaf.webcrawler.dao.PageDao;
import com.blackleaf.webcrawler.domain.Page;

public class PageDaoImpl extends SqlMapClientDaoSupport implements PageDao {
	public long insertPage(Page page) {
		return (Long) getSqlMapClientTemplate().insert("insertPage", page);
	}
}
