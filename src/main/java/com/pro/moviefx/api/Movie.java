package com.pro.moviefx.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movie extends Media {

	private static final long serialVersionUID = 1L;

	private String release_date;
	private Integer runtime;	
	private String title;
	private Boolean video;
		
	
	
}
