package com.blackleaf.webcrawler.processor.impl;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.CrawlerError;
import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;

public class GetAvailLinkProcessor implements InvocationProcessor<CrawlerContext> {

	private LinkService linkService;

	public boolean invoke(CrawlerContext context) {
		try {
			Link currLink = linkService.getCrawlableLink();
			if (currLink == null) {
				context.setError(new CrawlerError(CrawlerError.ERR_GET_AVAIL_LINK, "no available link"));
				return false;
			}
			context.setCurrLink(currLink);
		} catch (CrawlerException e) {
			context.setError(new CrawlerError(CrawlerError.ERR_GET_AVAIL_LINK, "get available link error"));
			return false;
		}
		return true;
	}

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

}
