package com.blackleaf.webcrawler.service.impl;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.processor.ExceptionalProcessor;
import com.blackleaf.webcrawler.processor.NoneExceptionProcessor;
import com.blackleaf.webcrawler.service.CrawlService;

public class CrawlServiceImpl implements CrawlService {
	private ExceptionalProcessor preProcessor;
	private ExceptionalProcessor bizProcessor;
	private NoneExceptionProcessor postProcessor;
	private NoneExceptionProcessor rollbackProcessor;

	public ExceptionalProcessor getPreProcessor() {
		return preProcessor;
	}

	public void setPreProcessor(ExceptionalProcessor preProcessor) {
		this.preProcessor = preProcessor;
	}

	public ExceptionalProcessor getBizProcessor() {
		return bizProcessor;
	}

	public void setBizProcessor(ExceptionalProcessor bizProcessor) {
		this.bizProcessor = bizProcessor;
	}

	public NoneExceptionProcessor getPostProcessor() {
		return postProcessor;
	}

	public void setPostProcessor(NoneExceptionProcessor postProcessor) {
		this.postProcessor = postProcessor;
	}

	public NoneExceptionProcessor getRollbackProcessor() {
		return rollbackProcessor;
	}

	public void setRollbackProcessor(NoneExceptionProcessor rollbackProcessor) {
		this.rollbackProcessor = rollbackProcessor;
	}

	public void crawl() {
		CrawlerContext context = new CrawlerContext();
		try {
			preProcessor.invoke(context);
			bizProcessor.invoke(context);
			postProcessor.invoke(context);
		} catch (Exception e) {
			e.printStackTrace();
			rollbackProcessor.invoke(context);
		}
	}
}
