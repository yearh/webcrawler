package com.blackleaf.webcrawler.dao.impl;

import java.util.List;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.blackleaf.webcrawler.dao.WsdlDao;
import com.blackleaf.webcrawler.domain.ServiceProfile;

public class WsdlDaoImpl extends SqlMapClientDaoSupport implements WsdlDao {
	public void insertWsdl(ServiceProfile profile) {
		this.getSqlMapClientTemplate().insert("insertWsdl", profile);
	}

	public void insertWsdls(List<ServiceProfile> profiles) {
		for (ServiceProfile profile : profiles)
			this.getSqlMapClientTemplate().insert("insertWsdl", profile);
	}
}
