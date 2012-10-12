package com.blackleaf.webcrawler.processor;

import com.blackleaf.webcrawler.core.CrawlerContext;

public class NoneExceptionProcessor extends InvocationCollection<CrawlerContext> {

	@Override
	public boolean invoke(CrawlerContext processBean) {
		try {
			super.invoke(processBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

}
