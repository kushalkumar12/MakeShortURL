package com.url.shorten.controller;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.qrcode.QRCodeWriter;
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
		String shortQRUrl = null;
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
				shortQRUrl = response;
				response = urlService.generateShortRealURL(response);
			}

			result.put("shortUrl", response);
			result.put("shortQRUrl", shortQRUrl);
			printLog("Longer Url end converting shorter succesfully completed.......", GetClientLengthURLForm.class);

		} catch (Exception e) {
			printLog("Not Able to processLongerUrl To short exception occured", GetClientLengthURLForm.class);
			e.printStackTrace();
		}
		return result;

	}
	
	@PostMapping(GET_QR_CODE)
	@ResponseBody
	public Map<String, String> getQRCode(@RequestParam("qrCode") String qrCode) throws Exception{
	    Map<String, String> result = new HashMap<>();
	    try {
	    	printLog("Started: Got the Shorter URL to generate the QR Code : " + qrCode , GetClientLengthURLForm.class);
	        if (qrCode != null && !qrCode.isBlank()) {
	            URLStore urlStore = urlService.getShorterUrl(qrCode);
	            printLog("Started: Got the Longer URL from the shorter URL to generate the QR Code : " + urlStore.getLongUrl() , GetClientLengthURLForm.class);
	            if (null != urlStore) {
	                QRCodeWriter qrCodeWriter = new QRCodeWriter();
	                var bitMatrix = qrCodeWriter.encode(urlStore.getLongUrl(), BarcodeFormat.QR_CODE, 300, 300);

	                BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(bitMatrix);
	                ByteArrayOutputStream baos = new ByteArrayOutputStream();
	                javax.imageio.ImageIO.write(qrImage, "png", baos);

	                String base64Img = "data:image/png;base64," + Base64.getEncoder().encodeToString(baos.toByteArray());
	                result.put("qrImage", base64Img);
	            }
	        } else {
	            result.put("error", "Invalid input");
	        }
	    } catch (Exception e) {
	        result.put("error", "Failed to generate QR");
	        e.printStackTrace();
	    }
	    printLog("Ended: Sending Generated QR Code for View : ", GetClientLengthURLForm.class);
	    return result;
	}


	@PostMapping(GET_LONG_URL_IN)
	public URLStore getLongUrl(@RequestBody ShortURL url) {
		return urlService.getShorterUrl(url.getShortUrl());
	}
}