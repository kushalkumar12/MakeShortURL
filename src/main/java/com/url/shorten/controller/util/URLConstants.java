package com.url.shorten.controller.util;

public interface URLConstants {
	// JSP Constants

	public static final String BASE_URL = "/api";
	public static final String SHORTEN_URL_HOME = "/shorten/url/home";

	// URL shortening endpoints
	public static final String LONG_TO_SHORT = BASE_URL + "/long/to/short";
	public static final String GET_LONG_URL = BASE_URL + "/get/long/url";

	// Alternative version if you need the exact paths you specified
	public static final String LONG_TO_SHORT_IN = "/long/to/short/in";
	public static final String GET_LONG_URL_IN = "/get/long/url/in";
	public static final String GET_QR_CODE = "/get/qr/code";
}
