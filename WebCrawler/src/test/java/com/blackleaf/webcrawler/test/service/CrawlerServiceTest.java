package com.blackleaf.webcrawler.test.service;

import com.blackleaf.webcrawler.service.CrawlService;
import com.blackleaf.webcrawler.test.SpringBaseTest;

public class CrawlerServiceTest extends SpringBaseTest{
	private CrawlService crawlService;

	public CrawlService getCrawlService() {
		return crawlService;
	}

	public void setCrawlService(CrawlService crawlService) {
		this.crawlService = crawlService;
	}
	
	public void testCrawl1(){
		crawlService.crawl();
	}

}
