package com.pro.moviefx.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Tv extends Media {

	private static final long serialVersionUID = 1L;

	private String name;
	private String original_name;
	private String popularity;
	private String first_air_date;
	
}
