package com.pro.moviefx.api;

import lombok.Getter;

@Getter
public abstract class Tmdb {
	protected Integer page;
	protected Integer total_pages;
	protected Integer total_results;
	protected Boolean success;
	protected Integer status_code;
	protected String  status_message;
}
