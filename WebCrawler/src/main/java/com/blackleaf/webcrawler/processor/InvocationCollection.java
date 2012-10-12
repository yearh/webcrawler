package com.blackleaf.webcrawler.processor;

import java.util.List;

public class InvocationCollection<T> implements InvocationProcessor<T> {

	private List<InvocationProcessor<T>> processors;

	public List<InvocationProcessor<T>> getProcessors() {
		return processors;
	}

	public void setProcessors(List<InvocationProcessor<T>> processors) {
		this.processors = processors;
	}

	public boolean invoke(T processBean) {
		boolean result = false;
		for (InvocationProcessor<T> proc : processors) {
			result = proc.invoke(processBean);
			if (result == false) {
				return result;
			}
		}
		return result;
	}

}
