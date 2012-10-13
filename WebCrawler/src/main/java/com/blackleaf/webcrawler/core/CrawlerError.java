package com.blackleaf.webcrawler.core;

public class CrawlerError {
	public final static String ERR_PROCESSOR_FALSE_UNKNOWN = "ERR_PROCESSOR_FALSE_UNKNOWN";
	public final static String ERR_PROCESSOR_UNKNOWN = "ERR_PROCESSOR_UNKNOWN";
	public final static String ERR_RETRIEVE_PAGE = "ERR_RETRIEVE_PAGE";
	public final static String ERR_RETRIEVE_LINK = "ERR_RETRIEVE_LINK";
	public final static String ERR_LOCK_LINK = "ERR_LOCK_LINK";
	public final static String ERR_GET_AVAIL_LINK = "ERR_GET_AVAIL_LINK";
	
	private String errorCode;
	private String message;

	public CrawlerError(String errorCode, String message) {
		this.errorCode = errorCode;
		this.message = message;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
