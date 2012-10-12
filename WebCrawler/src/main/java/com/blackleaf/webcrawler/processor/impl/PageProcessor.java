package com.blackleaf.webcrawler.processor.impl;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.domain.Page;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.PageService;

public class PageProcessor implements InvocationProcessor<CrawlerContext> {
	private PageService pageService;

	public boolean invoke(CrawlerContext context) {
		Page currPage = context.getCurrPage();
		int pageType = getPageType(currPage.getContent());
		currPage.setType(pageType);

		pageService.insertPage(currPage);

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
