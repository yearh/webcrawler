package com.blackleaf.webcrawler.filter;

import java.net.URL;

import com.blackleaf.webcrawler.interfaces.LinkFilter;

public class LinkValidFilter implements LinkFilter {

	public boolean doFilter(String url) {
		if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
			return false;

		try {
			URL verifiedUrl = new URL(url);
			return true;
		} catch (Exception e) {
			System.out.println("invalid link: " + url);
			return false;
		}
	}

}
