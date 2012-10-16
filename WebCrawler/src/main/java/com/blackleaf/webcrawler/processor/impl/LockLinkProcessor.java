package com.blackleaf.webcrawler.processor.impl;

import java.util.ArrayList;
import java.util.List;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.CrawlerError;
import com.blackleaf.webcrawler.core.CrawlerException;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;
import com.blackleaf.webcrawler.vo.PageBean;

public class LockLinkProcessor implements InvocationProcessor<CrawlerContext> {

	private LinkService linkService;

	public boolean invoke(CrawlerContext context) {
		List<Long> linkIds = new ArrayList<Long>();
		try {
			for (PageBean pb : context.getPageBeanList()) {
				linkIds.add(pb.getPageLink().getId());
			}
			linkService.lockLinks(linkIds);
			context.setLinkLockFlag(true);
		} catch (CrawlerException e) {
			e.printStackTrace();
			context.setError(new CrawlerError(CrawlerError.ERR_LOCK_LINK, "lock links error, link size=" + linkIds.size() + ", linkId=(" + linkIds.get(0).longValue() + "..."
					+ linkIds.get(linkIds.size() - 1) + ")"));
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
