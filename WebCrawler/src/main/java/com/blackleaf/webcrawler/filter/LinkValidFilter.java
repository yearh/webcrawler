package com.blackleaf.webcrawler.filter;

import java.net.URL;

public class LinkValidFilter extends BaseLinkFilter {

	@Override
	public boolean doFilter(String url) {
		if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
			return false;

		try {
			URL verifiedUrl = new URL(url);
			return getNextFilter().doFilter(url);
		} catch (Exception e) {
			System.out.println("invalid link: " + url);
			return false;
		}
	}

	@Override
	public String getFilterName() {
		return VALID_FILTER;
	}

}
