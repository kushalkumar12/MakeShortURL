package com.url.shorten.controller.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class MappingConstants extends Const implements URLConstants{
	
	public String getPageByURL(String URL) {
		return rootURLToPage.getOrDefault(URL, "");
	}
	
	
	private static final Map<String, String> rootURLToPage = new HashMap<>();
	static {
		rootURLToPage.put(SHORTEN_URL_HOME, "URLHome");
	}
}
