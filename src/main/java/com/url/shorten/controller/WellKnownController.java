package com.url.shorten.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WellKnownController {

    @GetMapping("/.well-known/appspecific/com.chrome.devtools.json")
    public ResponseEntity<String> devToolsFile() {
        return ResponseEntity.notFound().build(); // or return an empty JSON
    }
}