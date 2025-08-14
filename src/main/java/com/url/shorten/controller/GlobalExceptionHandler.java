package com.url.shorten.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(Exception.class)
	public String handleException(Exception e, Model model) {
		model.addAttribute("errorMessage", e.getMessage());
		e.printStackTrace(); // log the stack trace
		return "error"; // error.html in templates
	}
}