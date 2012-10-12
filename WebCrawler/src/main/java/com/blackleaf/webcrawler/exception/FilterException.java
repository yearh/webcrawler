package com.blackleaf.webcrawler.exception;

import com.blackleaf.webcrawler.interfaces.LinkFilter;

public class FilterException extends Exception {
	private static final long serialVersionUID = 1L;
	private String filterName;

	public FilterException(LinkFilter filter) {
		this.filterName = filter.getFilterName();
	}

	public FilterException(LinkFilter filter, Throwable t) {
		super(t);
		this.filterName = filter.getFilterName();
	}

	public FilterException(String message, LinkFilter filter) {
		super("Filter name: " + filter.getFilterName() + "  Reason: " + message);
		this.filterName = filter.getFilterName();
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
}
