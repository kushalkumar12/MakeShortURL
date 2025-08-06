package com.url.shorten.controller.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.url.shorten.controller.repository.URLRepository;
import com.url.shorten.controller.util.Util;
import com.url.shorten.model.LongURL;
import com.url.shorten.model.URLStore;

@Service
public class URLService {

	@Autowired
	private URLRepository uRLRepository;
	
	@Autowired
	private Util util;
	
	public String saveLongUrl(LongURL url) {

		String shorterUrl = util.createShorterUrl();
		URLStore urlShorter = new URLStore(null ,url.getLengthUrl(), shorterUrl);
		uRLRepository.save(urlShorter);
		System.out.println("Generated shorter URL : " + shorterUrl);
		
		return shorterUrl;
	}

	public URLStore getShorterUrl(String shortUrl) {
		return uRLRepository.findByShortUrl(shortUrl);
	}

}
