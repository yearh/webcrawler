package com.blackleaf.webcrawler.filter;

import com.blackleaf.webcrawler.exception.FilterException;
import com.blackleaf.webcrawler.interfaces.LinkFilter;

public abstract class BaseLinkFilter implements LinkFilter {
	public final static String HOST_FILTER = "Host Filter";
	public final static String ROBOT_FILTER = "Robot Filter";
	public final static String VALID_FILTER = "Valid Filter";

	protected LinkFilter nextFilter;

	public abstract boolean doFilter(String url);

	public LinkFilter getNextFilter() {
		return nextFilter;
	}

	public void setNextFilter(LinkFilter filter) {
		nextFilter = filter;
	}

	public abstract String getFilterName();
}
