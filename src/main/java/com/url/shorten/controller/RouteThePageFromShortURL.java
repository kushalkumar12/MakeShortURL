package com.url.shorten.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.url.shorten.controller.service.URLService;
import com.url.shorten.controller.util.Const;
import com.url.shorten.model.URLStore;

@RestController

public class RouteThePageFromShortURL extends Const{
	
	@Autowired
	private URLService urlService;

	@GetMapping("/{shortURL}")
	public ResponseEntity<Void> redirectToOriginalURL(@PathVariable("shortURL") String url){
		
		URLStore urlStore = urlService.getShorterUrl(url);
		System.out.println("Passing shorter URL to Database : " + ((null != urlStore && null !=  urlStore.getLongUrl()) ? urlStore.getLongUrl() : ""));
		if(null == urlStore || urlStore.getLongUrl() == null) {
			return ResponseEntity.notFound().build(); // 404 if not found
		}
		
		  return ResponseEntity.status(HttpStatus.FOUND) // 302 redirect
	                .location(URI.create(urlStore.getLongUrl()))
	                .build();
	}
}
