package com.blackleaf.webcrawler.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.blackleaf.webcrawler.filter.HostFilter;
import com.blackleaf.webcrawler.filter.LinkValidFilter;
import com.blackleaf.webcrawler.interfaces.LinkFilter;

public class WebCrawler {

	private static LinkFilter buildLinkFilter() {
		List<String> links = new ArrayList<String>();
		links.add("webservices.seekda.com");
		LinkFilter filter = new LinkValidFilter();
//		filter.setNextFilter(new HostFilter(links));
		return filter;
	}

	private static ArrayList<String> getRootLinks() throws IOException {
		File roots = new File("roots.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(new DataInputStream(new FileInputStream(roots))));

		String line;
		ArrayList<String> links = new ArrayList<String>();

		// add root nodes from file
		while ((line = br.readLine()) != null) {
			links.add(line);
		}

		br.close();

		return links;
	}

	public static void main(String[] args) {
		try {

			ArrayList<String> rootLinks = getRootLinks();

			for (int i = 0; i < rootLinks.size(); i++) {
				new Crawler(rootLinks.get(i), buildLinkFilter());
			}

		} catch (IOException ex) {
			System.out.println(ex);
		}
	}

}
