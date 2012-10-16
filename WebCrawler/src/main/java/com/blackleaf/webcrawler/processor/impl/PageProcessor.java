package com.blackleaf.webcrawler.processor.impl;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.Page;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.PageService;
import com.blackleaf.webcrawler.vo.PageBean;

public class PageProcessor implements InvocationProcessor<CrawlerContext> {
	private PageService pageService;

	public boolean invoke(CrawlerContext context) {

		for (PageBean pb : context.getPageBeanList()) {
			if (pb.getPageLink().getStatus() != Link.LINK_STATUS_INIT)
				continue;
			Page page = pb.getPage();
			int pageType = getPageType(page.getContent());
			page.setType(pageType);
			pageService.insertPage(page);
		}

		return true;
	}

	private int getPageType(String pageContent) {
		String wsdlPattern = "<wsdl:definitions";
		if (pageContent.indexOf(wsdlPattern) == -1)
			return Page.PAGE_TYPE_NORMAL;
		else
			return Page.PAGE_TYPE_WSDL;
	}

	public PageService getPageService() {
		return pageService;
	}

	public void setPageService(PageService pageService) {
		this.pageService = pageService;
	}

}
