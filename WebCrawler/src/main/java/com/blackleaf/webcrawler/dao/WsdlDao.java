package com.blackleaf.webcrawler.dao;

import java.util.List;

import com.blackleaf.webcrawler.domain.ServiceProfile;

public interface WsdlDao {
	public void insertWsdl(ServiceProfile profile);

	public void insertWsdls(List<ServiceProfile> profiles);
}
