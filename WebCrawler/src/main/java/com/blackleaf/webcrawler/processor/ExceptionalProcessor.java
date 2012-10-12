package com.blackleaf.webcrawler.processor;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.CrawlerError;
import com.blackleaf.webcrawler.core.CrawlerException;

public class ExceptionalProcessor extends InvocationCollection<CrawlerContext> {

	@Override
	public boolean invoke(CrawlerContext context) {
		boolean result = true;
		for (InvocationProcessor<CrawlerContext> proc : getProcessors()) {
			try {
				result = proc.invoke(context);
				if (result == false) {
					if (context.getError() == null) {
						context.setError(new CrawlerError(CrawlerError.ERR_PROCESSOR_FALSE_UNKNOWN, "processor return false, processor = " + proc.toString()));
					}
					throw new CrawlerException("ExceptionalProcessor return false, processor = " + proc);
				}
			} catch (Exception e) {
				result = false;
				context.setError(new CrawlerError(CrawlerError.ERR_PROCESSOR_UNKNOWN, "processor error, processor = " + proc));
				throw new CrawlerException("ExceptionalProcessor error, processor = " + proc, e);
			}
		}

		return result;
	}
}
