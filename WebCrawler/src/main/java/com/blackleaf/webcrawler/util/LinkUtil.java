package com.blackleaf.webcrawler.util;

import java.net.MalformedURLException;
import java.net.URL;

public class LinkUtil {
	public static String getHost(String url) {
		URL address;
		try {
			address = new URL(url);
			return address.getHost();
		} catch (MalformedURLException e) {
			return null;
		}
	}
}
