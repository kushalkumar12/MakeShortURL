package com.url.shorten.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.url.shorten.controller.service.URLService;
import com.url.shorten.controller.util.Const;
import com.url.shorten.model.LongURL;
import com.url.shorten.model.ShortURL;
import com.url.shorten.model.URLStore;

@RestController
@RequestMapping("/api")
public class GetClientLengthURLRest extends Const{

	@Autowired
	private URLService urlService;
	
	@PostMapping("/long/to/short")
    public String getUser(@RequestBody LongURL url) {
		return urlService.saveLongUrl(url);
    }

	@PostMapping("/get/long/url")
    public URLStore getLongUrl(@RequestBody ShortURL url) {
    	return urlService.getShorterUrl(url.getShortUrl());
    }
}