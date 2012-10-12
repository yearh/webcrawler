package com.blackleaf.webcrawler.processor.impl;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.CrawlerError;
import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;

public class LockLinkProcessor implements InvocationProcessor<CrawlerContext> {

	private LinkService linkService;

	public boolean invoke(CrawlerContext context) {
		try {
			linkService.lockLink(context.getCurrLink().getId());
			context.setLinkLockFlag(true);
		} catch (CrawlerException e) {
			context.setError(new CrawlerError(CrawlerError.ERR_LOCK_LINK, "lock link error, linkId=" + context.getCurrLink().getId()));
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
