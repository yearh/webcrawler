package com.blackleaf.webcrawler.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class WebCrawler {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("classpath:com/blackleaf/webcrawler/config/spring/application-webcrawler-deploy.xml");
	}

}
