package com.blackleaf.webcrawler.processor.impl;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;

public class UnlockLinkProcessor implements InvocationProcessor<CrawlerContext> {
	private LinkService linkService;

	public boolean invoke(CrawlerContext context) {
		if (context.isLinkLockFlag())
			linkService.unlockLink(context.getCurrLink().getId());
		return true;
	}

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

}
