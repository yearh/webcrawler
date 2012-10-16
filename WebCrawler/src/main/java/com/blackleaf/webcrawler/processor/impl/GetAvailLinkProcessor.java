package com.blackleaf.webcrawler.processor.impl;

import java.util.List;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.CrawlerError;
import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;

public class GetAvailLinkProcessor implements InvocationProcessor<CrawlerContext> {

	private LinkService linkService;
	private int crawlSize = 5;

	public boolean invoke(CrawlerContext context) {
		try {
			List<Link> currLinks = linkService.getCrawlableLink(crawlSize);
			if (currLinks == null || currLinks.size() == 0) {
				context.setError(new CrawlerError(CrawlerError.ERR_GET_AVAIL_LINK, "no available link"));
				return false;
			}
			context.addCrawlLinks(currLinks);
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

	public int getCrawlSize() {
		return crawlSize;
	}

	public void setCrawlSize(int crawlSize) {
		this.crawlSize = crawlSize;
	}

}
