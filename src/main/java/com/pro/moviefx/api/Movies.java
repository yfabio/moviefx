package com.pro.moviefx.api;

import java.util.ArrayList;
import java.util.List;

public class Movies {
	
	private Integer page;
	private List<Movie> results = new ArrayList<>();
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public List<Movie> getResults() {
		return results;
	}
	public void setResults(List<Movie> results) {
		this.results = results;
	}
	
	
	
}
