package com.blackleaf.webcrawler.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.dao.WsdlDao;
import com.blackleaf.webcrawler.domain.Page;
import com.blackleaf.webcrawler.domain.ServiceProfile;
import com.blackleaf.webcrawler.service.LinkService;
import com.blackleaf.webcrawler.service.PageService;

public class WsdlServiceImpl {
	private PageService pageService;
	private LinkService linkService;
	private WsdlDao wsdlDao;
	private int sizePerTime;

	public void getWsdls() {
		List<ServiceProfile> profiles = new ArrayList<ServiceProfile>();
		List<Page> pageList = pageService.getPagesByType(Page.PAGE_TYPE_WSDL, sizePerTime);
		for (Page wsdlPage : pageList) {
			// 获取描述wsdl服务端页面
			Link fromLink = linkService.getFromLinkByToLinkId(wsdlPage.getLinkId());
			Page descrptPage = pageService.getPageByLinkId(fromLink.getId());

			// 分析wsdl描述
			ServiceProfile profile = analyzeServiceByPage(wsdlPage.getContent(), descrptPage.getContent());

			// 设置wsdl与链接关联
			profile.setLinkId(wsdlPage.getLinkId());
			profile.setProfileLinkId(fromLink.getId());

			profiles.add(profile);
		}

		wsdlDao.insertWsdls(profiles);
	}

	private ServiceProfile analyzeServiceByPage(String wsdl, String desc) {
		ServiceProfile profile = new ServiceProfile();
		profile.setAvailability(1);
		profile.setProvider("test provider");
		profile.setName("test");
		profile.setRating(0);

		return profile;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

	public int getSizePerTime() {
		return sizePerTime;
	}

	public void setSizePerTime(int sizePerTime) {
		this.sizePerTime = sizePerTime;
	}

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public WsdlDao getWsdlDao() {
		return wsdlDao;
	}

	public void setWsdlDao(WsdlDao wsdlDao) {
		this.wsdlDao = wsdlDao;
	}

}
