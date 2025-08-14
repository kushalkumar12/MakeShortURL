package com.url.shorten.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.url.shorten.controller.util.URLConstants;
import com.url.shorten.controller.util.Util;

@Controller
public class HomeController extends Util implements URLConstants{
	
	@GetMapping(SHORTEN_URL_HOME)
	public String openHome(Model model) {
		printLog("Opening Home page............", HomeController.class);
	    model.addAttribute("message", "Welcome to URL Shortener!");
	    return getPageByURL(SHORTEN_URL_HOME);
    }
}
