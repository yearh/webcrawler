package com.blackleaf.webcrawler.interfaces;


public interface LinkFilter {
	public boolean doFilter(String url);

	public LinkFilter getNextFilter();

	public void setNextFilter(LinkFilter filter);
	
	public String getFilterName();
}
