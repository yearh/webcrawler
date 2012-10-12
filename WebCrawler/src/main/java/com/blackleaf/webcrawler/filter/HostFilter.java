package com.blackleaf.webcrawler.filter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

public class HostFilter extends BaseLinkFilter {
	private List<String> allowedHost;

	public HostFilter(List<String> allowedHost) {
		this.allowedHost = allowedHost;
	}

	@Override
	public boolean doFilter(String link) {
		try {
			URL url = new URL(link);
			for (Iterator<String> it = allowedHost.iterator(); it.hasNext();) {
				String allow = it.next();
				if (allow.equals(url.getHost())) {
					if (nextFilter != null)
						return getNextFilter().doFilter(link);
					else
						return true;
				}
			}
			return false;
		} catch (MalformedURLException e) {
			System.out.println("invalid link: " + link);
			return false;
		}
	}

	@Override
	public String getFilterName() {
		return HOST_FILTER;
	}
}
