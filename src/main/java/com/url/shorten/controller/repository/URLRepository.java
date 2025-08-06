package com.url.shorten.controller.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.url.shorten.model.URLStore;

public interface URLRepository extends JpaRepository<URLStore, Long> {
	 URLStore findByShortUrl(String shortUrl);
}
