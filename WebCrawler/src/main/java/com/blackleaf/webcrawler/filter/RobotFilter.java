package com.blackleaf.webcrawler.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RobotFilter extends BaseLinkFilter {
	private Map<String, List<String>> robotMap;

	public RobotFilter() {
		robotMap = new HashMap<String, List<String>>();
	}

	private List<String> getRobotLink(String originalLink) throws IOException {
		URL url = new URL(originalLink);
		String host = url.getHost();
		URL hostLink = new URL("http://" + host + "/robots.txt");

		if (robotMap.containsKey(hostLink.toString()))
			return robotMap.get(hostLink.toString());
		else {
			List<String> robotLink = new ArrayList<String>();

			BufferedReader br = new BufferedReader(new InputStreamReader(hostLink.openStream()));

			String line;
			String disallowLink;
			while ((line = br.readLine()) != null) {
				if (line.toLowerCase().startsWith("disallow:")) {
					disallowLink = line.substring(line.lastIndexOf("Disallow:"));
					if (disallowLink.contains("#")) {
						// remove comments
						disallowLink = disallowLink.substring(0, disallowLink.indexOf("#"));
					}
					disallowLink.trim();
					disallowLink = hostLink + disallowLink;
					robotLink.add(disallowLink);
				}
			}

			br.close();

			robotMap.put(hostLink.toString(), robotLink);
			return robotLink;
		}
	}

	@Override
	public boolean doFilter(String link) {
		List<String> robotLink;
		try {
			robotLink = getRobotLink(link);

			for (String lnk : robotLink) {
				if (link.startsWith(lnk)) {
					return false;
				}
			}

			return getNextFilter().doFilter(link);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("invalid link: " + link);
			return false;
		}

	}

	@Override
	public String getFilterName() {
		return ROBOT_FILTER;
	}

}
