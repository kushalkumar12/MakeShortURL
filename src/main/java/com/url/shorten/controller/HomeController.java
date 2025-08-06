package com.url.shorten.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.url.shorten.controller.util.URLConstants;
import com.url.shorten.controller.util.Util;

@Controller
public class HomeController extends Util implements URLConstants {
	@GetMapping(SHORTEN_URL_HOME)
	public String openHome() {
		return getPageByURL(SHORTEN_URL_HOME);
	}
}
