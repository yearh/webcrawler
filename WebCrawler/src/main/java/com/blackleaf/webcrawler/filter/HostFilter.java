package com.blackleaf.webcrawler.filter;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import com.blackleaf.webcrawler.interfaces.LinkFilter;

public class HostFilter implements LinkFilter {
	private List<String> allowedHost;

	public boolean doFilter(String link) {
		try {
			URL url = new URL(link);
			for (Iterator<String> it = allowedHost.iterator(); it.hasNext();) {
				String allow = it.next();
				if (url.getHost().indexOf(allow) != -1) {
					return true;
				}
			}
			return false;
		} catch (MalformedURLException e) {
			return false;
		}
	}

	public List<String> getAllowedHost() {
		return allowedHost;
	}

	public void setAllowedHost(List<String> allowedHost) {
		this.allowedHost = allowedHost;
	}

}
