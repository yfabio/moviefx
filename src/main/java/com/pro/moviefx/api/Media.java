package com.pro.moviefx.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Media implements Serializable {

	private static final long serialVersionUID = 1L;

	protected String backdrop_path;
	protected Genre[] genres;
	protected Long id;
	protected String original_language;
	protected String original_title;
	protected String overview;
	protected String poster_path;
	protected String status;
	protected Double vote_average;
	protected Integer vote_count;
	protected String tagline;
		
	public class Genre {
		public int id;
		public String name;
	}

	
	
}
