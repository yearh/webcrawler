package com.blackleaf.webcrawler.processor.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.Page;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;
import com.blackleaf.webcrawler.vo.PageBean;

public class RetrievePageProcessor implements InvocationProcessor<CrawlerContext> {
	private LinkService linkService;

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public boolean invoke(CrawlerContext context) {
		Link currLink = null;
		List<PageBean> pageBeanList = context.getPageBeanList();

		// 逐个爬取链接内容，记录爬取错误的链接
		for (PageBean pageBean : pageBeanList) {
			currLink = pageBean.getPageLink();
			Page page = new Page();
			try {
				page.setContent(retrieveContents(currLink.getUrl()));
				page.setUrl(currLink.getUrl());
				pageBean.setPage(page);
			} catch (IOException e) {
				context.getErrorLinks().add(currLink);
				pageBean.getPageLink().setStatus(Link.LINK_STATUS_ERROR);
			}
		}

		return true;
	}

	private String retrieveContents(String url) throws IOException {
		URL pageURL = new URL(url);
		StringBuffer sb = new StringBuffer();
		String s;
		BufferedReader br = new BufferedReader(new InputStreamReader(pageURL.openStream()));
		while ((s = br.readLine()) != null) {
			sb.append(s);
		}

		br.close();
		return sb.toString();

	}
}
