package com.blackleaf.webcrawler.processor;

public interface InvocationProcessor<T> {
	public boolean invoke(T processBean);
}
