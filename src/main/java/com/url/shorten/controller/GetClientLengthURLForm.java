package com.url.shorten.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.url.shorten.controller.service.URLService;
import com.url.shorten.controller.util.URLConstants;
import com.url.shorten.controller.util.Util;
import com.url.shorten.model.LongURL;
import com.url.shorten.model.ShortURL;
import com.url.shorten.model.URLStore;

@RestController
public class GetClientLengthURLForm extends Util implements URLConstants {

	@Autowired
	private URLService urlService;

	@PostMapping(LONG_TO_SHORT_IN)
	@ResponseBody
	public Map<String, String> processLongerURLs(@RequestParam("url") String originalUrl) throws Exception {

		printLog("Longer Url starting converting shorter url.......", GetClientLengthURLForm.class);
		String response;
		LongURL longUrl = new LongURL();
		Map<String, String> result = new HashMap<>();
		try {

			if (originalUrl != null && !originalUrl.isBlank()) {
				longUrl.setLengthUrl(decodeUrl(originalUrl));
				response = urlService.saveLongUrl(longUrl);
			} else {
				response = NO_VALUE_ARRIVED;
			}

			// return as JSON
			if (!NO_VALUE_ARRIVED.equals(response)) {
				response = urlService.generateShortRealURL(response);
			}

			result.put("shortUrl", response);
			printLog("Longer Url end converting shorter succesfully completed.......", GetClientLengthURLForm.class);

		} catch (Exception e) {
			printLog("Not Able to processLongerUrl To short exception occured", GetClientLengthURLForm.class);
			e.printStackTrace();
		}
		return result;

	}

	@PostMapping(GET_LONG_URL_IN)
	public URLStore getLongUrl(@RequestBody ShortURL url) {
		return urlService.getShorterUrl(url.getShortUrl());
	}
}