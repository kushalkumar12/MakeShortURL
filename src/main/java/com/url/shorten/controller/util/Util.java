package com.url.shorten.controller.util;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.springframework.stereotype.Component;

@Component
public class Util extends MappingConstants {
	

	public String createShorterUrl() {
		return generateId();
	}

	private String generateId() {
		return UUID.randomUUID().toString().replace("-", "").substring(0, 3);
	}

	public void printException(Exception e) throws Exception {
		e.printStackTrace();
		throw e;
	}

	public static String decodeUrl(String encodedUrl) {
		return URLDecoder.decode(encodedUrl, StandardCharsets.UTF_8);
	}

	public static void printLog(String message, Class<?> clazz) {
	    try {
	        String ip = InetAddress.getLocalHost().getHostAddress();
	        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	        System.out.printf("[%s] Class: %-20s IP: %-15s Message: %s%n",timestamp, clazz.getSimpleName(), ip, message);
	    } catch (UnknownHostException e) {
	        System.err.println("Unable to get IP address: " + e.getMessage());
	    }
	}
	
}
