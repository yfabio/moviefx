package com.pro.moviefx.api;

public enum TvApi {
	
	POPULAR("Popular"),
	AIRING_TODAY("Airing today"),
	ON_THE_AIR("On the air"),
	TOP_RATED("Top rated");
	
	private String title;

	private TvApi(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}
}
