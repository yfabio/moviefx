package com.pro.moviefx.api;

public enum MovieApi implements SortBy {	
	
	POPULAR("Popular"),
	NOW_PLAYING("Now playing"),
	UPCOMING("Upcoming"),
	TOP_RATED("Top rated");
	
	private String title;

	private MovieApi(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

}
