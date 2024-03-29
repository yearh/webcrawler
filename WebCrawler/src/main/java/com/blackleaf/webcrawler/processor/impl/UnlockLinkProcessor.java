package com.blackleaf.webcrawler.processor.impl;

import java.util.ArrayList;
import java.util.List;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;
import com.blackleaf.webcrawler.vo.PageBean;

public class UnlockLinkProcessor implements InvocationProcessor<CrawlerContext> {
	private LinkService linkService;

	public boolean invoke(CrawlerContext context) {
		if (context.isLinkLockFlag()) {
			// 获取待解锁链接id
			List<Long> linkIds = new ArrayList<Long>();
			for (PageBean pb : context.getPageBeanList())
				linkIds.add(pb.getPageLink().getId());

			// 解锁链接
			linkService.unlockLinks(linkIds);
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
