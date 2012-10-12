package com.blackleaf.webcrawler.core;

public class CrawlerException extends RuntimeException {

	private static final long serialVersionUID = -7440521370599379734L;

	public CrawlerException(String msg) {
		super(msg);
	}

	public CrawlerException(String msg, Throwable e) {
		super(msg, e);
	}
}
