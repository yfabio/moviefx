package com.pro.moviefx.http;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandler;

import com.pro.moviefx.resource.Resource;

public class Http {

	private static HttpClient httpClient = HttpClient.newHttpClient();

	private static String tmdbKey;
	
	static {
		tmdbKey = Resource.getValue("tmdb.key");
	}
	
	private static String[] headers = { "Content-Type", "application/json", "Authorization","Bearer %s".formatted(tmdbKey)};
	
	public static <T> T get(String url, BodyHandler<T> body) {
		try {
			HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(url)).headers(headers).GET().build();
			return httpClient.send(httpRequest, body).body();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
