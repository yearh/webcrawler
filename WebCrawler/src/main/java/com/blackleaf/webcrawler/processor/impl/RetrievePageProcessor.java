package com.blackleaf.webcrawler.processor.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.CrawlerError;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.Page;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;

public class RetrievePageProcessor implements InvocationProcessor<CrawlerContext> {
	private LinkService linkService;

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

	public boolean invoke(CrawlerContext context) {
		boolean result = false;
		Link link = context.getCurrLink();

		try {
			Page page = new Page();
			page.setContent(retrieveContents(link.getUrl()));
			page.setUrl(link.getUrl());
			context.setCurrPage(page);

			result = true;
		} catch (IOException e) {
			e.printStackTrace();
			context.setError(new CrawlerError(CrawlerError.ERR_RETRIEVE_PAGE, "retrieve page error, linkId=" + link.getId() + "url=" + link.getUrl()));
			result = false;
		}

		return result;
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
