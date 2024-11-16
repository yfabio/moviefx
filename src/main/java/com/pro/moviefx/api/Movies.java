package com.pro.moviefx.api;

import java.util.ArrayList;
import java.util.List;

import com.pro.moviefx.model.Movie;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Movies extends Tmdb {
		
	private List<Movie> results = new ArrayList<>();
	private MovieApi movieApi;
		
}
