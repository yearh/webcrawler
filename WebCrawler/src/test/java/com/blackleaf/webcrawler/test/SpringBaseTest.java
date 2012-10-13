package com.blackleaf.webcrawler.test;

import org.springframework.test.AbstractDependencyInjectionSpringContextTests;

public class SpringBaseTest extends AbstractDependencyInjectionSpringContextTests {
	@Override
	protected String[] getConfigLocations() {
		return new String[] { "classpath:com/blackleaf/webcrawler/config/spring/application-webcrawler-deploy.xml" };
	}

	public SpringBaseTest() {
		setAutowireMode(AUTOWIRE_BY_NAME);
	}
}
