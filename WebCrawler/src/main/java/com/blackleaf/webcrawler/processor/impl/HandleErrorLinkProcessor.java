package com.blackleaf.webcrawler.processor.impl;

import java.util.List;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;

public class HandleErrorLinkProcessor implements InvocationProcessor<CrawlerContext> {

	private LinkService linkService;

	public boolean invoke(CrawlerContext context) {
		List<Link> errorLinks = context.getErrorLinks();
		for (Link link : errorLinks) {
			link.setStatus(Link.LINK_STATUS_ERROR);
		}
		linkService.updateLinks(errorLinks);
		return true;
	}

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

}
