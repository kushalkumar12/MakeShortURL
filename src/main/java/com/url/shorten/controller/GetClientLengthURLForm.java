package com.url.shorten.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.url.shorten.controller.service.URLService;
import com.url.shorten.controller.util.URLConstants;
import com.url.shorten.controller.util.Util;
import com.url.shorten.model.LongURL;
import com.url.shorten.model.ShortURL;
import com.url.shorten.model.URLStore;

@RestController
public class GetClientLengthURLForm extends Util implements URLConstants{

	@Autowired
	private URLService urlService;

	@SuppressWarnings("null")
	@PostMapping(LONG_TO_SHORT_IN)
	public String getUser(@RequestBody String url) throws Exception {

		String response = NO_RESPONSE;
		LongURL longUrl = new LongURL();
		try {

			if (null != url && !url.isBlank()) {
				longUrl.setLengthUrl(decodeUrl(url));
				response = urlService.saveLongUrl(longUrl);
			} else {
				response = NO_VALUE_ARRIVED;
			}
		} catch (Exception e) {
			printException(e);
		}
		return response;
	}

	@PostMapping(GET_LONG_URL_IN)
	public URLStore getLongUrl(@RequestBody ShortURL url) {
		return urlService.getShorterUrl(url.getShortUrl());
	}
}