package com.blackleaf.webcrawler.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

import com.blackleaf.webcrawler.domain.LinkRecord;
import com.blackleaf.webcrawler.domain.WSDLDescriptor;
import com.blackleaf.webcrawler.interfaces.LinkFilter;
import com.blackleaf.webcrawler.service.DatabaseService;

public class Crawler implements Runnable {

	private final static long INVALID_LINK_ID = -1;

	private LinkRecord seedLink;
	private LinkedHashSet<LinkRecord> newLinks = new LinkedHashSet<LinkRecord>();
	private HashSet<LinkRecord> oldLinks = new HashSet<LinkRecord>();
	private static ArrayList<HashSet<LinkRecord>> allLinks = new ArrayList<HashSet<LinkRecord>>();
	private static CrawlerThread crawlerThread = new CrawlerThread();
	private LinkFilter linkFilter;
	private static int linkCount = 0;
	private static final String WSDL_FOLDER = "wsdl";
	private DatabaseService databaseService;

	public Crawler(String url, LinkFilter filter) {
		databaseService = new DatabaseService();
		seedLink = new LinkRecord();
		seedLink.setLink(url);
		seedLink.setLinkId(INVALID_LINK_ID);
		linkFilter = filter;
		new Thread(this).start();
	}

	private synchronized int increaseLinkCount() {
		linkCount++;
		return linkCount;
	}

	public void run() {

		allLinks.add(newLinks);
		allLinks.add(oldLinks);
		// this variable stores root node
		ArrayList<LinkRecord> links = new ArrayList<LinkRecord>();
		PageManager m = new PageManager();
		int count = 0;
		WSDLDescriptor wsdl = null;

		LinkRecord currentLink;

		try {
			crawlerThread.addToList(newLinks, seedLink);

			// main loop
			while (!newLinks.isEmpty() && count < 3000) {

				currentLink = crawlerThread.getNextLink(newLinks);
				long linkId = databaseService.insertLink(currentLink);
				currentLink.setLinkId(linkId);
				System.out.println("New link: " + currentLink.getLink());
				crawlerThread.addToList(oldLinks, currentLink);

				wsdl = m.processWSDLPage(currentLink.getLink(), WSDL_FOLDER);

				if (wsdl == null) {
					links = m.retrieveLinks(currentLink);
					for (int i = 0; i < links.size(); i++) {
						if (!linkFilter.doFilter(links.get(i).getLink()))
							continue;

						if (crawlerThread.isNewLink(allLinks, links.get(i))) {
							crawlerThread.addToList(newLinks, links.get(i));
						}
					}
				} else {
					databaseService.insertWSDL(wsdl);
				}

				count = increaseLinkCount();
			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}

	}
}
