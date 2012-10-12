package com.blackleaf.webcrawler.core;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.wsdl.Definition;
import javax.wsdl.Service;
import javax.wsdl.WSDLException;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import javax.wsdl.xml.WSDLWriter;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

import com.blackleaf.webcrawler.domain.LinkRecord;
import com.blackleaf.webcrawler.domain.ServiceProfile;
import com.blackleaf.webcrawler.domain.WSDLDescriptor;

public class PageManager {

	private String pageContents;

	public PageManager() {
		pageContents = "";

	}

	public ArrayList<LinkRecord> retrieveLinks(LinkRecord pageLink) throws MalformedURLException {
		URL pageURL = new URL(pageLink.getLink());
		// retrieveContents(pageURL);
		Pattern p = Pattern.compile("<*\\s+href\\s*=\\s*\"?(.*?)[\"|>]", Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(pageContents);
		ArrayList<LinkRecord> links = new ArrayList<LinkRecord>();

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

				// String baseURL = getBaseUrl(pageURL);

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
								path = path.substring(0, path.length() - 1);// remove
																			// last
																			// slash
																			// from
																			// path
								path = path.substring(0, path.lastIndexOf('/') + 1);
							}

							link = "http://" + pageURL.getHost() + path + link;
							// link = baseURL + link;
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

				LinkRecord lnk = new LinkRecord();
				lnk.setLink(link);
				lnk.setInLink(pageLink.getLink());
				lnk.setInLinkId(pageLink.getLinkId());
				links.add(lnk);

			}
		} catch (Exception e) {
		}
		return links;
	}

	private void retrieveContents(URL pageURL) {

		StringBuffer sb = new StringBuffer();
		String s;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(pageURL.openStream()));
			while ((s = br.readLine()) != null) {
				sb.append(s);
			}

			pageContents = sb.toString();
			br.close();

		} catch (MalformedURLException mue) {
			System.out.println(mue);

		} catch (IOException ioe) {
			// System.out.println(ioe);

		}

	}

	private String removeLastSlash(String tUrl) {
		if (tUrl.endsWith("/")) {
			tUrl = tUrl.substring(0, tUrl.length() - 1);
		}
		return (tUrl);
	}

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

	public ServiceProfile getServiceProfile(String url) {
		return null;
	}

	public WSDLDescriptor processWSDLPage(String url, String wsdlFolder) {
		try {
			String pattern = "<wsdl:definitions";
			retrieveContents(new URL(url));
			if (pageContents.indexOf(pattern) == -1)
				return null;

			WSDLFactory factory = WSDLFactory.newInstance();
			WSDLReader reader = factory.newWSDLReader();
			reader.setFeature("javax.wsdl.verbose", true);
			reader.setFeature("javax.wsdl.importDocuments", true);
			Definition def = reader.readWSDL(url);
			WSDLWriter writer = factory.newWSDLWriter();

			String fileName = System.currentTimeMillis() + ".txt";
			FileOutputStream fout = new FileOutputStream(wsdlFolder + "/" + fileName);
			writer.writeWSDL(def, fout);
			fout.close();

			WSDLDescriptor wsdl = new WSDLDescriptor();
			wsdl.setFileName(fileName);

			Map map = def.getServices();
			printMap(map);

			Element ele = def.getDocumentationElement();
			if (ele != null)
				wsdl.setDescription(ele.getTextContent());
			wsdl.setUrl(url);

			System.out.println("wsdl: " + url);
			return wsdl;
		} catch (WSDLException e) {
			return null;
		} catch (MalformedURLException e) {
			System.out.println("invalid url: " + url);
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private void printMap(Map map) {
		for (QName key : (Set<QName>) map.keySet()) {
			System.out.println("key=" + key.toString() + " value=" + ((Service) map.get(key)).getQName().getNamespaceURI());
		}
	}

	public static void main(String[] args) throws IOException {
		String url = "http://hzsrc.ctfs.ftn.qq.com/ftn_handler/26f4fc7cfaa8a79e6b88989fb1411cc593c605ba4e4e31320a4061b6c1cc84dc986dfd73a317d5b108a4b47f89bdd2d11d9afb3ae4935b48e10cf7575cb0c990/?fname=%E9%9B%81%E8%8D%A1%E5%B1%B12012-10-3.rar&k=7c633736c66d6884b5a0bc321162564d0454010e5252545119565554004f505607501a570f06514f075002070e51535b0152540f377164b3d7d683ff8a505453064e06061a514a105511370b&fr=00&&txf_fid=0000000077a2a1ba7191478692a7a0d992af1b99";
		BufferedInputStream bi = new BufferedInputStream(new URL(url).openStream());
		FileOutputStream fout = new FileOutputStream(new File("test.txt"));
		byte[] buffer = new byte[256];
		int len = 0;
		try {
			while ((len = bi.read(buffer)) != -1) {
				fout.write(buffer, 0, len);
			}

		} catch (MalformedURLException mue) {
			System.out.println(mue);

		} catch (IOException ioe) {
			// System.out.println(ioe);

		} finally {
			bi.close();
			fout.close();
		}
	}
}
