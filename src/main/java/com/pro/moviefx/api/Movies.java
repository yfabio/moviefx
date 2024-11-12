package com.pro.moviefx.api;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movies {
	
	private Integer page;
	private List<Movie> results = new ArrayList<>();
		
}
