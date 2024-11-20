package com.pro.moviefx.api;

import java.util.HashMap;
import java.util.Map;

public interface SortBy {

	public default String getValue(String sortBy) {

		Map<String, String> map = new HashMap<>();

		map.put("Popularity Descending", "popularity.desc");
		map.put("Popularity Ascending", "popularity.asc");
		map.put("Rating Descending", "vote_average.desc");
		map.put("Rating Ascending", "vote_average.asc");
		map.put("Release Date Descending", "primary_release_date.desc");
		map.put("Release Date Ascending", "primary_release_date.asc");
		map.put("Title (A-Z)", "title.asc");
		map.put("Title (Z-A)", "title.desc");

		return map.get(sortBy);

	}
}
