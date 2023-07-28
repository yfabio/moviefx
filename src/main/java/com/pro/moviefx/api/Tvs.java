package com.pro.moviefx.api;

import java.util.ArrayList;
import java.util.List;

public class Tvs {

	private Integer page;
	private List<Tv> results = new ArrayList<>();
	
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public List<Tv> getResults() {
		return results;
	}
	public void setResults(List<Tv> results) {
		this.results = results;
	}
	
	
	
}
