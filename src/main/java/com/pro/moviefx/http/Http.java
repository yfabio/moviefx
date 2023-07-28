package com.pro.moviefx.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandler;

public class Http  {
	
	private static HttpClient httpClient = HttpClient.newHttpClient();

	public static <T> T get(String url, BodyHandler<T> body,String...headers)  {
				
		try {
			HttpRequest httpRequest = HttpRequest.newBuilder()
					.uri(URI.create(url))	
					.headers(headers)				
					.GET()				
					.build();			 
			return	httpClient.send(httpRequest, body).body();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	
	
}
