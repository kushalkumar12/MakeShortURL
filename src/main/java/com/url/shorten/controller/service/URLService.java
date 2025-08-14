package com.url.shorten.controller.service;

import java.net.InetAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.stereotype.Service;

import com.url.shorten.controller.repository.URLRepository;
import com.url.shorten.controller.util.Util;
import com.url.shorten.model.LongURL;
import com.url.shorten.model.URLStore;

@Service
public class URLService extends Util{

	@Autowired
	private URLRepository uRLRepository;
	
	@Autowired
	private Util util;
	
	private final WebServerApplicationContext serverAppContext;

    public URLService(WebServerApplicationContext serverAppContext) {
        this.serverAppContext = serverAppContext;
    }
    
    public String generateShortRealURL(String generateShortUrl) {
        printLog("Converting shorter to realistic forwardable url", getClass());
        try {
            // Get local IP
            String ip = InetAddress.getLocalHost().getHostAddress();

            // Get running port
            int port = serverAppContext.getWebServer().getPort();

            // Build full URL (http://<ip>:<port>/<shortUrl>)
            String fullUrl = "http://" + ip + ":" + port + "/" + generateShortUrl;

            printLog("Data send to response with realistic forwardable url", getClass());
            return fullUrl;

        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
	
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
