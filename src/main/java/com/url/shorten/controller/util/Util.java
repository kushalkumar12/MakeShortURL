package com.url.shorten.controller.util;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
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
}
