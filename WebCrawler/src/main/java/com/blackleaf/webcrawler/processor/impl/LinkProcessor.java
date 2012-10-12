package com.blackleaf.webcrawler.processor.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.blackleaf.webcrawler.core.CrawlerContext;
import com.blackleaf.webcrawler.core.CrawlerError;
import com.blackleaf.webcrawler.core.Link;
import com.blackleaf.webcrawler.domain.LinkRelation;
import com.blackleaf.webcrawler.domain.Page;
import com.blackleaf.webcrawler.processor.InvocationProcessor;
import com.blackleaf.webcrawler.service.LinkService;

public class LinkProcessor implements InvocationProcessor<CrawlerContext> {

	private LinkService linkService;

	public boolean invoke(CrawlerContext context) {
		boolean result = false;
		Page currPage = context.getCurrPage();
		Link currLink = context.getCurrLink();
		if (currPage.getType() == Page.PAGE_TYPE_NORMAL) {
			try {
				List<Link> linkList = retrieveLinks(currPage.getUrl(), currPage.getContent());
				currLink.setPageId(currPage.getId());
				currLink.setStatus(Link.LINK_STATUS_CRAWLED);
				saveLinks(currLink, linkList);
				result = true;
			} catch (Exception e) {
				e.printStackTrace();
				context.setError(new CrawlerError(CrawlerError.ERR_RETRIEVE_LINK, "retrieve link error, page url=" + currPage.getUrl()));
				result = false;
			}
		}
		return result;
	}

	private void saveLinks(Link parentLink, List<Link> childLinks) {
		linkService.insertLink(parentLink);
		for (Link childLink : childLinks) {
			childLink.setStatus(Link.LINK_STATUS_INIT);
			linkService.insertLink(childLink);
			linkService.insertLinkRelation(new LinkRelation(parentLink.getId(), childLink.getId()));
		}
	}

	private List<Link> retrieveLinks(String url, String pageContent) throws MalformedURLException {
		URL pageURL = new URL(url);
		Pattern p = Pattern.compile("<*\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pageContent);
		List<Link> links = new ArrayList<Link>();

		try {

			while (m.find()) {
				String link = m.group(1).trim();

				// Skip empty links.
				if (link.length() < 1) {
					continue;
				}

				// Skip links that are just page anchors.
				if (link.charAt(0) == '#') {
					continue;
				}

				// Skip mailto links.
				if (link.indexOf("mailto:") != -1) {
					continue;
				}

				// Skip javascript links
				if (link.toLowerCase().indexOf("javascript") != -1) {
					continue;
				}

				if (link.startsWith(".")) {
					link = link.substring(1);
				}

				// relative links
				if (link.indexOf("://") == -1) {
					if (link.charAt(0) == '/') {
						link = "http://" + pageURL.getHost() + link;
					} else if (link.charAt(0) == '.') {
						link = link.substring(1);
						String tmp_link = pageURL.getHost();
						tmp_link = tmp_link.substring(0, tmp_link.lastIndexOf('/'));
						link = "http://" + tmp_link + link;
					} else {
						String file = pageURL.getFile();
						if (file.indexOf('/') == -1) {
							link = "http://" + pageURL.getHost() + "/" + link;

						} else {
							String path = file.substring(0, file.lastIndexOf('/') + 1);

							if (link.charAt(0) == '.') {
								link = link.substring(2);
								// remove last slash from path
								path = path.substring(0, path.length() - 1);
								path = path.substring(0, path.lastIndexOf('/') + 1);
							}

							link = "http://" + pageURL.getHost() + path + link;
						}
					}
				}

				// Remove anchors from link.
				int index = link.indexOf('#');
				if (index != -1) {
					link = link.substring(0, index);
				}
				// Remove leading last slash from URL's host if present.
				link = removeLastSlash(link);

				// skip if url format is not valid
				if (!isVerified(pageURL.toString())) {
					continue;
				}

				Link lnk = new Link();
				lnk.setUrl(link);
				links.add(lnk);

			}
		} catch (Exception e) {
		}
		return links;
	}

	private String removeLastSlash(String tUrl) {
		if (tUrl.endsWith("/")) {
			tUrl = tUrl.substring(0, tUrl.length() - 1);
		}
		return (tUrl);
	}

	@SuppressWarnings("unused")
	private boolean isVerified(String url) {

		if (!url.toLowerCase().startsWith("http://") && !url.toLowerCase().startsWith("https://"))
			return false;

		URL verifiedUrl;
		try {
			verifiedUrl = new URL(url);
			return true;
		} catch (Exception e) {
			return false;
		}

	}

	public LinkService getLinkService() {
		return linkService;
	}

	public void setLinkService(LinkService linkService) {
		this.linkService = linkService;
	}

}
